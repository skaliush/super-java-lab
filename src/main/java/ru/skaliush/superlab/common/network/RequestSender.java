package ru.skaliush.superlab.common.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class RequestSender {
    private String serverHost;
    private int serverPort;
    private final Serializer serializer = new Serializer();

    public RequestSender(String serverHost, int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    public Response send(Request request) {
        try (
                Socket socket = new Socket(serverHost, serverPort);
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                DataInputStream inputStream = new DataInputStream(socket.getInputStream())
        ) {
            String serializedRequest = this.serializer.serializeToString(request);
            outputStream.writeUTF(serializedRequest);
            String serializedResponse = inputStream.readUTF();
            return (Response) this.serializer.deserializeFromString(serializedResponse);
        } catch (IOException e) {
            throw new ServerUnavailableException(e.getMessage());
        }
    }

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }
}
