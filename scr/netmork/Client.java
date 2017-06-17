package netmork;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public class Client extends NetworkConnection {
    private String ip;
    private int port;

    public Client(String ip,int port) throws IOException {
        this.ip=ip;
        this.port=port;
        socket=new Socket("127.0.0.1",8080);
        System.out.println("Socket connected");
    }

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
}
