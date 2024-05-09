package ru.skaliush.superlab.client.app;

import ru.skaliush.superlab.client.resolver.CommandResolver;
import ru.skaliush.superlab.common.request.RequestSender;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Deque;

public class ClientAppContainer {
    private static ClientAppContainer instance;

    // сделать для всего интерфейсы
    private LineReader promptReader;
    private CommandResolver commandResolver;
    private RequestSender requestSender;

    private final Deque<File> scriptsStack = new ArrayDeque<>();

    private ClientAppContainer() {
    }

    public static ClientAppContainer getInstance() {
        if (instance == null) {
            instance = new ClientAppContainer();
        }
        return instance;
    }

    public LineReader getPromptReader() {
        return promptReader;
    }

    public void setPromptReader(LineReader promptReader) {
        this.promptReader = promptReader;
    }

    public CommandResolver getCommandResolver() {
        return commandResolver;
    }

    public void setCommandResolver(CommandResolver commandResolver) {
        this.commandResolver = commandResolver;
    }

    public Deque<File> getScriptsStack() {
        return scriptsStack;
    }

    public boolean isInteractiveMode() {
        return !scriptsStack.isEmpty();
    }

    public RequestSender getRequestSender() {
        return requestSender;
    }

    public void setRequestSender(RequestSender requestSender) {
        this.requestSender = requestSender;
    }
}
