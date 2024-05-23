package ru.skaliush.superlab.server.app;

import ru.skaliush.superlab.server.collection.CollectionManager;
import ru.skaliush.superlab.server.storage.StorageSaver;

import java.sql.Connection;

public class ServerAppContainer {
    private static ServerAppContainer instance;

    private CollectionManager collectionManager;
    private StorageSaver storageSaver;

    private Connection connection;

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

    public StorageSaver getStorageSaver() {
        return storageSaver;
    }

    public void setStorageSaver(StorageSaver storageSaver) {
        this.storageSaver = storageSaver;
    }

    public void save() {
        getStorageSaver().save(getCollectionManager().getCollection());
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
