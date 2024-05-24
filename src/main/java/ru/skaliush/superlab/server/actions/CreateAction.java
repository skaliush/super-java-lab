package ru.skaliush.superlab.server.actions;

import ru.skaliush.superlab.common.models.Person;
import ru.skaliush.superlab.common.models.dto.PersonDTO;
import ru.skaliush.superlab.common.network.Request;
import ru.skaliush.superlab.server.collection.PostgresCollectionManager;

public class CreateAction extends Action<Person> {
    public Person execute(Request request) {
        PersonDTO personDTO = (PersonDTO) request.getData();
        PostgresCollectionManager collectionManager = this.app.getCollectionManager();
        return collectionManager.createPerson(personDTO);
    }
}
