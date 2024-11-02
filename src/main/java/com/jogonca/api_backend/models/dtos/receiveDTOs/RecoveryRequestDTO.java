package com.jogonca.api_backend.models.dtos.receiveDTOs;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"identifier", "pass"})
public class RecoveryRequestDTO extends RepresentationModel<RecoveryRequestDTO> {

    private String identifier;  // pode ser email ou telefone
    private String pass;

    public RecoveryRequestDTO() { }

    public RecoveryRequestDTO(String identifier, String pass) {
        this.identifier = identifier;
        this.pass = pass;
    }

    // Getters e Setters
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
}