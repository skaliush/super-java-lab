package ru.skaliush.superlab.commands;

import ru.skaliush.superlab.app.EndOfLineException;
import ru.skaliush.superlab.app.LineReader;
import ru.skaliush.superlab.app.ResponseWriter;
import ru.skaliush.superlab.app.StopProgramException;
import ru.skaliush.superlab.resolver.exceptions.CommandResolverException;
import ru.skaliush.superlab.validation.rules.FileRule;
import ru.skaliush.superlab.validation.rules.Rule;

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
        LineReader oldRequestReader = this.app.getRequestReader();

        try (FileInputStream inputStream = new FileInputStream(scriptFile)) {
            LineReader lineReader = new LineReader(inputStream);
            this.app.setRequestReader(lineReader);
            while (lineReader.hasNextLine()) {
                try {
                    String request = lineReader.nextLine().trim().toLowerCase();
                    if (!request.equals("")) {
                        try {
                            this.app.getCommandResolver().resolve(request);
                        } catch (CommandResolverException e) {
                            ResponseWriter.write(e.getMessage());
                            break;
                        }
                    }
                } catch (StopProgramException | EndOfLineException e) {
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.app.setRequestReader(oldRequestReader);
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
