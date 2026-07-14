package br.com.c7flex.academia.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND),

    VALIDATION_ERROR(HttpStatus.BAD_REQUEST),

    BUSINESS_ERROR(HttpStatus.BAD_REQUEST),

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED),

    FORBIDDEN(HttpStatus.FORBIDDEN),

    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR);

    private final HttpStatus status;

    ErrorCode(HttpStatus status){
        this.status = status;
    }

    public HttpStatus getStatus(){
        return status;
    }

}