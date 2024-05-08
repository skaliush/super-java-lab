package ru.skaliush.superlab.server.app;

import ru.skaliush.superlab.server.collection.CollectionManager;

public class ServerAppContainer {
    private static ServerAppContainer instance;

    private CollectionManager collectionManager;

    private ServerAppContainer() {
    }

    public static ServerAppContainer getInstance() {
        if (instance == null) {
            instance = new ServerAppContainer();
        }
        return instance;
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
}
