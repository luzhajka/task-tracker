package com.luzhajka.tasktracker.exceptions;

public class ReadIncomingFileException extends RuntimeException {
    public ReadIncomingFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReadIncomingFileException(String message) {
        super(message);

    }
}
