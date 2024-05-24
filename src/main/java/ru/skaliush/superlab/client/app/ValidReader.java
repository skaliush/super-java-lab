package ru.skaliush.superlab.client.app;

import ru.skaliush.superlab.client.validation.ValidationException;
import ru.skaliush.superlab.client.validation.Validator;
import ru.skaliush.superlab.client.validation.rules.Rule;

import java.util.List;

public class ValidReader {
    public static String readValidValue(List<Rule> rules, boolean nullable) {
        ClientAppContainer appContainer = ClientAppContainer.getInstance();
        LineReader reader = appContainer.getPromptReader();
        while (true) {
            String input = reader.nextLine().trim().toLowerCase();
            if (nullable && input.equals("")) {
                return null;
            }
            try {
                Validator.validate(input, rules);
                return input;
            } catch (ValidationException e) {
                if (appContainer.isInteractiveMode()) {
                    throw new StopProgramException();
                }
                for (String errorMsg : e.getErrors()) {
                    ResponseWriter.write(" • " + errorMsg);
                }
            }
        }
    }

    public static String readValidValue(List<Rule> rules) {
        return readValidValue(rules, false);
    }

    public static String readValidValue() {
        return readValidValue(List.of(), false);
    }
}
