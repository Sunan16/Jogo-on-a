package com.jogonca.api_backend.models.dtos.sendDTOs;

import com.jogonca.api_backend.interfaces.IDtoSend;

public class ItemSendDTO implements IDtoSend<String> {

    private String name;
    private String description;
    private String urlItem;
    private String urlPhoto;
    private Long idCategoria;

    public ItemSendDTO() {
    }

    public ItemSendDTO(String name, String description, String urlItem, String urlPhoto, Long idCategoria) {
        this.name = name;
        this.description = description;
        this.urlItem = urlItem;
        this.urlPhoto = urlPhoto;
        this.idCategoria = idCategoria;
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

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public String getIdentifier() {
        return getName();
    }

}
