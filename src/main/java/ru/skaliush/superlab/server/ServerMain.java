package ru.skaliush.superlab.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.skaliush.superlab.common.models.Person;
import ru.skaliush.superlab.common.network.Request;
import ru.skaliush.superlab.common.network.Response;
import ru.skaliush.superlab.common.network.Serializer;
import ru.skaliush.superlab.server.app.Router;
import ru.skaliush.superlab.server.app.ServerAppContainer;
import ru.skaliush.superlab.server.collection.CollectionManager;
import ru.skaliush.superlab.server.storage.StorageReader;
import ru.skaliush.superlab.server.storage.StorageSaver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
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
        Logger logger = LogManager.getLogger("server");
        Serializer serializer = new Serializer();
        Router router = new Router();
        logger.info("[Server] start");
        while (true) {
            try (
                    ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
                    Socket socket = serverSocket.accept();
                    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                    DataInputStream inputStream = new DataInputStream(socket.getInputStream())
            ) {
                InetSocketAddress socketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
                String ipString = socketAddress.getHostString() + ":" + socketAddress.getPort();
                logger.info("[Server] the client has connected. IP: " + ipString);
                String requestString = inputStream.readUTF();
                Request request = (Request) serializer.deserializeFromString(requestString);
                logger.info("[Server] client request:");
                logger.info("    " + request);
                Response response = router.resolve(request);
                outputStream.writeUTF(serializer.serializeToString(response));
                logger.info("[Server] server response:");
                logger.info("    " + response);
            } catch (IOException e) {
                logger.info(e.getMessage());
                break;
            }
        }
        logger.info("[Server] end");
    }

    private static void initAppContainer() {
        ServerAppContainer appContainer = ServerAppContainer.getInstance();

        StorageSaver storageSaver = new StorageSaver(STORAGE_FILE_NAME);
        appContainer.setStorageSaver(storageSaver);

        StorageReader storageReader = new StorageReader(STORAGE_FILE_NAME);
        Collection<Person> collection = storageReader.read();

        CollectionManager collectionManager = new CollectionManager(collection);
        appContainer.setCollectionManager(collectionManager);
    }
}
