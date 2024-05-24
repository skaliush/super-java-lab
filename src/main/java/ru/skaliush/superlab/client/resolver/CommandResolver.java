package ru.skaliush.superlab.client.resolver;

import ru.skaliush.superlab.client.app.ClientAppContainer;
import ru.skaliush.superlab.client.app.ResponseWriter;
import ru.skaliush.superlab.client.commands.*;
import ru.skaliush.superlab.client.resolver.exceptions.ArgumentMissedException;
import ru.skaliush.superlab.client.resolver.exceptions.CommandNotFoundException;
import ru.skaliush.superlab.client.resolver.exceptions.InvalidArgumentException;
import ru.skaliush.superlab.client.validation.ValidationException;
import ru.skaliush.superlab.client.validation.Validator;

import java.util.Map;

public class CommandResolver {
    private final Map<String, Command> commands = Map.ofEntries(
            Map.entry("login", new LoginCommand()),
            Map.entry("register", new RegisterCommand()),
            Map.entry("help", new HelpCommand()),
            Map.entry("info", new InfoCommand()),
            Map.entry("add", new AddCommand()),
            Map.entry("show", new ShowCommand()),
            Map.entry("execute", new ExecuteScriptCommand()),
            Map.entry("remove", new RemoveByIdCommand()),
            Map.entry("update", new UpdateCommand()),
            Map.entry("clear", new ClearCommand()),
            Map.entry("exit", new ExitCommand())
    );

    public void resolve(String prompt) {
        String[] parts = prompt.split(" ", 2);
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
            ClientAppContainer appContainer = ClientAppContainer.getInstance();
            if (command.needToAuthorize() && !appContainer.isLoggedIn()) {
                ResponseWriter.write("Для начала необходимо войти в аккаунт");
            } else {
                command.exec(argument);
            }
        } else {
            throw new CommandNotFoundException(commandAlias);
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
