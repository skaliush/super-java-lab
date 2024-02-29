package ru.skaliush.superlab.app;

import ru.skaliush.superlab.resolver.CommandResolver;
import ru.skaliush.superlab.resolver.exceptions.ArgumentMissedException;
import ru.skaliush.superlab.resolver.exceptions.CommandNotFoundException;
import ru.skaliush.superlab.resolver.exceptions.InvalidArgumentException;

public class ConsoleRequestHandler {
    public void handle() {
        AppContainer app = AppContainer.getInstance();
        LineReader reader = app.getRequestReader();
        CommandResolver commandResolver = app.getCommandResolver();
        ResponseWriter.write("Привет");
        while (true) {
            try {
                String request = reader.nextLine().trim().toLowerCase();
                if (!request.equals("")) {
                    try {
                        commandResolver.resolve(request);
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
            } catch (StopProgramException | EndOfLineException e) {
                break;
            }
        }
        ResponseWriter.write("Пока");
    }
}
