package ru.skaliush.superlab;

import ru.skaliush.superlab.app.AppContainer;
import ru.skaliush.superlab.app.CommandResolver;
import ru.skaliush.superlab.app.ConsoleRequestHandler;
import ru.skaliush.superlab.app.LineReader;
import ru.skaliush.superlab.collection.CollectionManager;

public class Main {
    public static void main(String[] args) {
        initAppContainer();
        ConsoleRequestHandler consoleRequestHandler = new ConsoleRequestHandler();
        consoleRequestHandler.handle();
    }

    private static void initAppContainer() {
        AppContainer app = AppContainer.getInstance();
        CollectionManager collectionManager = new CollectionManager();
        app.setCollectionManager(collectionManager);
        LineReader lineReader = new LineReader(System.in);
        app.setRequestReader(lineReader);
        CommandResolver commandResolver = new CommandResolver();
        app.setCommandResolver(commandResolver);
    }
}