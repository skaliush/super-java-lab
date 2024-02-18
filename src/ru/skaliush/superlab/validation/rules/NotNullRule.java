package ru.skaliush.superlab.validation.rules;

public class NotNullRule implements Rule {
    public boolean check(String value) {
        return value != null && !value.equals("");
    }

    public String errorMessage() {
        return "Поле не должно быть пустым";
    }
}
