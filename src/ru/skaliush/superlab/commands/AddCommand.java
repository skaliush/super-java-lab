package ru.skaliush.superlab.commands;

import ru.skaliush.superlab.app.ResponseWriter;
import ru.skaliush.superlab.collection.CollectionManager;
import ru.skaliush.superlab.dto.PersonDTO;

public class AddCommand extends Command {
    public void exec() {
        PersonForm form = new PersonForm();
        PersonDTO personDto = form.askPerson();
        ResponseWriter.write(personDto);
        CollectionManager collectionManager = this.app.getCollectionManager();
        collectionManager.addPerson(personDto);
    }

    public String getDescription() {
        return "добавить новый элемент в коллекцию";
    }

    public String getAlias() {
        return "add";
    }
}
