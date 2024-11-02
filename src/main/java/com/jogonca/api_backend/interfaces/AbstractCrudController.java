package com.jogonca.api_backend.interfaces;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jogonca.api_backend.exceptions.NotFoundException;
import com.jogonca.api_backend.exceptions.utils.ExceptionResponse;
import com.jogonca.api_backend.utils.Utils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public abstract class AbstractCrudController<Dto extends AbstractDTOHateoas<Dto>, E extends IEntity, SendDto extends IDtoSend<TypeIdentifier>, TypeIdentifier> {

    protected final AbstractCrudService<Dto, E, SendDto, TypeIdentifier> service;

    public AbstractCrudController(AbstractCrudService<Dto, E, SendDto, TypeIdentifier> service) {
        this.service = service;
    }

    // GET
    @Operation(summary = "Retorna todos os valores", description = "Retorna o todas entidades")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dto> findAll() {
        List<Dto> list = service.findAll();
        list.forEach(
                dto -> dto.add(linkTo(methodOn(this.getClass()).findById(String.valueOf(dto.getKey()))).withSelfRel()));
        return list;
    }

    // GET
    @Operation(summary = "Retorna Entidade por ID", description = "Retorna o valor da entidade atraves do ID")
    @GetMapping(value = "/{param}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public Dto findById(@PathVariable String param) {
        Dto item = service.findById(Utils.toLong(param));
        if (item == null) {
            throw new NotFoundException("Entidade não encontrada com ID: " + param);
        }
        item.add(linkTo(methodOn(this.getClass()).findById(param)).withSelfRel());
        return item;
    }

    // DELETE
    @Operation(summary = "Deleta por ID", description = "Deleta a entidade através do seu ID, cuidado ao usar, não há como desfazer")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204", description = "Deletado com sucesso!",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
        ),
        @ApiResponse(
            responseCode = "500", description = "Erro Servidor",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ExceptionResponse.class))
        )}
    )
    @DeleteMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public void delete(@PathVariable String id) {
        Dto dto = service.findById(Utils.toLong(id));
        if (dto == null) {
            throw new NotFoundException("Entidade não encontrada com ID: " + id);
        }
        service.delete(dto);
    }

    // POST
    @Operation(summary = "Criar uma nova entidade", description = "Cria uma entidade nova, mas cuidado, existem campos unicos como por exemplo 'name' ou 'username', onde gera um erro caso esses campos estejam com dados repetidos")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    public Dto create(@RequestBody SendDto send) {
        Dto dto = service.insert(send);
        dto.add(linkTo(methodOn(this.getClass()).findById(String.valueOf(dto.getKey()))).withSelfRel());
        return dto;
    }

    // PUT
    @Operation(summary = "Atualização de entidade", description = "Atualiza entidade, atualiza todos os campos, mas se colocar como 'null' alguns campos não tem problema, pois tem uma logica feita pra evitar null em campos vazios\nCaso queira um campo 'null' coloque o valor = ' '. \nExistem campos unicos como por exemplo 'name' ou 'username', onde gera um erro caso esses campos estejam com dados repetidos")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    public Dto update(@RequestBody SendDto send) {
        Dto dto = service.update(send);
        dto.add(linkTo(methodOn(this.getClass()).findById(String.valueOf(dto.getKey()))).withSelfRel());
        return dto;
    }
}
