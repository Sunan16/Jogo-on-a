package com.jogonca.api_backend.interfaces;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface IDtoSend<TypeIdentifier> extends Serializable {
    
    @JsonIgnore
    public TypeIdentifier getIdentifier();

}
