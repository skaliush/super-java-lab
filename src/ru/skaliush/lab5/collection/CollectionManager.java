package ru.skaliush.lab5.collection;

import ru.skaliush.lab5.models.Person;

import java.util.HashSet;
import java.util.Set;

public class CollectionManager {
    private final Set<Person> collection = new HashSet<>();

    public Set<Person> getCollection() {
        return new HashSet<>(collection);
    }

    public void addElement(Person element) {
        // разобраться с ID
        collection.add(element);
    }

    public void updateElementById(Long id, Person element) {
        // collection.stream().filter(person -> person.getId().equals(id))
    }
}
