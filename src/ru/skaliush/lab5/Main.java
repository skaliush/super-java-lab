package ru.skaliush.lab5;

import ru.skaliush.lab5.app.AppContainer;
import ru.skaliush.lab5.app.CommandResolver;
import ru.skaliush.lab5.app.ConsoleRequestHandler;
import ru.skaliush.lab5.collection.CollectionManager;

public class Main {
    public static void main(String[] args) {
        initAppContainer();
        ConsoleRequestHandler consoleRequestHandler = new ConsoleRequestHandler();
        consoleRequestHandler.handle();
    }

    private static void initAppContainer() {
        AppContainer app = AppContainer.getInstance();
        CommandResolver commandResolver = new CommandResolver();
        app.setCommandResolver(commandResolver);
        CollectionManager collectionManager = new CollectionManager();
        app.setCollectionManager(collectionManager);
    }
}