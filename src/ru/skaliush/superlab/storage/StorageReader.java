package ru.skaliush.superlab.storage;

import ru.skaliush.superlab.models.Person;
import ru.skaliush.superlab.storage.csv.CsvParser;
import ru.skaliush.superlab.storage.csv.Row;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class StorageReader {
    public Collection<Person> read() {
        String fileName = "people.csv";
        CsvParser parser = new CsvParser();
        List<Row> rows = parser.parse(fileName);
        for (Row row : rows) {
            System.out.println(row);
        }
        // List<String> fieldsToSave = List.of("id", "name", "creationDate", "height", "eyeColor", "hairColor", "nationality", "location");
        return new HashSet<>();
    }
}
