package ru.skaliush.superlab.resolver.exceptions;

import ru.skaliush.superlab.validation.ValidationException;

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
