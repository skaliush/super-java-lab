package ru.skaliush.superlab.client.validation.rules;

public class FloatRule implements Rule {
    public boolean check(String value) {
        try {
            Float.parseFloat(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String errorMessage() {
        return "Поле должно быть числом";
    }
}
