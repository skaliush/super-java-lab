package ru.skaliush.superlab.commands;

import ru.skaliush.superlab.storage.StorageSaver;

public class SaveCommand extends Command {
    public void exec(String argument) {
        StorageSaver storageSaver = new StorageSaver();
        storageSaver.save(this.app.getCollectionManager().getCollection());
    }

    public String getDescription() {
        return "save collection";
    }

}
