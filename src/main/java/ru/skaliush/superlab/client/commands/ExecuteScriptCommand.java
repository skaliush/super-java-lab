package ru.skaliush.superlab.client.commands;

import ru.skaliush.superlab.client.app.EndOfFileException;
import ru.skaliush.superlab.client.app.LineReader;
import ru.skaliush.superlab.client.app.ResponseWriter;
import ru.skaliush.superlab.client.app.StopProgramException;
import ru.skaliush.superlab.client.resolver.exceptions.CommandResolverException;
import ru.skaliush.superlab.client.validation.rules.FileRule;
import ru.skaliush.superlab.client.validation.rules.Rule;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class ExecuteScriptCommand extends Command {
    public void exec(String argument) {
        File scriptFile = new File(argument);
        if (this.app.getScriptsStack().contains(scriptFile)) {
            ResponseWriter.write("Попытка вызвать скрипт, который уже исполняется.");
            return;
        }
        this.app.getScriptsStack().add(scriptFile);
        LineReader oldPromptReader = this.app.getPromptReader();

        try (FileInputStream inputStream = new FileInputStream(scriptFile)) {
            LineReader lineReader = new LineReader(inputStream);
            this.app.setPromptReader(lineReader);
            while (lineReader.hasNextLine()) {
                try {
                    String prompt = lineReader.nextLine().trim().toLowerCase();
                    if (!prompt.equals("")) {
                        try {
                            this.app.getCommandResolver().resolve(prompt);
                        } catch (CommandResolverException e) {
                            ResponseWriter.write(e.getMessage());
                            break;
                        }
                    }
                } catch (StopProgramException | EndOfFileException e) {
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.app.setPromptReader(oldPromptReader);
        this.app.getScriptsStack().removeLast();
    }

    public String getDescription() {
        return "считать и исполнить скрипт из указанного файла";
    }

    public String getArgumentName() {
        return "file_name";
    }

    public List<Rule> getArgumentValidationRules() {
        return List.of(new FileRule());
    }
}
