package com.mock.docker.app.model.exceptions;

public class NoSuchProductException extends Throwable {
    public NoSuchProductException() {
        super();
    }
    public NoSuchProductException(final String message) {
        super(message);
    }
}
