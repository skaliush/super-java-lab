package ru.skaliush.superlab.client;

import ru.skaliush.superlab.client.app.ClientAppContainer;
import ru.skaliush.superlab.client.app.ConsolePromptHandler;
import ru.skaliush.superlab.client.app.LineReader;
import ru.skaliush.superlab.client.resolver.CommandResolver;
import ru.skaliush.superlab.common.network.RequestSender;

public class ClientMain {
    public static void main(String[] args) {
        initAppContainer();
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
}