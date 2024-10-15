package com.example.demo.exceptions;

public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable t) {
        super(message, t);
    }
}
