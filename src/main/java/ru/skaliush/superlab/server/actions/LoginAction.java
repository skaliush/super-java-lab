package ru.skaliush.superlab.server.actions;

import ru.skaliush.superlab.common.models.User;
import ru.skaliush.superlab.common.network.Request;

public class LoginAction extends Action<Boolean> {
    public Boolean execute(Request request) {
        User user = (User) request.getData();
        return app.getUserManager().checkUserLogin(user);
    }
}
