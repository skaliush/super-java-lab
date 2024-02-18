package ru.skaliush.superlab.commands;

import ru.skaliush.superlab.app.ResponseWriter;
import ru.skaliush.superlab.collection.CollectionManager;

public class InfoCommand extends Command {
    public void exec() {
        CollectionManager collectionManager = this.app.getCollectionManager();
        ResponseWriter.write(collectionManager.getCollectionType());
    }

    public String getDescription() {
        return "вывести информацию о коллекции";
    }

    public String getAlias() {
        return "info";
    }
}
