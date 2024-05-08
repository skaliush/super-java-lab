package ru.skaliush.superlab.client;

import ru.skaliush.superlab.client.app.ClientAppContainer;
import ru.skaliush.superlab.client.app.ConsolePromptHandler;
import ru.skaliush.superlab.client.app.LineReader;
import ru.skaliush.superlab.client.resolver.CommandResolver;
import ru.skaliush.superlab.common.models.Person;
import ru.skaliush.superlab.common.request.RequestSender;
import ru.skaliush.superlab.server.collection.CollectionManager;
import ru.skaliush.superlab.server.storage.StorageReader;

import java.util.Collection;
import java.util.HashSet;

public class ClientMain {
    public static void main(String[] args) {
        initAppContainer();
        initCollection(args);
        ConsolePromptHandler consolePromptHandler = new ConsolePromptHandler();
        consolePromptHandler.handle();
    }

    private static void initAppContainer() {
        ClientAppContainer app = ClientAppContainer.getInstance();
        LineReader lineReader = new LineReader(System.in, true);
        app.setPromptReader(lineReader);
        CommandResolver commandResolver = new CommandResolver();
        app.setCommandResolver(commandResolver);
        RequestSender requestSender = new RequestSender("127.0.0.1", 8888);
        app.setRequestSender(requestSender);
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
        ClientAppContainer.getInstance().setCollectionManager(collectionManager);
    }
}