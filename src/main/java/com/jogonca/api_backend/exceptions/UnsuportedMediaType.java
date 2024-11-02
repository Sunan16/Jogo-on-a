package com.jogonca.api_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
public class UnsuportedMediaType extends RuntimeException {
    
    public UnsuportedMediaType(){
        super("Tipo de midia não aceita, provavelmente retorno de um valor 'null' não tratado");
    }

    public UnsuportedMediaType(String msg){
        super(msg);
    }
    
}
