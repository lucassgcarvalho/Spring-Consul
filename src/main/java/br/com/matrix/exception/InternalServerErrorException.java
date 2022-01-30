package br.com.matrix.exception;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends ApiException {

    public InternalServerErrorException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public InternalServerErrorException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message, cause);
    }

    public InternalServerErrorException(Throwable cause) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, cause);
    }

    protected InternalServerErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message, cause, enableSuppression, writableStackTrace);
    }
}
