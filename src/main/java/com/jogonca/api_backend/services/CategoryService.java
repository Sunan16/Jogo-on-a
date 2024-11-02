package com.jogonca.api_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jogonca.api_backend.interfaces.AbstractCrudService;
import com.jogonca.api_backend.models.Category;
import com.jogonca.api_backend.models.dtos.receiveDTOs.CategoryDTO;
import com.jogonca.api_backend.models.dtos.sendDTOs.CategorySendDTO;
import com.jogonca.api_backend.repositories.CategoryRepository;

@Service
public class CategoryService extends AbstractCrudService<CategoryDTO, Category, CategorySendDTO, String>{

    public CategoryService(@Autowired CategoryRepository repository) {
        super(Category.class.getName(), repository, Category.class, CategoryDTO.class);
    }

    protected void updateData(Category entity, CategoryDTO i) {
        if(i.getName() != null){
            entity.setName(i.getName());
        }
        if(i.getDescription() != null){
            entity.setDescription(i.getDescription());
        }
    }

}
