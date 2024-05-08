package ru.skaliush.superlab.server.actions;

import ru.skaliush.superlab.common.models.Person;
import ru.skaliush.superlab.common.request.Request;
import ru.skaliush.superlab.server.app.ServerAppContainer;

import java.util.Collection;

public class ShowAction implements Action<Collection<Person>> {
    public Collection<Person> execute(Request request) {
        ServerAppContainer appContainer = ServerAppContainer.getInstance();
        return appContainer.getCollectionManager().getCollection();
    }
}
