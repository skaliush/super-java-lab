package ru.skaliush.lab5.commands;

import ru.skaliush.lab5.app.StopProgramException;

public class ExitCommand extends Command {
    public void exec() {
        throw new StopProgramException();
    }

    public String getAlias() {
        return "exit";
    }

    public String getDescription() {
        return "завершить программу (без сохранения в файл)";
    }
}
