package ru.skaliush.superlab.commands;

import ru.skaliush.superlab.app.ResponseWriter;
import ru.skaliush.superlab.models.Person;

import java.util.Set;

public class ShowCommand extends Command {
    public void exec() {
        Set<Person> collection = this.app.getCollectionManager().getCollection();
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

    public String getAlias() {
        return "show";
    }
}
