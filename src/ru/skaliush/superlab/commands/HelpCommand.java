package ru.skaliush.superlab.commands;

import ru.skaliush.superlab.app.ResponseWriter;
import ru.skaliush.superlab.resolver.CommandResolver;

import java.util.Map;

public class HelpCommand extends Command {
    public void exec(String argument) {
        CommandResolver commandResolver = this.app.getCommandResolver();
        ResponseWriter.write("Список доступных команд:");
        for (Map.Entry<String, Command> commandEntry : commandResolver.getCommands().entrySet()) {
            Command command = commandEntry.getValue();
            String output = " • " + commandEntry.getKey();
            if (command.getArgumentName() != null) {
                output += " {" + command.getArgumentName() + "}";
            }
            output += " - " + command.getDescription();
            ResponseWriter.write(output);
        }
    }

    public String getDescription() {
        return "вывести справку по доступным командам";
    }
}
