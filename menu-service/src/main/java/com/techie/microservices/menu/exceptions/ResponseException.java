package com.techie.microservices.menu.exceptions;

import lombok.Getter;

@Getter
public class ResponseException extends RuntimeException {
    private final int errorCode;

    public ResponseException(String message) {
        super(message);
        this.errorCode = 400; // Mặc định Bad Request
    }

    public ResponseException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}
