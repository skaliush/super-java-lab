package ru.skaliush.superlab.validation.rules;

public class MinRule implements Rule {
    private final double minValue;

    public MinRule(double minValue) {
        this.minValue = minValue;
    }

    public boolean check(String value) {
        try {
            return Double.parseDouble(value) >= minValue;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String errorMessage() {
        return "Поле должно быть не меньше " + minValue;
    }
}
