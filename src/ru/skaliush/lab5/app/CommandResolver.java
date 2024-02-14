package ru.skaliush.lab5.app;

import ru.skaliush.lab5.commands.Command;
import ru.skaliush.lab5.commands.ExitCommand;
import ru.skaliush.lab5.commands.HelpCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandResolver {
    private final List<Command> commands = new ArrayList<>() {{
        add(new HelpCommand());
        add(new ExitCommand());
    }};

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
