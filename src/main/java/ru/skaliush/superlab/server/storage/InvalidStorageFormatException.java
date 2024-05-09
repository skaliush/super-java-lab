package ru.skaliush.superlab.server.storage;

public class InvalidStorageFormatException extends RuntimeException {
    public InvalidStorageFormatException() {
    }

    public InvalidStorageFormatException(String message) {
        super(message);
    }
}
