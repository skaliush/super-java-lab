package ru.skaliush.superlab.client.commands;

import ru.skaliush.superlab.client.app.ResponseWriter;
import ru.skaliush.superlab.server.storage.StorageSaver;

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
