package netmork;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public class Server extends NetworkConnection {
    private int port;
    private ServerSocket serverSocket;
    @Override
    public boolean isServer() {
        return false;
    }

    @Override
    public String getIP() {
        return null;
    }

    @Override
    public int getPort() {
        return 0;
    }

    public Server(int port) throws IOException {
        this.port=port;
        serverSocket = new ServerSocket(port);
        socket= serverSocket.accept();
        System.out.println("socket connected");
    }
}
