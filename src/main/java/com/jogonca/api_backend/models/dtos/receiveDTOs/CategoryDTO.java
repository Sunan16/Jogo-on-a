package com.jogonca.api_backend.models.dtos.receiveDTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.jogonca.api_backend.interfaces.AbstractDTOHateoas;

@JsonPropertyOrder({"id","name","description"})
public class CategoryDTO extends AbstractDTOHateoas<CategoryDTO> {

    @JsonProperty("id")
    private Long key;
    private String name;
    private String description;

    public CategoryDTO() {}

    public CategoryDTO(Long key, String name, String description) {
        this.key = key;
        this.name = name;
        this.description = description;
    }

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

}
