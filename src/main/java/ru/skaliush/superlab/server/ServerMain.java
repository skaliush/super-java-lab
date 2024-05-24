package ru.skaliush.superlab.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.skaliush.superlab.common.network.Request;
import ru.skaliush.superlab.common.network.Response;
import ru.skaliush.superlab.common.network.Serializer;
import ru.skaliush.superlab.server.app.Router;
import ru.skaliush.superlab.server.app.ServerAppContainer;
import ru.skaliush.superlab.server.collection.PostgresCollectionManager;
import ru.skaliush.superlab.server.user.UserManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class ServerMain {
    public static final int SERVER_PORT = 8888;

    public static final String DB_URL = "jdbc:postgresql://localhost:5432/misha";
    public static final String DB_USER = "postgres";
    public static final String DB_PASSWORD = "root";

    public static void main(String[] args) {
        connectDB();
        runServer();
    }

    private static void runServer() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ExecutorService forkJoinPool = new ForkJoinPool();
        Logger logger = LogManager.getLogger("server");
        Serializer serializer = new Serializer();
        Router router = new Router();
        logger.info("[Server] start");
        cachedThreadPool.submit(() -> {
            try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
                while (true) {
                    try {
                        Socket socket = serverSocket.accept();
                        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                        InetSocketAddress socketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
                        String ipString = socketAddress.getHostString() + ":" + socketAddress.getPort();
                        logger.info("[Server] the client has connected. IP: " + ipString);
                        String requestString = inputStream.readUTF();
                        Request request = (Request) serializer.deserializeFromString(requestString);
                        logger.info("[Server] client request:");
                        logger.info("    " + request);
                        forkJoinPool.submit(() -> {
                            Response response = router.resolve(request);
                            Thread thread = new Thread(() -> {
                                try {
                                    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                                    outputStream.writeUTF(serializer.serializeToString(response));
                                    socket.close();
                                    logger.info("[Server] server response:");
                                    logger.info("    " + response);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                            thread.start();
                        });
                    } catch (IOException e) {
                        logger.info(e.getMessage());
                        break;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void connectDB() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            ServerAppContainer appContainer = ServerAppContainer.getInstance();
            appContainer.setConnection(connection);
            PostgresCollectionManager collectionManager = new PostgresCollectionManager(connection);
            appContainer.setCollectionManager(collectionManager);
            UserManager userManager = new UserManager(connection);
            appContainer.setUserManager(userManager);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
