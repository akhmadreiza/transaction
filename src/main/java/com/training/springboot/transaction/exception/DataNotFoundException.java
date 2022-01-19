package com.training.springboot.transaction.exception;

public class DataNotFoundException extends RuntimeException {
    private String message;

    public DataNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
