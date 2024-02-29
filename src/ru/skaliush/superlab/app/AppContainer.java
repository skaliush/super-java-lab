package ru.skaliush.superlab.app;

import ru.skaliush.superlab.collection.CollectionManager;
import ru.skaliush.superlab.resolver.CommandResolver;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Deque;

public class AppContainer {
    private static AppContainer instance;

    private CollectionManager collectionManager;
    private LineReader requestReader;
    private CommandResolver commandResolver;

    private final Deque<File> scriptsStack = new ArrayDeque<>();

    private AppContainer() {
    }

    public static AppContainer getInstance() {
        if (instance == null) {
            instance = new AppContainer();
        }
        return instance;
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public LineReader getRequestReader() {
        return requestReader;
    }

    public void setRequestReader(LineReader requestReader) {
        this.requestReader = requestReader;
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

    public File getCurrentScript() {
        if (scriptsStack.isEmpty()) {
            return null;
        }
        return scriptsStack.getLast();
    }

    public boolean isInteractiveMode() {
        return !scriptsStack.isEmpty();
    }
}
