package br.com.matrix.exception;

import org.springframework.http.HttpStatus;

public class DuplicateException extends ApiException {
    private static final long serialVersionUID = -5542627657418159227L;

    public DuplicateException() {
        super(HttpStatus.CONFLICT);
    }

    public DuplicateException(String message) {
        super(HttpStatus.CONFLICT, message);
    }

    public DuplicateException(String message, Throwable cause) {
        super(HttpStatus.CONFLICT, message, cause);
    }

    public DuplicateException(Throwable cause) {
        super(HttpStatus.CONFLICT, cause);
    }

    protected DuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(HttpStatus.CONFLICT, message, cause, enableSuppression, writableStackTrace);
    }
}

