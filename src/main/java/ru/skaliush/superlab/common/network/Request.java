package ru.skaliush.superlab.common.network;

import ru.skaliush.superlab.client.app.ClientAppContainer;
import ru.skaliush.superlab.common.models.User;

import java.io.Serializable;

public class Request implements Serializable {
    private final ActionAlias actionAlias;
    private Object data;
    private User user;

    public Request(ActionAlias actionAlias) {
        this.actionAlias = actionAlias;
        this.user = ClientAppContainer.getInstance().getUser();
    }

    public Request(ActionAlias actionAlias, Object data) {
        this(actionAlias);
        this.data = data;
    }

    public ActionAlias getActionAlias() {
        return actionAlias;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Request{" +
                "actionAlias=" + actionAlias +
                ", data=" + data +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
