package ru.skaliush.superlab.common.network;

public class ServerUnavailableException extends RuntimeException {
    public ServerUnavailableException() {
    }

    public ServerUnavailableException(String message) {
        super(message);
    }
}
