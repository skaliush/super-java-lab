package ru.skaliush.superlab.commands;

import ru.skaliush.superlab.storage.StorageSaver;

public class SaveCommand extends Command {
    public void exec() {
        StorageSaver storageSaver = new StorageSaver();
        storageSaver.save(this.app.getCollectionManager().getCollection());
    }

    public String getDescription() {
        return "save collection";
    }

    public String getAlias() {
        return "save";
    }
}
