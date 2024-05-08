package ru.skaliush.superlab.client.commands;

import ru.skaliush.superlab.client.app.ResponseWriter;
import ru.skaliush.superlab.common.models.Person;
import ru.skaliush.superlab.common.request.ActionAlias;
import ru.skaliush.superlab.common.request.Request;
import ru.skaliush.superlab.common.request.Response;

import java.util.Collection;

public class ShowCommand extends Command {
    public void exec(String argument) {
        Response response = this.app.getRequestSender().send(new Request(ActionAlias.SHOW));
        Collection<Person> collection = (Collection<Person>) response.getData();
        if (collection.isEmpty()) {
            ResponseWriter.write("Коллекция пуста");
        } else {
            for (Person person : collection) {
                ResponseWriter.write(person);
            }
        }
    }

    public String getDescription() {
        return "вывести все элементы коллекции";
    }

}
