package ru.skaliush.superlab.storage;

import ru.skaliush.superlab.models.Person;
import ru.skaliush.superlab.storage.csv.CsvWriter;
import ru.skaliush.superlab.storage.csv.Row;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StorageSaver {
    public void save(Collection<Person> collection) {
        String fileName = "people.csv";
        List<Row> rows = new ArrayList<>();
        List<String> header = List.of("id", "name", "creationDate", "height", "eyeColor", "hairColor", "nationality", "location");
        rows.add(new Row(header));
        for (Person person : collection) {
            List<String> fields = List.of(
                    person.getId().toString(),
                    person.getName(),
                    person.getCreationDate().toString(),
                    person.getHeight().toString(),
                    person.getEyeColor().toString(),
                    person.getHairColor().toString(),
                    person.getNationality().toString(),
                    person.getLocation().toString()
            );
            rows.add(new Row(fields));
        }
        CsvWriter csvWriter = new CsvWriter();
        csvWriter.write(fileName, rows);
    }
}
