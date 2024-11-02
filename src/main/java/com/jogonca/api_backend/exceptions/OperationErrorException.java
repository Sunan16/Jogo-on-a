package com.jogonca.api_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class OperationErrorException extends RuntimeException {
    
    public OperationErrorException(){
        super("Erro Interno do Servidor!\nEntre em contato com o Suporte");
    }

    public OperationErrorException(String msg){
        super(msg);
    }
    
}
