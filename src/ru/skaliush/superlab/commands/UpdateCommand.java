package ru.skaliush.superlab.commands;

import ru.skaliush.superlab.app.ResponseWriter;
import ru.skaliush.superlab.collection.CollectionManager;
import ru.skaliush.superlab.models.dto.PersonDTO;
import ru.skaliush.superlab.validation.rules.LongRule;
import ru.skaliush.superlab.validation.rules.Rule;

import java.util.List;

public class UpdateCommand extends Command {
    public void exec(String argument) {
        ResponseWriter.write(argument);
        Long id = Long.parseLong(argument);
        PersonForm form = new PersonForm();
        PersonDTO personDto = form.askPerson();
        ResponseWriter.write(personDto);
        CollectionManager collectionManager = this.app.getCollectionManager();
        collectionManager.updatePersonById(id, personDto);
        // что если такого ID нет?
    }

    public String getDescription() {
        return "обновить значение элемента коллекции, id которого равен заданному";
    }

    public String getArgumentName() {
        return "id";
    }

    public List<Rule> getArgumentValidationRules() {
        return List.of(new LongRule());
    }
}
