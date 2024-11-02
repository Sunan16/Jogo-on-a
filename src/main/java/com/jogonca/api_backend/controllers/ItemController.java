package com.jogonca.api_backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jogonca.api_backend.interfaces.AbstractCrudController;
import com.jogonca.api_backend.models.Item;
import com.jogonca.api_backend.models.dtos.receiveDTOs.ItemDTO;
import com.jogonca.api_backend.models.dtos.sendDTOs.ItemSendDTO;
import com.jogonca.api_backend.services.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController extends AbstractCrudController<ItemDTO, Item, ItemSendDTO, String> {

    public ItemController(ItemService service) {
        super(service);
    }
    
}
