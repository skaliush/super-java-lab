package ru.skaliush.lab5.app;

import ru.skaliush.lab5.collection.CollectionManager;

public class AppContainer {
    private static AppContainer instance;

    private CommandResolver commandResolver;
    private CollectionManager collectionManager;

    private AppContainer() {
    }

    public static AppContainer getInstance() {
        if (instance == null) {
            instance = new AppContainer();
        }
        return instance;
    }

    public CommandResolver getCommandResolver() {
        return commandResolver;
    }

    public void setCommandResolver(CommandResolver commandResolver) {
        this.commandResolver = commandResolver;
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
}
