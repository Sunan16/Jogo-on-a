package com.jogonca.api_backend.models.dtos.sendDTOs;

import com.jogonca.api_backend.interfaces.IDtoSend;

public class HabilitySendDTO implements IDtoSend<String> {

    private String name;
    private String description;
    private String url;
    
    public HabilitySendDTO() { }

    public HabilitySendDTO(String name, String description, String url) {
        this.name = name;
        this.description = description;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getIdentifier() {
        return getName();
    }
    
}
