package ru.skaliush.superlab.server.actions;

import ru.skaliush.superlab.common.request.Request;
import ru.skaliush.superlab.server.app.ServerAppContainer;

public class DeleteAction implements Action<Boolean> {
    public Boolean execute(Request request) {
        Long id = (Long) request.getData();
        ServerAppContainer app = ServerAppContainer.getInstance();
        return app.getCollectionManager().removePersonById(id);
    }
}
