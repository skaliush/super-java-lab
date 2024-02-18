package ru.skaliush.superlab.commands;

import ru.skaliush.superlab.PersonDTO;
import ru.skaliush.superlab.app.AppContainer;
import ru.skaliush.superlab.app.LineReader;
import ru.skaliush.superlab.app.ResponseWriter;
import ru.skaliush.superlab.app.StopProgramException;
import ru.skaliush.superlab.models.Color;
import ru.skaliush.superlab.models.Country;
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
        return personDto;
    }

    private void askNationality(PersonDTO personDto) {
        ResponseWriter.write("Выберите национальность:");
        for (int i = 0; i < Country.values().length; i++) {
            ResponseWriter.write(" • " + i + " - " + Country.values()[i]);
        }
        String nationalityCode = readValidValue(List.of(new NotNullRule(), new IntRule(), new EnumRule(Country.values())));
        personDto.setNationality(Country.values()[Integer.parseInt(nationalityCode)]);
    }

    private void askEyeColor(PersonDTO personDto) {
        ResponseWriter.write("Выберите цвет глаз:");
        for (int i = 0; i < Color.values().length; i++) {
            ResponseWriter.write(" • " + i + " - " + Color.values()[i]);
        }
        String eyeColorCode = readValidValue(List.of(new NotNullRule(), new IntRule(), new EnumRule(Color.values())));
        personDto.setEyeColor(Color.values()[Integer.parseInt(eyeColorCode)]);
    }

    private void askHairColor(PersonDTO personDto) {
        ResponseWriter.write("Выберите цвет волос:");
        for (int i = 0; i < Color.values().length; i++) {
            ResponseWriter.write(" • " + i + " - " + Color.values()[i]);
        }
        String hairColorCode = readValidValue(List.of(new NotNullRule(), new IntRule(), new EnumRule(Color.values())));
        personDto.setHairColor(Color.values()[Integer.parseInt(hairColorCode)]);
    }

    private void askHeight(PersonDTO personDto) {
        ResponseWriter.write("Введите рост");
        String height = readValidValue(List.of(new NotNullRule(), new IntRule(), new MinRule(0), new MaxRule(300)));
        personDto.setHeight(Integer.parseInt(height));
    }

    private void askName(PersonDTO personDto) {
        ResponseWriter.write("Введите имя");
        String name = readValidValue(List.of(new NotNullRule()));
        personDto.setName(name);
    }

    protected String readValidValue(List<Rule> rules) {
        LineReader reader = AppContainer.getInstance().getRequestReader();
        while (true) {
            String input = reader.nextLine().trim().toLowerCase();
            try {
                Validator.validate(input, rules);
                return input;
            } catch (ValidationException e) {
                if (!AppContainer.getInstance().isInteractiveMode()) {
                    throw new StopProgramException();
                }
                for (String errorMsg : e.getErrors()) {
                    ResponseWriter.write(" • " + errorMsg);
                }
            }
        }
    }
}
