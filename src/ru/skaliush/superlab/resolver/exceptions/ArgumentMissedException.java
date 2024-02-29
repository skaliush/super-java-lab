package ru.skaliush.superlab.resolver.exceptions;

public class ArgumentMissedException extends CommandResolverException {
    private final String argumentName;

    public ArgumentMissedException(String argumentName) {
        this.argumentName = argumentName;
    }

    public String getArgumentName() {
        return argumentName;
    }

    public String getMessage() {
        return "Пропущен обязательный аргумент " + argumentName;
    }
}
