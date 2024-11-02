package com.jogonca.api_backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jogonca.api_backend.exceptions.NotFoundException;
import com.jogonca.api_backend.interfaces.AbstractCrudService;
import com.jogonca.api_backend.mapper.Mapper;
import com.jogonca.api_backend.models.Item;
import com.jogonca.api_backend.models.dtos.receiveDTOs.ItemDTO;
import com.jogonca.api_backend.models.dtos.sendDTOs.ItemSendDTO;
import com.jogonca.api_backend.repositories.CategoryRepository;
import com.jogonca.api_backend.repositories.ItemRepository;

@Service
public class ItemService extends AbstractCrudService<ItemDTO, Item, ItemSendDTO, String> {

    @Autowired
    private CategoryRepository dependecy;

    public ItemService(@Autowired ItemRepository repository) {
        super(Item.class.getName(), repository, Item.class, ItemDTO.class);
    }

    public ItemDTO findByName(String name) {
        Optional<Item> item = repository.findByIdentifier(name);
        if (item.isEmpty()) {
            throw new NotFoundException("E-mail não encontrado ou usuario não utilizando e-mail informado!");
        }
        return Mapper.parseObject(item.get(), ItemDTO.class);
    }

    public Item findByNameEntity(String name) {
        Optional<Item> item = repository.findByIdentifier(name);
        if (item.isEmpty()) {
            throw new NotFoundException("E-mail não encontrado ou usuario não utilizando e-mail informado!");
        }
        return item.get();
    }

    protected void updateData(Item entity, ItemDTO i) {
        if (i.getName() != null) {
            entity.setName(i.getName());
        }
        if (i.getDescription() != null) {
            entity.setDescription(i.getDescription());
        }
        if (i.getUrlItem() != null) {
            entity.setUrlItem(i.getUrlItem());
        }
        if (i.getUrlPhoto() != null) {
            entity.setUrlPhoto(i.getUrlPhoto());
        }
        if (i.getCategory() != null && i.getCategory().getKey() != null) {
            entity.setCategory(dependecy.findById(i.getCategory().getKey()).orElseThrow(
                    () -> new NotFoundException("Categoria não encontrada!")));
        }
    }

}
