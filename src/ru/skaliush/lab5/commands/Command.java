package ru.skaliush.lab5.commands;

public abstract class Command {
    public abstract void exec();

    public abstract String getDescription();

    public abstract String getAlias();

    public String getPattern() {
        return getAlias();
    }
}
