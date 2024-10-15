package com.example.demo.exceptions;

public class BrandNotValidException extends CustomException {

    public BrandNotValidException(String message) {
        super(message);
    }

    public BrandNotValidException(String message, Throwable t) {
        super(message, t);
    }
}
