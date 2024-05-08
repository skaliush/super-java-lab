package ru.skaliush.superlab.client.validation.rules;

public class IntRule implements Rule {
    public boolean check(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String errorMessage() {
        return "Поле должно быть целым числом";
    }
}
