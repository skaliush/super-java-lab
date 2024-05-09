package ru.skaliush.superlab.client.commands;

import ru.skaliush.superlab.client.app.ResponseWriter;
import ru.skaliush.superlab.common.models.Person;
import ru.skaliush.superlab.common.network.ActionAlias;
import ru.skaliush.superlab.common.network.Request;
import ru.skaliush.superlab.common.network.Response;

import java.util.Collection;

public class InfoCommand extends Command {
    public void exec(String argument) {
        Request request = new Request(ActionAlias.SHOW);
        Response response = this.app.getRequestSender().send(request);
        Collection<Person> collection = (Collection<Person>) response.getData();
        ResponseWriter.write("Информация о коллекции:");
        ResponseWriter.write("Кол-во элементов: " + collection.size());
        ResponseWriter.write("Тип коллекции: " + collection.getClass().getSimpleName());
        ResponseWriter.write("Тип элементов: " + Person.class.getSimpleName());
    }

    public String getDescription() {
        return "вывести информацию о коллекции";
    }

}
