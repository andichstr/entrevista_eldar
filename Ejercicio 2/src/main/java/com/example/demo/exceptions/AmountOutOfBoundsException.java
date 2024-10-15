package com.example.demo.exceptions;

public class AmountOutOfBoundsException extends CustomException {
    public AmountOutOfBoundsException(String message) {
        super(message);
    }

    public AmountOutOfBoundsException(String message, Throwable t) {
        super(message, t);
    }
}
