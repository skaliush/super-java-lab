package ru.skaliush.superlab.client.resolver.exceptions;

import ru.skaliush.superlab.client.validation.ValidationException;

public class InvalidArgumentException extends CommandResolverException {
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

    public String getMessage() {
        return "Неверный формат аргумента " + argumentName;
    }
}
