package com.jogonca.api_backend.exceptions.utils;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse  implements Serializable {
    
    private Date timestamp;
    private String message;
    private String details;
    private String className;
    private String httpCode;

    public ExceptionResponse(Date timestamp, String message, String details, String className, String httpCode) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.className = className;
        this.httpCode = httpCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public String getClassName() {
        return className;
    }

    public String getHttpCode() {
        return httpCode;
    }
    
}
