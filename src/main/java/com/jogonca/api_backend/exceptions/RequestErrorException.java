package com.jogonca.api_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequestErrorException extends RuntimeException {
    
    public RequestErrorException(){
        super("Verifique a integridade dos dados que inseriu!");
    }

    public RequestErrorException(String msg){
        super(msg);
    }
    
}