package ru.skaliush.superlab.server.actions;

import ru.skaliush.superlab.common.request.Request;
import ru.skaliush.superlab.server.app.ServerAppContainer;

public class ClearAction implements Action<Integer> {
    public Integer execute(Request request) {
        ServerAppContainer app = ServerAppContainer.getInstance();
        return app.getCollectionManager().clearCollection();
    }
}
