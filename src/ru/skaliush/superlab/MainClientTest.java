package ru.skaliush.superlab;

import ru.skaliush.superlab.common.request.ActionAlias;
import ru.skaliush.superlab.common.request.Request;
import ru.skaliush.superlab.common.request.RequestSender;
import ru.skaliush.superlab.common.request.Response;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MainClientTest {
    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("[Client] start");
        try (
                Socket socket = new Socket("127.0.0.1", 8080);
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                DataInputStream inputStream = new DataInputStream(socket.getInputStream())
        ) {
            System.out.println("[Client] connected to the server");
            while (scanner.hasNextLine()) {
                String prompt = scanner.nextLine();
                outputStream.writeUTF(prompt);
                // String response = new String(inputStream.readAllBytes());
                String response = inputStream.readUTF();
                System.out.println("[Client] server response:");
                System.out.println(response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("[Client] end");
    }

    public static void main(String[] args) {
        RequestSender requestSender = new RequestSender("127.0.0.1", 8888);
        Response response = requestSender.send(new Request(ActionAlias.INFO));
        System.out.println(response.getData());
    }
}
