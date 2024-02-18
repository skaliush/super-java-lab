package ru.skaliush.superlab.commands;

import ru.skaliush.superlab.app.CommandResolver;
import ru.skaliush.superlab.app.ResponseWriter;

public class HelpCommand extends Command {
    public void exec() {
        CommandResolver commandResolver = this.app.getCommandResolver();
        ResponseWriter.write("Список доступных команд:");
        for (Command command : commandResolver.getCommands()) {
            ResponseWriter.write(" • " + command.getAlias() + " - " + command.getDescription());
        }
    }

    public String getAlias() {
        return "help";
    }

    public String getDescription() {
        return "вывести справку по доступным командам";
    }
}
