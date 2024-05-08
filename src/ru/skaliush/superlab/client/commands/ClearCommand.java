package ru.skaliush.superlab.client.commands;

import ru.skaliush.superlab.client.app.ResponseWriter;
import ru.skaliush.superlab.common.request.ActionAlias;
import ru.skaliush.superlab.common.request.Request;
import ru.skaliush.superlab.common.request.Response;

public class ClearCommand extends Command {
    public void exec(String argument) {
//        int count = this.app.getCollectionManager().clearCollection();
        Response response = this.app.getRequestSender().send(new Request(ActionAlias.CLEAR));
        Integer count = (Integer) response.getData();
        ResponseWriter.write("Коллекция очищена. Удалено элементов: " + count);
    }

    public String getDescription() {
        return "очистить коллекцию";
    }
}
