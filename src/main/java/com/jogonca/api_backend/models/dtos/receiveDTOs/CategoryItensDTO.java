package com.jogonca.api_backend.models.dtos.receiveDTOs;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.jogonca.api_backend.interfaces.AbstractDTOHateoas;

@JsonPropertyOrder({"id","name","description","itens"})
public class CategoryItensDTO extends AbstractDTOHateoas<CategoryItensDTO> {
    
    @JsonProperty("id")
    private Long key;
    private String name;
    private String description;
    private List<ItemCategoryDTO> itens;

    public CategoryItensDTO() {}

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ItemCategoryDTO> getItens() {
        return itens;
    }

}
