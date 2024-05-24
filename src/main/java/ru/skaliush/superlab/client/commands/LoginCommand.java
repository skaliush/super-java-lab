package ru.skaliush.superlab.client.commands;

import ru.skaliush.superlab.client.app.ResponseWriter;
import ru.skaliush.superlab.client.app.ValidReader;
import ru.skaliush.superlab.common.models.User;
import ru.skaliush.superlab.common.network.ActionAlias;
import ru.skaliush.superlab.common.network.Request;
import ru.skaliush.superlab.common.network.Response;

public class LoginCommand extends Command {
    public void exec(String argument) {
        ResponseWriter.write("Введите логин");
        String login = ValidReader.readValidValue();
        ResponseWriter.write("Введите пароль");
        String password = ValidReader.readValidValue();
        User user = new User(login, password);
        Request request = new Request(ActionAlias.LOGIN, user);
        Response response = app.getRequestSender().send(request);
        boolean isLoggedIn = (Boolean) response.getData();
        if (isLoggedIn) {
            app.setUser(user);
            ResponseWriter.write("Успешный вход");
        } else {
            ResponseWriter.write("Неверный логин или пароль. Попробуйте ещё раз.");
        }
    }

    public String getDescription() {
        return "вход в аккаунт";
    }

    public boolean needToAuthorize() {
        return false;
    }
}
