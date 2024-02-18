package ru.skaliush.superlab.validation;

import ru.skaliush.superlab.validation.rules.Rule;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    public static void validate(String value, List<Rule> rules) {
        List<String> errors = new ArrayList<>();
        for (Rule rule : rules) {
            if (!rule.check(value)) {
                errors.add(rule.errorMessage());
            }
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
