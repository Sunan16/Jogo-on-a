package com.jogonca.api_backend.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jogonca.api_backend.exceptions.utils.ExceptionResponse;
import com.jogonca.api_backend.models.dtos.receiveDTOs.ItemDTO;
import com.jogonca.api_backend.models.dtos.receiveDTOs.PaymentDTO;
import com.jogonca.api_backend.models.dtos.receiveDTOs.RecoveryRequestDTO;
import com.jogonca.api_backend.models.dtos.receiveDTOs.UserDTO;
import com.jogonca.api_backend.models.dtos.sendDTOs.AddItemUser;
import com.jogonca.api_backend.models.dtos.sendDTOs.ItemSendDTO;
import com.jogonca.api_backend.models.dtos.sendDTOs.UserSendDTO;
import com.jogonca.api_backend.services.LoginService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/login")
public class LoginController {
    
    public static Map<String, String> emailRecovery = new LinkedHashMap<>();

    @Autowired
    private LoginService loginService;

    @Operation(
        summary = "Adiciona Item a Usuario",
        description = "Utiliza email do usuario para relacionar a algum item, e retorna todos seus itens"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", description = "Item adicionado ao Usuario!",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation =  PaymentDTO.class))
        ),
        @ApiResponse(
            responseCode = "404", description = "Não encontrado com informações oferecidas",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class))
        ),
        @ApiResponse(
            responseCode = "500", description = "Erro Servidor",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class))
        )}
    )
    @PostMapping(
        value = "/addItem",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public List<ItemDTO> addItem(@RequestBody AddItemUser json) {
        List<ItemDTO> list = loginService.addItem(json);
        list.forEach(i -> {
            i.add(linkTo(methodOn(ItemController.class).findAll()).withSelfRel());
            i.add(linkTo(methodOn(ItemController.class).findById("" + i.getKey())).withSelfRel());
            i.add(linkTo(methodOn(ItemController.class).update(new ItemSendDTO())).withRel("PUT - update"));
        });
        return list;
    }

    @Operation(
        summary = "Pegar todos os Itens",
        description = "Utiliza email do usuario para pegar todos os itens pertencente ao mesmo, necessario apenas o preenchimento do campo:\n- email"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", description = "Retorno de Itens",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation =  PaymentDTO.class))
        ),
        @ApiResponse(
            responseCode = "404", description = "Não encontrado com informações oferecidas",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class))
        ),
        @ApiResponse(
            responseCode = "500", description = "Erro Servidor",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class))
        )}
    )
    @PostMapping(
        value = "/itens",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public List<ItemDTO> getItens(@RequestBody UserSendDTO json) {
        List<ItemDTO> list = loginService.getItens(json);
        list.forEach(i -> {
            i.add(linkTo(methodOn(ItemController.class).findAll()).withSelfRel());
            i.add(linkTo(methodOn(ItemController.class).findById("" + i.getKey())).withSelfRel());
            i.add(linkTo(methodOn(ItemController.class).update(new ItemSendDTO())).withRel("PUT - update"));
        });
        return list;
    }

    @Operation(
        summary = "Recuperar Senha",
        description = "Localiza usuario pelo seu e-mail e manda uma mensagem para confirmar a alternancia de senha, a senha somente muda pós a confirmação pelo link. Utilizasse apenas os campos: \n- identifier\n- pass"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", description = "Enviado com sucesso!",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation =  PaymentDTO.class))
        ),
        @ApiResponse(
            responseCode = "404", description = "Não encontrado com informações oferecidas",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class))
        ),
        @ApiResponse(
            responseCode = "500", description = "Erro Servidor",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class))
        )}
    )
    @PostMapping(
        value = "/recoveryForEmail",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public RecoveryRequestDTO recoveryPassEmail(@RequestBody RecoveryRequestDTO json) {
        String token = loginService.recoveryPassEmail(json);
        json.add(linkTo(methodOn(LoginController.class).confirmRecovery(token)).withSelfRel());
        return json;
    }

    @Operation(
        summary = "Confirmar Token",
        description = "Localiza usuario pelo Token, se estiver dentro do prazo de 15 minutos e o token estiver correto, ocorre a mudança de senha"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", description = "Senha Alterada com Sucesso!",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation =  PaymentDTO.class))
        ),
        @ApiResponse(
            responseCode = "404", description = "Não encontrado com informações oferecidas",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class))
        ),
        @ApiResponse(
            responseCode = "500", description = "Erro Servidor",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class))
        )}
    )
    @GetMapping(
        value = "/confirmRecovery/{token}"
    )
    public UserDTO confirmRecovery(@PathVariable String token) {
        UserDTO dto = loginService.confirmRecovery(token);
        dto.add(linkTo(methodOn(UserController.class).findAll()).withSelfRel());
        dto.add(linkTo(methodOn(UserController.class).findById("" + dto.getKey())).withSelfRel());
        dto.add(linkTo(methodOn(UserController.class).update(new UserSendDTO())).withRel("PUT - update"));
        return dto;
    }

}
