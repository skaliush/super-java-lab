package ru.skaliush.superlab.app;

import ru.skaliush.superlab.validation.ValidationException;

public class InvalidArgumentException extends RuntimeException {
    private final String argumentName;
    private final ValidationException cause;


    public InvalidArgumentException(String argumentName, ValidationException cause) {
        this.argumentName = argumentName;
        this.cause = cause;
    }

    public String getArgumentName() {
        return argumentName;
    }

    public ValidationException getCause() {
        return cause;
    }
}
