package ru.skaliush.superlab.storage;

import ru.skaliush.superlab.models.Location;
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
        List<String> header = List.of("id", "name", "creationDate", "height", "eyeColor", "hairColor", "nationality", "location_name", "location_x", "location_y", "location_z");
        rows.add(new Row(header));
        for (Person person : collection) {
            Location location = person.getLocation();
            List<String> fields = new ArrayList<>();
            fields.add(person.getId().toString());
            fields.add(person.getName());
            fields.add(person.getCreationDate().toString());
            fields.add(person.getHeight().toString());
            fields.add(person.getEyeColor().toString());
            fields.add(person.getHairColor().toString());
            fields.add(person.getNationality().toString());
            fields.add(location != null ? location.getName() : null);
            fields.add(location != null ? location.getX().toString() : null);
            fields.add(location != null ? location.getY().toString() : null);
            fields.add(location != null ? location.getZ().toString() : null);

            rows.add(new Row(fields));
        }
        CsvWriter csvWriter = new CsvWriter();
        csvWriter.write(fileName, rows);
    }
}
