package ru.skaliush.lab5.app;

import java.util.Scanner;

public class ConsoleRequestHandler {
    public void handle() {
        System.out.println("Привет");
        Scanner scanner = new Scanner(System.in);
        CommandResolver commandResolver = AppContainer.getInstance().getCommandResolver();
        while (true) {
            String request = scanner.nextLine().trim().toLowerCase();
            if (!request.equals("")) {
                try {
                    if (!commandResolver.resolve(request)) {
                        System.out.println("404 Not found");
                    }
                } catch (StopProgramException e) {
                    System.out.println("Пока");
                    break;
                }
            } else {
                System.out.println("Введите нужную команду");
            }
        }
    }
}
