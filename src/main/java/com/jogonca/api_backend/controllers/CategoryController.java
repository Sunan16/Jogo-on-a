package com.jogonca.api_backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jogonca.api_backend.interfaces.AbstractCrudController;
import com.jogonca.api_backend.models.Category;
import com.jogonca.api_backend.models.dtos.receiveDTOs.CategoryDTO;
import com.jogonca.api_backend.models.dtos.sendDTOs.CategorySendDTO;
import com.jogonca.api_backend.services.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController extends AbstractCrudController<CategoryDTO, Category, CategorySendDTO, String> {

    public CategoryController(CategoryService service) {
        super(service);
    }

}
