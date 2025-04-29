package br.com.marcosprado.filmlocationapi.domain.exception;

import org.springframework.http.HttpStatus;

public class NoDataFoundException extends RuntimeException {
    private final HttpStatus errorCode;

    public NoDataFoundException(String message) {
        super(message);
        this.errorCode = HttpStatus.NOT_FOUND;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }
}
