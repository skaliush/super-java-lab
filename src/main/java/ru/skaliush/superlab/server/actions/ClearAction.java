package ru.skaliush.superlab.server.actions;

import ru.skaliush.superlab.common.network.Request;

public class ClearAction extends Action<Integer> {
    public Integer execute(Request request) {
        int count = this.app.getCollectionManager().clearCollection();
        app.save();
        return count;
    }
}
