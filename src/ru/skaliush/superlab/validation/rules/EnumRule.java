package ru.skaliush.superlab.validation.rules;

import java.util.ArrayList;
import java.util.List;

public class EnumRule implements Rule {
    private final Enum<?>[] allowedValues;

    public EnumRule(Enum<?>[] allowedValues) {
        this.allowedValues = allowedValues;
    }

    public boolean check(String value) {
        try {
            return Integer.parseInt(value) < allowedValues.length;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String errorMessage() {
        List<String> parts = new ArrayList<>();
        for (int i = 0; i < allowedValues.length; i++) {
            parts.add(allowedValues[i] + " (" + i + ")");
        }
        return "Поле должно принимать одно из следующих значений: " + String.join(", ", parts);
    }
}
