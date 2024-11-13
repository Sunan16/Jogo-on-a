package com.jogonca.api_backend.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jogonca.api_backend.interfaces.AbstractCrudController;
import com.jogonca.api_backend.models.Item;
import com.jogonca.api_backend.models.dtos.receiveDTOs.ItemDTO;
import com.jogonca.api_backend.models.dtos.sendDTOs.ItemSendDTO;
import com.jogonca.api_backend.services.ItemService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/item")
public class ItemController extends AbstractCrudController<ItemDTO, Item, ItemSendDTO, String> {

    public ItemController(ItemService service) {
        super(service);
    }
    
    @Operation(summary = "Criar um novo item", description = "Cria uma entidade nova, mas cuidado, existem campos unicos como por exemplo 'name', onde gera um erro caso esses campos estejam com dados repetidos")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    public ItemDTO create(@RequestBody ItemSendDTO send) {
        ItemDTO dto = service.insert(send);
        dto.add(linkTo(methodOn(this.getClass()).findById(String.valueOf(dto.getKey()))).withSelfRel());
        return dto;
    }

}