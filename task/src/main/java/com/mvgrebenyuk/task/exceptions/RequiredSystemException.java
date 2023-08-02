package com.mvgrebenyuk.task.exceptions;

public class RequiredSystemException extends RuntimeException {
    public RequiredSystemException(String message) {
        super(message);
    }
}
