package com.mock.app.model.exceptions;

public class InsufficientPermissionsException extends Throwable {
    public InsufficientPermissionsException() {
        super();
    }
    public InsufficientPermissionsException(final String message) {
        super(message);
    }
}
