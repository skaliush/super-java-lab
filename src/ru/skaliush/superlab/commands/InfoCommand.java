package ru.skaliush.superlab.commands;

import ru.skaliush.superlab.app.ResponseWriter;
import ru.skaliush.superlab.collection.CollectionManager;
import ru.skaliush.superlab.models.Person;

public class InfoCommand extends Command {
    public void exec(String argument) {
        CollectionManager collectionManager = this.app.getCollectionManager();
        ResponseWriter.write("Информация о коллекции:");
        ResponseWriter.write("Кол-во элементов: " + collectionManager.getCollection().size());
        ResponseWriter.write("Тип коллекции: " + collectionManager.getCollectionType());
        ResponseWriter.write("Тип элементов: " + Person.class.getSimpleName());
    }

    public String getDescription() {
        return "вывести информацию о коллекции";
    }

}
