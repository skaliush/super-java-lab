package ru.skaliush.superlab.server.actions;

import ru.skaliush.superlab.common.request.Request;

public interface Action<T> {
    T execute(Request request);
}
