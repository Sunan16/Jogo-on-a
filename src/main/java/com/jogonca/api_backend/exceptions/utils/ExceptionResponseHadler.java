package com.jogonca.api_backend.exceptions.utils;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jogonca.api_backend.exceptions.NotFoundException;
import com.jogonca.api_backend.exceptions.OperationErrorException;
import com.jogonca.api_backend.exceptions.RequestErrorException;
import com.jogonca.api_backend.exceptions.UnAuthorizedException;
import com.jogonca.api_backend.exceptions.UnsuportedMediaType;

@ControllerAdvice
@RestController
public class ExceptionResponseHadler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RequestErrorException.class)
    public final ResponseEntity<ExceptionResponse> handlerRequestError(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false), ex.getClass().getSimpleName(), "Erro 400 - Requisição mal feita");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public final ResponseEntity<ExceptionResponse> handlerResourceNotFound(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false), ex.getClass().getSimpleName(), "Erro 401 - Não Autorizado");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handlerNotFound(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false), ex.getClass().getSimpleName(), "Erro 404 - Não Encontrado");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnsuportedMediaType.class)
    public final ResponseEntity<ExceptionResponse> handlerUnsuportedMedia(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false), ex.getClass().getSimpleName(), "Erro 415 - Midia não suportada");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handlerGenericErrorInternal(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false), ex.getClass().getSimpleName(), "Erro 500 - Não identificado");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(OperationErrorException.class)
    public final ResponseEntity<ExceptionResponse> handlerOperationError(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false), ex.getClass().getSimpleName(), "Erro 500 - Erro interno");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
