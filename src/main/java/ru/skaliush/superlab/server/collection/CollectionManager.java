package ru.skaliush.superlab.server.collection;

import ru.skaliush.superlab.common.models.Person;
import ru.skaliush.superlab.common.models.dto.PersonDTO;

import java.util.Collection;

public interface CollectionManager {
    Collection<Person> getCollection();

    Person createPerson(PersonDTO personDTO);

    Person updatePersonById(Long id, PersonDTO personDTO);

    boolean removePersonById(Long id);

    int clearCollection();

    void addPersonIfMin(Person person);

    int removeGreaterThanHeight(int height);

    int removeLowerThanHeight(int height);
}
