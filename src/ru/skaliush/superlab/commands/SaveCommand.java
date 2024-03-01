package ru.skaliush.superlab.commands;

import ru.skaliush.superlab.app.ResponseWriter;
import ru.skaliush.superlab.storage.StorageSaver;

public class SaveCommand extends Command {
    public void exec(String argument) {
        StorageSaver storageSaver = new StorageSaver();
        storageSaver.save(this.app.getCollectionManager().getCollection());
        ResponseWriter.write("Сохранено");
    }

    public String getDescription() {
        return "save collection";
    }

}
