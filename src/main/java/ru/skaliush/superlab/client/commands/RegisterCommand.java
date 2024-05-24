package ru.skaliush.superlab.client.commands;

import ru.skaliush.superlab.client.app.ResponseWriter;
import ru.skaliush.superlab.client.app.ValidReader;
import ru.skaliush.superlab.common.models.User;
import ru.skaliush.superlab.common.network.ActionAlias;
import ru.skaliush.superlab.common.network.Request;
import ru.skaliush.superlab.common.network.Response;

public class RegisterCommand extends Command {
    public void exec(String argument) {
        ResponseWriter.write("Введите логин");
        String login = ValidReader.readValidValue();
        ResponseWriter.write("Введите пароль");
        String password = ValidReader.readValidValue();
        User user = new User(login, password);
        Request request = new Request(ActionAlias.REGISTER, user);
        Response response = app.getRequestSender().send(request);
        boolean registered = (Boolean) response.getData();
        if (registered) {
            app.setUser(user);
            ResponseWriter.write("Успешная регистрация");
        } else {
            ResponseWriter.write("Похоже такой логин уже занят");
        }
    }

    public String getDescription() {
        return null;
    }

    public boolean needToAuthorize() {
        return false;
    }
}
