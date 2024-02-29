package ru.skaliush.superlab.commands;

import ru.skaliush.superlab.app.AppContainer;
import ru.skaliush.superlab.validation.rules.Rule;

import java.util.List;

public abstract class Command {
    protected final AppContainer app;

    public Command() {
        this.app = AppContainer.getInstance();
    }

    public abstract void exec(String argument);

    public abstract String getDescription();

    /**
     * @return argument name or null, if the argument is not needed
     */
    public String getArgumentName() {
        return null;
    }

    public List<Rule> getArgumentValidationRules() {
        return List.of();
    }
}
