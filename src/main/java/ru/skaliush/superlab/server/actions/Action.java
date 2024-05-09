package ru.skaliush.superlab.server.actions;

import ru.skaliush.superlab.common.network.Request;
import ru.skaliush.superlab.server.app.ServerAppContainer;

public abstract class Action<T> {
    protected final ServerAppContainer app;

    public Action() {
        this.app = ServerAppContainer.getInstance();
    }

    abstract public T execute(Request request);
}
