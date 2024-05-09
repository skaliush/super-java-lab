package ru.skaliush.superlab.client.commands;

import ru.skaliush.superlab.client.app.ResponseWriter;
import ru.skaliush.superlab.client.validation.rules.LongRule;
import ru.skaliush.superlab.client.validation.rules.Rule;
import ru.skaliush.superlab.common.models.Person;
import ru.skaliush.superlab.common.models.dto.PersonDTO;
import ru.skaliush.superlab.common.network.ActionAlias;
import ru.skaliush.superlab.common.network.Request;
import ru.skaliush.superlab.common.network.Response;

import java.util.List;

public class UpdateCommand extends Command {
    public void exec(String argument) {
        ResponseWriter.write(argument);
        Long id = Long.parseLong(argument);
        PersonForm form = new PersonForm();
        PersonDTO personDto = form.askPerson();
        personDto.setId(id);
        Request request = new Request(ActionAlias.UPDATE, personDto);
        Response response = this.app.getRequestSender().send(request);
        Person person = (Person) response.getData();
        ResponseWriter.write("Чел обновлен - " + person);
        // что если такого ID нет?
    }

    public String getDescription() {
        return "обновить значение элемента коллекции, id которого равен заданному";
    }

    public String getArgumentName() {
        return "id";
    }

    public List<Rule> getArgumentValidationRules() {
        return List.of(new LongRule());
    }
}
