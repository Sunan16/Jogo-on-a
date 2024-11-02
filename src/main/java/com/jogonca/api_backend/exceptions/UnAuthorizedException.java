package com.jogonca.api_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException extends RuntimeException {
    
    public UnAuthorizedException(){
        super("Não Autorizado para realizar Operações");
    }

    public UnAuthorizedException(String msg){
        super(msg);
    }
    
}