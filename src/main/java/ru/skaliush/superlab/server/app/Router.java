package ru.skaliush.superlab.server.app;

import ru.skaliush.superlab.common.request.ActionAlias;
import ru.skaliush.superlab.common.request.Request;
import ru.skaliush.superlab.common.request.Response;
import ru.skaliush.superlab.server.actions.*;

import java.util.Map;

public class Router {
    private final Map<ActionAlias, Action<?>> routeList = Map.of(
            ActionAlias.SHOW, new ShowAction(),
            ActionAlias.ADD, new CreateAction(),
            ActionAlias.UPDATE, new UpdateAction(),
            ActionAlias.REMOVE, new DeleteAction(),
            ActionAlias.CLEAR, new ClearAction()
    );

    public Response resolve(Request request) {
        ActionAlias alias = request.getActionAlias();
        if (routeList.containsKey(alias)) {
            Action<?> action = routeList.get(alias);
            Object result = action.execute(request);
            return new Response(request, result);
        } else {
            throw new RuntimeException("Команда не реализована - " + alias.name());
        }
    }

    public Map<ActionAlias, Action<?>> getRouteList() {
        return routeList;
    }
}
