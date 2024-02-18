package ru.skaliush.superlab.commands;

import ru.skaliush.superlab.PersonDTO;

public class UpdateCommand extends Command {
    public void exec() {
        PersonForm form = new PersonForm();
        PersonDTO personDto = form.askPerson();
    }

    public String getDescription() {
        return "обновить значение элемента коллекции, id которого равен заданному";
    }

    public String getAlias() {
        return "update {id}";
    }
}
