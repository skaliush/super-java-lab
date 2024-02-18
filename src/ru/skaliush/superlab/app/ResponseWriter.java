package ru.skaliush.superlab.app;

public class ResponseWriter {
    public static void write(Object output) {
        if (AppContainer.getInstance().isInteractiveMode()) {
            System.out.println(output);
        }
    }
}
