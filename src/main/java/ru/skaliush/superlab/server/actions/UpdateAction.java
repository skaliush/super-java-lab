package ru.skaliush.superlab.server.actions;

import ru.skaliush.superlab.common.models.Person;
import ru.skaliush.superlab.common.models.dto.PersonDTO;
import ru.skaliush.superlab.common.network.Request;
import ru.skaliush.superlab.server.collection.CollectionManager;

public class UpdateAction extends Action<Person> {
    public Person execute(Request request) {
        PersonDTO personDTO = (PersonDTO) request.getData();
        CollectionManager collectionManager = this.app.getCollectionManager();
        Person person = collectionManager.updatePersonById(personDTO.getId(), personDTO);
        app.save();
        return person;
    }
}
