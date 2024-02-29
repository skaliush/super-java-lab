package ru.skaliush.superlab.commands;

import ru.skaliush.superlab.app.AppContainer;
import ru.skaliush.superlab.app.LineReader;
import ru.skaliush.superlab.app.ResponseWriter;
import ru.skaliush.superlab.app.StopProgramException;
import ru.skaliush.superlab.models.Color;
import ru.skaliush.superlab.models.Country;
import ru.skaliush.superlab.models.Location;
import ru.skaliush.superlab.models.dto.PersonDTO;
import ru.skaliush.superlab.validation.ValidationException;
import ru.skaliush.superlab.validation.Validator;
import ru.skaliush.superlab.validation.rules.*;

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
        String name = readValidValue();
        personDto.setName(name);
    }

    private void askHeight(PersonDTO personDto) {
        ResponseWriter.write("Введите рост");
        String height = readValidValue(List.of(new IntRule(), new MinRule(0), new MaxRule(300)));
        personDto.setHeight(Integer.parseInt(height));
    }

    private void askHairColor(PersonDTO personDto) {
        ResponseWriter.write("Выберите цвет волос:");
        for (int i = 0; i < Color.values().length; i++) {
            ResponseWriter.write(" • " + i + " - " + Color.values()[i]);
        }
        String hairColorCode = readValidValue(List.of(new IntRule(), new EnumRule(Color.values())));
        personDto.setHairColor(Color.values()[Integer.parseInt(hairColorCode)]);
    }

    private void askEyeColor(PersonDTO personDto) {
        ResponseWriter.write("Выберите цвет глаз:");
        for (int i = 0; i < Color.values().length; i++) {
            ResponseWriter.write(" • " + i + " - " + Color.values()[i]);
        }
        String eyeColorCode = readValidValue(List.of(new IntRule(), new EnumRule(Color.values())));
        personDto.setEyeColor(Color.values()[Integer.parseInt(eyeColorCode)]);
    }

    private void askNationality(PersonDTO personDto) {
        ResponseWriter.write("Выберите национальность:");
        for (int i = 0; i < Country.values().length; i++) {
            ResponseWriter.write(" • " + i + " - " + Country.values()[i]);
        }
        String nationalityCode = readValidValue(List.of(new IntRule(), new EnumRule(Country.values())));
        personDto.setNationality(Country.values()[Integer.parseInt(nationalityCode)]);
    }

    private void askLocation(PersonDTO personDto) {
        ResponseWriter.write("Вы хотите указать локацию (Y/[N])?");
        String needLocationStr = readValidValue(List.of(new BoolRule()), true);
        needLocationStr = needLocationStr == null ? "n" : needLocationStr;
        boolean needLocation = needLocationStr.equals("y");
        if (needLocation) {
            ResponseWriter.write("Введите имя локации (может быть пустым)");
            String locationName = readValidValue(List.of(), true);
            ResponseWriter.write("Введите координату X");
            float x = Float.parseFloat(readValidValue(List.of(new FloatRule())));
            ResponseWriter.write("Введите координату Y");
            float y = Float.parseFloat(readValidValue(List.of(new FloatRule())));
            ResponseWriter.write("Введите координату Z");
            float z = Float.parseFloat(readValidValue(List.of(new FloatRule())));
            Location location = new Location(x, y, z, locationName);
            personDto.setLocation(location);
        }
    }

    private String readValidValue(List<Rule> rules, boolean nullable) {
        AppContainer appContainer = AppContainer.getInstance();
        LineReader reader = appContainer.getRequestReader();
        while (true) {
            String input = reader.nextLine().trim().toLowerCase();
            if (nullable && input.equals("")) {
                return null;
            }
            try {
                Validator.validate(input, rules);
                return input;
            } catch (ValidationException e) {
                if (appContainer.isInteractiveMode()) {
                    throw new StopProgramException();
                }
                for (String errorMsg : e.getErrors()) {
                    ResponseWriter.write(" • " + errorMsg);
                }
            }
        }
    }

    private String readValidValue(List<Rule> rules) {
        return readValidValue(rules, false);
    }

    private String readValidValue() {
        return readValidValue(List.of(), false);
    }
}
