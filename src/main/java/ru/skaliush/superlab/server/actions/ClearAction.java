package ru.skaliush.superlab.server.actions;

import ru.skaliush.superlab.common.network.Request;

public class ClearAction extends Action<Integer> {
    public Integer execute(Request request) {
        return this.app.getCollectionManager().clearCollection(request.getUser());
    }
}
