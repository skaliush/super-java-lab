package ru.skaliush.superlab.storage;

public class InvalidStorageFormatException extends RuntimeException {
    public InvalidStorageFormatException() {
    }

    public InvalidStorageFormatException(String message) {
        super(message);
    }
}
