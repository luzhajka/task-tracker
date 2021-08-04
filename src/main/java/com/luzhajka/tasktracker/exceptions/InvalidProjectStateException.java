package com.luzhajka.tasktracker.exceptions;

public class InvalidProjectStateException extends RuntimeException {
    public InvalidProjectStateException(String message) {
        super(message);
    }
}
