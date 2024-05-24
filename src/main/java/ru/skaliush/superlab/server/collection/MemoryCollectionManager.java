package ru.skaliush.superlab.server.collection;

import ru.skaliush.superlab.common.models.Person;
import ru.skaliush.superlab.common.models.dto.PersonDTO;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.Collectors;

public class MemoryCollectionManager implements CollectionManager {
    private Collection<Person> collection;

    private long lastId = 0;

    public MemoryCollectionManager() {
        this.collection = new HashSet<>();
    }

    public MemoryCollectionManager(Collection<Person> collection) {
        this.collection = collection;
        collection.stream().max(Comparator.comparing(Person::getId))
                .ifPresent(person -> lastId = person.getId());
    }

    public Collection<Person> getCollection() {
        return new HashSet<>(collection);
    }

    public Person createPerson(PersonDTO personDTO) {
        Person person = new Person(generateId(), personDTO);
        collection.add(person);
        return person;
    }

    @Override
    public Person getPersonById(Long id) {
        return null;
    }

    public Person updatePersonById(Long id, PersonDTO personDTO) {
        removePersonById(id);
        Person person = new Person(id, personDTO);
        collection.add(person);
        return person;
    }

    public boolean removePersonById(Long id) {
        int oldSize = collection.size();
        collection = collection.stream().filter(person -> !person.getId().equals(id)).collect(Collectors.toSet());
        return oldSize > collection.size();
    }

    public int clearCollection() {
        int count = collection.size();
        collection = new HashSet<>();
        return count;
    }

    public void addPersonIfMin(Person person) {
        Person minPerson = collection.stream().min(Person::compareTo).orElse(null);
        if (person.compareTo(minPerson) < 0) {
            collection.add(person);
        }
    }

    public int removeGreaterThanHeight(int height) {
        int oldSize = collection.size();
        collection = collection.stream().filter(person -> person.getHeight() <= height).collect(Collectors.toSet());
        return oldSize - collection.size();
    }

    public int removeLowerThanHeight(int height) {
        int oldSize = collection.size();
        collection = collection.stream().filter(person -> person.getHeight() >= height).collect(Collectors.toSet());
        return oldSize - collection.size();
    }

    private Long generateId() {
        return ++lastId;
    }

    public void setLastId(long lastId) {
        this.lastId = lastId;
    }
}
