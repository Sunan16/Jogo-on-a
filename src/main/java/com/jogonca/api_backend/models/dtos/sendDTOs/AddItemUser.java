package com.jogonca.api_backend.models.dtos.sendDTOs;

import com.jogonca.api_backend.interfaces.IDtoSend;

public class AddItemUser implements IDtoSend<String> {
    
    private String emailUser;
    private String nameItem;

    public AddItemUser() { }

    public AddItemUser(String emailUser, String nameItem) {
        this.emailUser = emailUser;
        this.nameItem = nameItem;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }
    
    @Override
    public String getIdentifier() {
        return getEmailUser();
    }

}
