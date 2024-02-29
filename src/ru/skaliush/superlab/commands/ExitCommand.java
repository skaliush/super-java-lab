package ru.skaliush.superlab.commands;

import ru.skaliush.superlab.app.StopProgramException;

public class ExitCommand extends Command {
    public void exec(String argument) {
        throw new StopProgramException();
    }

    public String getDescription() {
        return "завершить программу (без сохранения в файл)";
    }
}
