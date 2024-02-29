package ru.skaliush.superlab.app;

public class ConsoleRequestHandler {
    public void handle() {
        AppContainer app = AppContainer.getInstance();
        LineReader reader = app.getRequestReader();
        CommandResolver commandResolver = app.getCommandResolver();
        System.out.println("Привет");
        while (true) {
            try {
                String request = reader.nextLine().trim().toLowerCase();
                if (!request.equals("")) {
                    try {
                        commandResolver.resolve(request);
                    } catch (CommandNotFoundException e) {
                        System.out.println("404 Not found");
                    } catch (ArgumentMissedException e) {
                        System.out.println("Пропущен обязательный аргумент " + e.getArgumentName());
                    } catch (InvalidArgumentException e) {
                        System.out.println("Неверный формат аргумента " + e.getArgumentName() + ":");
                        for (String errorMsg : e.getCause().getErrors()) {
                            System.out.println(" • " + errorMsg);
                        }
                    }
                } else {
                    System.out.println("Введите нужную команду");
                }
            } catch (StopProgramException | EndOfLineException e) {
                break;
            }
        }
        System.out.println("Пока");
    }
}
