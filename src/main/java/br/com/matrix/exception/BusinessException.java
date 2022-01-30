package br.com.matrix.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends ApiException {
    public BusinessException(HttpStatus httpStatus, String s) {
        super(httpStatus, s);
    }
}
