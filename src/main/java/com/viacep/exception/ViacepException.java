package com.viacep.exception;

public class ViacepException extends RuntimeException {
    public ViacepException(String message) {
        super(message);
    }

    public ViacepException(String message, Throwable cause) {
        super(message, cause);
    }
}
