package ru.skaliush.superlab.server.storage;

import ru.skaliush.superlab.common.models.Color;
import ru.skaliush.superlab.common.models.Country;
import ru.skaliush.superlab.common.models.Location;
import ru.skaliush.superlab.common.models.Person;
import ru.skaliush.superlab.common.models.dto.PersonDTO;
import ru.skaliush.superlab.server.storage.csv.CsvParser;
import ru.skaliush.superlab.server.storage.csv.Row;

import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class StorageReader {
    private final String fileName;

    public StorageReader(String fileName) {
        this.fileName = fileName;
    }

    private final List<String> fieldNames = List.of("id", "name", "creationDate", "height", "eyeColor", "hairColor", "nationality", "location_name", "location_x", "location_y", "location_z");

    public Collection<Person> read() {
        CsvParser parser = new CsvParser();
        List<Row> rows = parser.parse(fileName);
        if (rows.isEmpty()) {
            throw new InvalidStorageFormatException("Файл хранилища пуст");
        }
        Row header = rows.get(0);
        if (!header.cells().equals(fieldNames)) {
            throw new InvalidStorageFormatException("Неверный формат файла хранилища: неправильный заголовок");
        }
        Collection<Person> personCollection = new HashSet<>();
        if (rows.size() > 1) {
            for (int i = 1; i < rows.size(); i++) {
                Row row = rows.get(i);
                Person person = mapRowToPerson(row);
                personCollection.add(person);
            }
        }
        return personCollection;
    }

    private Person mapRowToPerson(Row row) {
        List<String> cells = row.cells();
        if (cells.size() != fieldNames.size()) {
            throw new InvalidStorageFormatException("Неверный формат файла хранилища: неверное кол-во столбцов");
        }
        PersonDTO personDTO = new PersonDTO();
        try {
            Long id = Long.parseLong(cells.get(0));
            personDTO.setName(cells.get(1));
            ZonedDateTime creationDate = ZonedDateTime.parse(cells.get(2));
            personDTO.setHeight(Integer.parseInt(cells.get(3)));
            personDTO.setEyeColor(Color.valueOf(cells.get(4)));
            personDTO.setHairColor(Color.valueOf(cells.get(5)));
            personDTO.setNationality(Country.valueOf(cells.get(6)));
            if (!cells.get(8).equals("") && !cells.get(9).equals("") && !cells.get(10).equals("")) {
                Location location = new Location(
                        Float.parseFloat(cells.get(8)),
                        Float.parseFloat(cells.get(9)),
                        Float.parseFloat(cells.get(10)),
                        cells.get(7).equals("") ? null : cells.get(7)
                );
                personDTO.setLocation(location);
            }
            return new Person(id, personDTO, creationDate);
        } catch (IllegalArgumentException | DateTimeParseException e) {
            throw new InvalidStorageFormatException("Неверный формат файла хранилища: не совпадает тип данных. Сообщение ошибки при парсинге: " + e.getMessage());
        }
    }
}
