package ru.skaliush.superlab.server.app;

import ru.skaliush.superlab.server.collection.PostgresCollectionManager;
import ru.skaliush.superlab.server.storage.StorageSaver;
import ru.skaliush.superlab.server.user.UserManager;

import java.sql.Connection;

public class ServerAppContainer {
    private static ServerAppContainer instance;

    private PostgresCollectionManager collectionManager;
    private StorageSaver storageSaver;

    private UserManager userManager;

    private Connection connection;

    private ServerAppContainer() {
    }

    public static ServerAppContainer getInstance() {
        if (instance == null) {
            instance = new ServerAppContainer();
        }
        return instance;
    }

    public PostgresCollectionManager getCollectionManager() {
        return collectionManager;
    }

    public void setCollectionManager(PostgresCollectionManager collectionManager) {
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

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}
