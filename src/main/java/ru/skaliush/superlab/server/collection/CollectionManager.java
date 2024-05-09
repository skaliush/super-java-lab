package ru.skaliush.superlab.server.collection;

import ru.skaliush.superlab.common.models.Person;
import ru.skaliush.superlab.common.models.dto.PersonDTO;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class CollectionManager {
    private final Collection<Person> collection;

    private long lastId = 0;

    public CollectionManager() {
        this.collection = new HashSet<>();
    }

    public CollectionManager(Collection<Person> collection) {
        this.collection = collection;
        for (Person person : collection) {
            lastId = Long.max(lastId, person.getId());
        }
    }

    public Collection<Person> getCollection() {
        return new HashSet<>(collection);
    }

    public Person createPerson(PersonDTO personDTO) {
        Person person = new Person(generateId(), personDTO);
        collection.add(person);
        return person;
    }

    public Person updatePersonById(Long id, PersonDTO personDTO) {
        removePersonById(id);
        Person person = new Person(id, personDTO);
        collection.add(person);
        return person;
    }

    public boolean removePersonById(Long id) {
        // collection.stream().filter(person -> !person.getId().equals(id))
        return collection.removeIf(person -> person.getId().equals(id));
    }

    public int clearCollection() {
        int count = collection.size();
        collection.clear();
        return count;
    }

    public void addPersonIfMin(Person person) {
        Person minPerson = Collections.min(collection);
        if (person.compareTo(minPerson) < 0) {
            collection.add(person);
        }
    }

    public void removeGreaterThanHeight(int height) {
        collection.removeIf(person -> person.getHeight() > height);
    }

    public void removeLowerThanHeight(int height) {
        collection.removeIf(person -> person.getHeight() < height);
    }

    private Long generateId() {
        return ++lastId;
    }

    public void setLastId(long lastId) {
        this.lastId = lastId;
    }
}
