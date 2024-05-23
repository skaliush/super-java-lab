package ru.skaliush.superlab.client.app;

import ru.skaliush.superlab.client.resolver.CommandResolver;
import ru.skaliush.superlab.client.resolver.exceptions.ArgumentMissedException;
import ru.skaliush.superlab.client.resolver.exceptions.CommandNotFoundException;
import ru.skaliush.superlab.client.resolver.exceptions.InvalidArgumentException;
import ru.skaliush.superlab.common.network.ServerUnavailableException;

public class ConsolePromptHandler {
    public void handle() {
        ClientAppContainer app = ClientAppContainer.getInstance();
        LineReader reader = app.getPromptReader();
        CommandResolver commandResolver = app.getCommandResolver();
        ResponseWriter.write("Привет");
        while (true) {
            try {
                String prompt = reader.nextLine().trim().toLowerCase();
                if (!prompt.equals("")) {
                    try {
                        commandResolver.resolve(prompt);
                    } catch (CommandNotFoundException e) {
                        ResponseWriter.write("404 Not found");
                    } catch (ArgumentMissedException e) {
                        ResponseWriter.write("Пропущен обязательный аргумент " + e.getArgumentName());
                    } catch (InvalidArgumentException e) {
                        ResponseWriter.write("Неверный формат аргумента " + e.getArgumentName() + ":");
                        for (String errorMsg : e.getCause().getErrors()) {
                            ResponseWriter.write(" • " + errorMsg);
                        }
                    } catch (ServerUnavailableException e) {
                        ResponseWriter.write("На данный момент сервер недоступен. Попробуйте позже.");
                        // System.err.println(e.getMessage());
                    }
                } else {
                    ResponseWriter.write("Введите нужную команду");
                }
            } catch (StopProgramException | EndOfFileException e) {
                break;
            }
        }
        ResponseWriter.write("Пока");
    }
}
