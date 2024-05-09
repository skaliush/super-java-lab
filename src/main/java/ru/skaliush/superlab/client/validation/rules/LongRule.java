package ru.skaliush.superlab.client.validation.rules;

public class LongRule implements Rule {
    public boolean check(String value) {
        try {
            Long.parseLong(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String errorMessage() {
        return "Поле должно быть целым числом";
    }
}
