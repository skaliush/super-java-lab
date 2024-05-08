package ru.skaliush.superlab.server;

import ru.skaliush.superlab.common.models.Person;
import ru.skaliush.superlab.common.request.Request;
import ru.skaliush.superlab.common.request.Response;
import ru.skaliush.superlab.common.request.Serializer;
import ru.skaliush.superlab.server.app.Router;
import ru.skaliush.superlab.server.app.ServerAppContainer;
import ru.skaliush.superlab.server.collection.CollectionManager;
import ru.skaliush.superlab.server.storage.StorageReader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;

public class ServerMain {
    public static final int SERVER_PORT = 8888;
    public static final String STORAGE_FILE_NAME = "people.csv";

    public static void main(String[] args) {
        initAppContainer();
        runServer();
    }

    private static void runServer() {
        Serializer serializer = new Serializer();
        Router router = new Router();
        System.out.println("[Server] start");
        while (true) {
            try (
                    ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
                    Socket socket = serverSocket.accept();
                    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                    DataInputStream inputStream = new DataInputStream(socket.getInputStream())
            ) {
                System.out.println("[Server] the client has connected");
                String requestString = inputStream.readUTF();
                Request request = (Request) serializer.deserializeFromString(requestString);
                System.out.println("[Server] client request:");
                System.out.println("    " + request);
                Response response = router.resolve(request);
                outputStream.writeUTF(serializer.serializeToString(response));
                System.out.println("[Server] server response:");
                System.out.println("    " + response);
                System.out.println();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
        System.out.println("[Server] end");
    }

    private static void initAppContainer() {
        ServerAppContainer appContainer = ServerAppContainer.getInstance();
        StorageReader storageReader = new StorageReader();
        Collection<Person> collection = storageReader.read(STORAGE_FILE_NAME);
        CollectionManager collectionManager = new CollectionManager(collection);
        appContainer.setCollectionManager(collectionManager);
    }
}
