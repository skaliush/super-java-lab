package ru.skaliush.superlab.validation.rules;

public interface Rule {
    boolean check(String value);

    String errorMessage();
}
