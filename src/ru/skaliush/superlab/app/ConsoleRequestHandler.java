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
                    boolean resolved = commandResolver.resolve(request);
                    if (!resolved) {
                        System.out.println("404 Not found");
                    }
                } else {
                    System.out.println("Введите нужную команду");
                }
            } catch (StopProgramException e) {
                break;
            }
        }
        System.out.println("Пока");
    }
}
