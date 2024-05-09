package ru.skaliush.superlab.common.network;

import java.io.Serializable;

public class Response implements Serializable {
    private final Request request;

    private Object data;

    public Response(Request request) {
        this.request = request;
    }

    public Response(Request request, Object data) {
        this.request = request;
        this.data = data;
    }

    public Request getRequest() {
        return request;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "request=" + request +
                ", data=" + data +
                '}';
    }
}
