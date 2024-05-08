package ru.skaliush.superlab.client.commands;

import ru.skaliush.superlab.client.app.ResponseWriter;
import ru.skaliush.superlab.common.models.Person;
import ru.skaliush.superlab.common.models.dto.PersonDTO;
import ru.skaliush.superlab.common.request.ActionAlias;
import ru.skaliush.superlab.common.request.Request;
import ru.skaliush.superlab.common.request.Response;

public class AddCommand extends Command {
    public void exec(String argument) {
        PersonForm form = new PersonForm();
        PersonDTO personDto = form.askPerson();
        ResponseWriter.write(personDto);
        Response response = this.app.getRequestSender().send(new Request(ActionAlias.ADD, personDto));
        Person person = (Person) response.getData();
        ResponseWriter.write("Создан чел с ID " + person.getId());
    }

    public String getDescription() {
        return "добавить новый элемент в коллекцию";
    }

}
