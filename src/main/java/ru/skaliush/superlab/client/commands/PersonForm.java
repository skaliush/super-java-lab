package ru.skaliush.superlab.client.commands;

import ru.skaliush.superlab.client.app.ResponseWriter;
import ru.skaliush.superlab.client.app.ValidReader;
import ru.skaliush.superlab.client.validation.rules.*;
import ru.skaliush.superlab.common.models.Color;
import ru.skaliush.superlab.common.models.Country;
import ru.skaliush.superlab.common.models.Location;
import ru.skaliush.superlab.common.models.dto.PersonDTO;

import java.util.List;

public class PersonForm {
    public PersonDTO askPerson() {
        PersonDTO personDto = new PersonDTO();
        askName(personDto);
        askHeight(personDto);
        askHairColor(personDto);
        askEyeColor(personDto);
        askNationality(personDto);
        askLocation(personDto);
        return personDto;
    }

    private void askName(PersonDTO personDto) {
        ResponseWriter.write("Введите имя");
        String name = ValidReader.readValidValue();
        personDto.setName(name);
    }

    private void askHeight(PersonDTO personDto) {
        ResponseWriter.write("Введите рост");
        String height = ValidReader.readValidValue(List.of(new IntRule(), new MinRule(0), new MaxRule(300)));
        personDto.setHeight(Integer.parseInt(height));
    }

    private void askHairColor(PersonDTO personDto) {
        ResponseWriter.write("Выберите цвет волос:");
        for (int i = 0; i < Color.values().length; i++) {
            ResponseWriter.write(" • " + i + " - " + Color.values()[i]);
        }
        String hairColorCode = ValidReader.readValidValue(List.of(new IntRule(), new EnumRule(Color.values())));
        personDto.setHairColor(Color.values()[Integer.parseInt(hairColorCode)]);
    }

    private void askEyeColor(PersonDTO personDto) {
        ResponseWriter.write("Выберите цвет глаз:");
        for (int i = 0; i < Color.values().length; i++) {
            ResponseWriter.write(" • " + i + " - " + Color.values()[i]);
        }
        String eyeColorCode = ValidReader.readValidValue(List.of(new IntRule(), new EnumRule(Color.values())));
        personDto.setEyeColor(Color.values()[Integer.parseInt(eyeColorCode)]);
    }

    private void askNationality(PersonDTO personDto) {
        ResponseWriter.write("Выберите национальность:");
        for (int i = 0; i < Country.values().length; i++) {
            ResponseWriter.write(" • " + i + " - " + Country.values()[i]);
        }
        String nationalityCode = ValidReader.readValidValue(List.of(new IntRule(), new EnumRule(Country.values())));
        personDto.setNationality(Country.values()[Integer.parseInt(nationalityCode)]);
    }

    private void askLocation(PersonDTO personDto) {
        ResponseWriter.write("Вы хотите указать локацию (Y/[N])?");
        String needLocationStr = ValidReader.readValidValue(List.of(new BoolRule()), true);
        needLocationStr = needLocationStr == null ? "n" : needLocationStr;
        boolean needLocation = needLocationStr.equals("y");
        if (needLocation) {
            ResponseWriter.write("Введите имя локации (может быть пустым)");
            String locationName = ValidReader.readValidValue(List.of(), true);
            ResponseWriter.write("Введите координату X");
            float x = Float.parseFloat(ValidReader.readValidValue(List.of(new FloatRule())));
            ResponseWriter.write("Введите координату Y");
            float y = Float.parseFloat(ValidReader.readValidValue(List.of(new FloatRule())));
            ResponseWriter.write("Введите координату Z");
            float z = Float.parseFloat(ValidReader.readValidValue(List.of(new FloatRule())));
            Location location = new Location(x, y, z, locationName);
            personDto.setLocation(location);
        }
    }
}
