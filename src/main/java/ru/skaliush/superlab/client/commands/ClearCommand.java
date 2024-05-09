package ru.skaliush.superlab.client.commands;

import ru.skaliush.superlab.client.app.ResponseWriter;
import ru.skaliush.superlab.common.network.ActionAlias;
import ru.skaliush.superlab.common.network.Request;
import ru.skaliush.superlab.common.network.Response;

public class ClearCommand extends Command {
    public void exec(String argument) {
        Request request = new Request(ActionAlias.CLEAR);
        Response response = this.app.getRequestSender().send(request);
        Integer count = (Integer) response.getData();
        ResponseWriter.write("Коллекция очищена. Удалено элементов: " + count);
    }

    public String getDescription() {
        return "очистить коллекцию";
    }
}
