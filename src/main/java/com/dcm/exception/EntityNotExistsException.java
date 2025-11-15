package com.dcm.exception;

public class EntityNotExistsException extends RuntimeException {

    public EntityNotExistsException(String message) {
        super(message);
    }

    public EntityNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
