package ru.skaliush.superlab.app;

public class ArgumentMissedException extends RuntimeException {
    private final String argumentName;

    public ArgumentMissedException(String argumentName) {
        this.argumentName = argumentName;
    }

    public String getArgumentName() {
        return argumentName;
    }
}
