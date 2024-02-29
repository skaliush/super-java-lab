package ru.skaliush.superlab.commands;

import ru.skaliush.superlab.app.ResponseWriter;
import ru.skaliush.superlab.models.Person;

import java.util.Collection;

public class ShowCommand extends Command {
    public void exec() {
        Collection<Person> collection = this.app.getCollectionManager().getCollection();
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
