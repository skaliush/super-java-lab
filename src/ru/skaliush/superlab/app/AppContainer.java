package ru.skaliush.superlab.app;

import ru.skaliush.superlab.collection.CollectionManager;

public class AppContainer {
    private static AppContainer instance;

    private CollectionManager collectionManager;
    private LineReader requestReader;
    private CommandResolver commandResolver;
    private boolean isInteractiveMode = true;

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

    public boolean isInteractiveMode() {
        return isInteractiveMode;
    }

    public void setInteractiveMode(boolean mode) {
        isInteractiveMode = mode;
    }

    public CommandResolver getCommandResolver() {
        return commandResolver;
    }

    public void setCommandResolver(CommandResolver commandResolver) {
        this.commandResolver = commandResolver;
    }
}
