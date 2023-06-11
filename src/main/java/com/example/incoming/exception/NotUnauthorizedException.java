package com.example.incoming.exception;

public class NotUnauthorizedException extends RuntimeException {
    public NotUnauthorizedException(String message) {
        super(message);
    }
}
