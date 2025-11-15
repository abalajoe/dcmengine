package com.dcm.exception;

public class CustomEntityNotFoundException extends RuntimeException {

    public CustomEntityNotFoundException(String message) {
        super(message);
    }
}
