package ru.skaliush.superlab;

import ru.skaliush.superlab.app.AppContainer;
import ru.skaliush.superlab.app.ConsoleRequestHandler;
import ru.skaliush.superlab.app.LineReader;
import ru.skaliush.superlab.collection.CollectionManager;
import ru.skaliush.superlab.models.Person;
import ru.skaliush.superlab.resolver.CommandResolver;
import ru.skaliush.superlab.storage.StorageReader;

import java.util.Collection;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        initAppContainer();
        initCollection(args);
        ConsoleRequestHandler consoleRequestHandler = new ConsoleRequestHandler();
        consoleRequestHandler.handle();
    }

    private static void initAppContainer() {
        AppContainer app = AppContainer.getInstance();

        LineReader lineReader = new LineReader(System.in, true);
        app.setRequestReader(lineReader);
        CommandResolver commandResolver = new CommandResolver();
        app.setCommandResolver(commandResolver);
    }

    private static void initCollection(String[] args) {
        Collection<Person> personCollection;
        if (args.length > 0) {
            String fileName = args[0];
            StorageReader storageReader = new StorageReader();
            personCollection = storageReader.read(fileName);
        } else {
            personCollection = new HashSet<>();
            // спросить новое имя файла
        }

        CollectionManager collectionManager = new CollectionManager(personCollection);
        AppContainer.getInstance().setCollectionManager(collectionManager);
    }
}