package ru.skaliush.superlab.validation.rules;

public class MaxRule implements Rule {
    private final double maxValue;

    public MaxRule(double maxValue) {
        this.maxValue = maxValue;
    }

    public boolean check(String value) {
        try {
            return Double.parseDouble(value) <= maxValue;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String errorMessage() {
        return "Поле должно быть не больше " + maxValue;
    }
}