package ru.skaliush.superlab.server.actions;

import ru.skaliush.superlab.common.models.Person;
import ru.skaliush.superlab.common.network.Request;

import java.util.Collection;

public class ShowAction extends Action<Collection<Person>> {
    public Collection<Person> execute(Request request) {
        return this.app.getCollectionManager().getCollection();
    }
}
