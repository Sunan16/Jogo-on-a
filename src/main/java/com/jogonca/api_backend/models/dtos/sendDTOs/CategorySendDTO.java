package com.jogonca.api_backend.models.dtos.sendDTOs;

import com.jogonca.api_backend.interfaces.IDtoSend;

public class CategorySendDTO implements IDtoSend<String> {

    private String name;
    private String description;

    public CategorySendDTO() {}

    public CategorySendDTO(String name, String description) {
        this.name = name;
        this.description = description;
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

    @Override
    public String getIdentifier() {
        return getName();
    }
    
}
