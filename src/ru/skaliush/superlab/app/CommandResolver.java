package ru.skaliush.superlab.app;

import ru.skaliush.superlab.commands.*;
import ru.skaliush.superlab.validation.ValidationException;
import ru.skaliush.superlab.validation.Validator;

import java.util.Map;

public class CommandResolver {
    private final Map<String, Command> commands = Map.of(
            "help", new HelpCommand(),
            "info", new InfoCommand(),
            "add", new AddCommand(),
            "show", new ShowCommand(),
            "save", new SaveCommand(),
            "update", new UpdateCommand(),
            "exit", new ExitCommand()
    );

    public void resolve(String request) {
        String[] parts = request.split(" ", 2);
        String commandAlias = parts[0];
        String argument = null;
        if (parts.length > 1) {
            argument = parts[1].trim();
        }

        if (commands.containsKey(commandAlias)) {
            Command command = commands.get(commandAlias);
            String argumentName = command.getArgumentName();
            if (argumentName != null) {
                if (argument == null) {
                    throw new ArgumentMissedException(argumentName);
                }
                try {
                    Validator.validate(argument, command.getArgumentValidationRules());
                } catch (ValidationException e) {
                    throw new InvalidArgumentException(argumentName, e);
                }
            }
            command.exec(argument);
        } else {
            throw new CommandNotFoundException();
        }
    }

    private boolean checkArgument(String argument) {
        if (argument == null) {
            return false;
        }
        try {
            Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
