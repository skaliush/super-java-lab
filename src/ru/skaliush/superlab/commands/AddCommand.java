package ru.skaliush.superlab.commands;

import ru.skaliush.superlab.PersonDTO;
import ru.skaliush.superlab.app.ResponseWriter;
import ru.skaliush.superlab.collection.CollectionManager;

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
