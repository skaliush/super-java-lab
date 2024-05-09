package ru.skaliush.superlab.common.network;

import java.io.Serializable;

public class Request implements Serializable {
    private final ActionAlias actionAlias;
    private Object data;

    public Request(ActionAlias actionAlias) {
        this.actionAlias = actionAlias;
    }

    public Request(ActionAlias actionAlias, Object data) {
        this.actionAlias = actionAlias;
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
}
