package ru.skaliush.superlab.client.commands;

import ru.skaliush.superlab.client.app.ClientAppContainer;
import ru.skaliush.superlab.client.validation.rules.Rule;

import java.util.List;

public abstract class Command {
    protected final ClientAppContainer app;

    public Command() {
        this.app = ClientAppContainer.getInstance();
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
