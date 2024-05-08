package ru.skaliush.superlab.server.actions;

import ru.skaliush.superlab.common.models.Person;
import ru.skaliush.superlab.common.models.dto.PersonDTO;
import ru.skaliush.superlab.common.request.Request;
import ru.skaliush.superlab.server.app.ServerAppContainer;
import ru.skaliush.superlab.server.collection.CollectionManager;

public class CreateAction implements Action<Person> {
    public Person execute(Request request) {
        PersonDTO personDTO = (PersonDTO) request.getData();
        ServerAppContainer app = ServerAppContainer.getInstance();
        CollectionManager collectionManager = app.getCollectionManager();
        return collectionManager.createPerson(personDTO);
    }
}
