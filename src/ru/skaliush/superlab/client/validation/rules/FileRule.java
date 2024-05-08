package ru.skaliush.superlab.client.validation.rules;

import java.io.File;
import java.nio.file.Path;

public class FileRule implements Rule {
    public boolean check(String value) {
        File file = Path.of(value).toFile();
        return file.exists() && file.canRead();
    }

    public String errorMessage() {
        return "Поле должно быть файлом, доступным для чтения";
    }
}
