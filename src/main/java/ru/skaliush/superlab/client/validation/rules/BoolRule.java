package ru.skaliush.superlab.client.validation.rules;

public class BoolRule implements Rule {
    public boolean check(String value) {
        return value.equals("y") || value.equals("n");
    }

    public String errorMessage() {
        return "Введите Y или N";
    }
}
