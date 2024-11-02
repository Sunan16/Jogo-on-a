package com.jogonca.api_backend.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jogonca.api_backend.exceptions.utils.ExceptionResponse;
import com.jogonca.api_backend.mapper.Mapper;
import com.jogonca.api_backend.models.dtos.receiveDTOs.PaymentDTO;
import com.jogonca.api_backend.models.dtos.sendDTOs.CoinSendDTO;
import com.jogonca.api_backend.models.dtos.sendDTOs.PayRequestDTO;
import com.jogonca.api_backend.models.enums.Status;
import com.jogonca.api_backend.services.CoinService;
import com.jogonca.api_backend.services.PaymentService;
import com.jogonca.api_backend.utils.Utils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private CoinService coinService;

    @Autowired
    private PaymentService service;
    
    @Operation(summary = "Retorna Pagamento", description = "Retorna informações de um Pagamento, passe o ID especifico sem erro, o valor dele é numero e positivo")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", description = "Pagamento realizado com sucesso",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation =  PaymentDTO.class))
        ),
        @ApiResponse(
            responseCode = "401", description = "Não Autorizado a fazer consulta",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class))
        ),
        @ApiResponse(
            responseCode = "500", description = "Erro Servidor",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class))
        )}
    )
    @GetMapping(
        value = "/{param}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public PaymentDTO findPayment(@PathVariable String param) {
        var dto = service.findById(Utils.toLong(param));
        return dto; 
    }
    
    @Operation(summary = "Realizar Pagamento", description = "Realiza um pagamento e retorna sua persistencia no banco.\nOs campos não necessario preencher são:\n- Key\n- Status\n- CreatAt")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", description = "Pagamento realizado com sucesso",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation =  PaymentDTO.class))
        ),
        @ApiResponse(
            responseCode = "500", description = "Erro Servidor",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class))
        )}
    )
    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public PaymentDTO payment(@RequestBody PayRequestDTO json){
        json.setKey(null);
        json.setStatus(Status.WAITING.getStatus());
        json.setCreatAt(Instant.now());
        var payment = service.insert(json);
        coinService.insert(new CoinSendDTO(json.getEmail(), json.getCoinsValue()));
        var dtoReceive = Mapper.parseObject(payment, PaymentDTO.class);
        dtoReceive.add(linkTo(methodOn(PaymentController.class).updatePayment(new PayRequestDTO())).withSelfRel());
        return dtoReceive;
    }

    @PostMapping(
        value = "/updated",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public PaymentDTO updatePayment(@RequestBody PayRequestDTO json){
        PaymentDTO item = service.insert(Mapper.parseObject(service.findById(json.getKey()), PayRequestDTO.class));
        item.setStatus(json.valueOfStatus());
        item.add(linkTo(methodOn(PaymentController.class).findPayment("" + json.getKey())).withSelfRel());
        return item;
    }
    
}
