package ru.skaliush.superlab.client.commands;

import ru.skaliush.superlab.client.app.ResponseWriter;
import ru.skaliush.superlab.client.validation.rules.LongRule;
import ru.skaliush.superlab.client.validation.rules.Rule;
import ru.skaliush.superlab.common.network.ActionAlias;
import ru.skaliush.superlab.common.network.Request;
import ru.skaliush.superlab.common.network.Response;

import java.util.List;

public class RemoveByIdCommand extends Command {
    public void exec(String argument) {
        Long id = Long.parseLong(argument);
        Request request = new Request(ActionAlias.REMOVE, id);
        Response response = this.app.getRequestSender().send(request);
        boolean removed = (boolean) response.getData();
        ResponseWriter.write(removed ? "Чел удален" : "Такого чела и не было");
    }

    public String getDescription() {
        return "удалить элемент из коллекции по его id";
    }

    public String getArgumentName() {
        return "id";
    }

    public List<Rule> getArgumentValidationRules() {
        return List.of(new LongRule());
    }
}
