package ru.skaliush.superlab.client.app;

import java.io.File;

public class ResponseWriter {
    public static void write(Object output) {
        ClientAppContainer appContainer = ClientAppContainer.getInstance();
        if (appContainer.isInteractiveMode()) {
            System.out.print("[script] ");
        }
        for (File script : appContainer.getScriptsStack()) {
            System.out.print(script.getName() + " > ");
        }
        System.out.println(output);
    }
}
