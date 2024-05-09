package ru.skaliush.superlab.server.storage;

import ru.skaliush.superlab.common.models.Location;
import ru.skaliush.superlab.common.models.Person;
import ru.skaliush.superlab.server.storage.csv.CsvWriter;
import ru.skaliush.superlab.server.storage.csv.Row;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StorageSaver {
    private final String fileName;

    public StorageSaver(String fileName) {
        this.fileName = fileName;
    }

    public void save(Collection<Person> collection) {
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
