package ru.skaliush.superlab.resolver.exceptions;

public class CommandNotFoundException extends CommandResolverException {
    private final String commandName;

    public CommandNotFoundException(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getMessage() {
        return "Не найдена команда " + commandName;
    }
}
