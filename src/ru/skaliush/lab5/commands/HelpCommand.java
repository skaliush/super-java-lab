package ru.skaliush.lab5.commands;

import ru.skaliush.lab5.app.AppContainer;

public class HelpCommand extends Command {
    public void exec() {
        AppContainer app = AppContainer.getInstance();
        System.out.println("Список доступных команд:");
        for (Command command : app.getCommandResolver().getCommands()) {
            System.out.println(" • " + command.getAlias() + " - " + command.getDescription());
        }
    }

    public String getAlias() {
        return "help";
    }

    public String getDescription() {
        return "вывести справку по доступным командам";
    }
}
