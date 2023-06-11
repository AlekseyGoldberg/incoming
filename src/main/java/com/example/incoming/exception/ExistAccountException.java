package com.example.incoming.exception;

public class ExistAccountException extends RuntimeException{
    public ExistAccountException(String message) {
        super(message);
    }
}
