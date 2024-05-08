package ru.skaliush.superlab;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServerTest {
    public static void main(String[] args) {
        System.out.println("[Server] start");
        try (
                ServerSocket serverSocket = new ServerSocket(8080);
                Socket socket = serverSocket.accept();
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                DataInputStream inputStream = new DataInputStream(socket.getInputStream())
        ) {
            System.out.println("[Server] the client has connected");
            String request;
            do {
                request = inputStream.readUTF();
                System.out.println("[Server] client request:");
                System.out.println(request);
                outputStream.writeUTF("Your req is : " + request);
            } while (!request.equals("stop"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // System.out.println("[Server] end");
    }
}
