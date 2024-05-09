package ru.skaliush.superlab.server.actions;

import ru.skaliush.superlab.common.network.Request;

public class DeleteAction extends Action<Boolean> {
    public Boolean execute(Request request) {
        Long id = (Long) request.getData();
        boolean removed = this.app.getCollectionManager().removePersonById(id);
        app.save();
        return removed;
    }
}
