package com.jogonca.api_backend.models.dtos.receiveDTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.jogonca.api_backend.interfaces.AbstractDTOHateoas;

@JsonPropertyOrder({"id","name","description","urlItem","urlPhoto","category",})
public class ItemDTO extends AbstractDTOHateoas<ItemDTO> {

    @JsonProperty("id")
    private Long key;
    private String name;
    private String description;
    private String urlItem;
    private String urlPhoto;
    private CategoryDTO category;

    public ItemDTO() {}

    public ItemDTO(Long key, String name, String description, String urlItem, String urlPhoto, CategoryDTO category) {
        this.key = key;
        this.name = name;
        this.description = description;
        this.urlItem = urlItem;
        this.urlPhoto = urlPhoto;
        this.category = category;
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

    public String getUrlItem() {
        return urlItem;
    }

    public void setUrlItem(String urlItem) {
        this.urlItem = urlItem;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

}
