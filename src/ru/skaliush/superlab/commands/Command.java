package ru.skaliush.superlab.commands;

import ru.skaliush.superlab.app.AppContainer;

public abstract class Command {
    protected final AppContainer app;

    public Command() {
        this.app = AppContainer.getInstance();
    }

    public abstract void exec();

    public abstract String getDescription();

    public abstract String getAlias();

    public String getPattern() {
        return getAlias();
    }
}
