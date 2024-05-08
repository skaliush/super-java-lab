package ru.skaliush.superlab.client.validation.rules;

public interface Rule {
    boolean check(String value);

    String errorMessage();
}
