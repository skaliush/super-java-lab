package ru.skaliush.superlab.client.app;

import ru.skaliush.superlab.client.resolver.CommandResolver;
import ru.skaliush.superlab.client.resolver.exceptions.ArgumentMissedException;
import ru.skaliush.superlab.client.resolver.exceptions.CommandNotFoundException;
import ru.skaliush.superlab.client.resolver.exceptions.InvalidArgumentException;

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
