package ru.skaliush.superlab.app;

import ru.skaliush.superlab.commands.*;

import java.util.List;

public class CommandResolver {
    private final List<Command> commands = List.of(
            new HelpCommand(),
            new InfoCommand(),
            new AddCommand(),
            new ShowCommand(),
            new ExitCommand()
    );

    public boolean resolve(String request) {
        for (Command command : commands) {
            if (request.matches(command.getPattern())) {
                command.exec();
                return true;
            }
        }
        return false;
    }

    public List<Command> getCommands() {
        return commands;
    }
}
