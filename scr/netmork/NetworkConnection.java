package netmork;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public abstract class NetworkConnection extends Thread {
    private Consumer<Serializable> onRecieveCallback;

    public NetworkConnection() {
    }
    public void startConnection() throws Exception {
        start();
    }
    public void send(String data) {}
    public void get(String data){}
    public void closeConnection() throws Exception {
    }

    public abstract boolean isServer();
    public abstract String getIP();
    public abstract int getPort();
    public Socket socket;
    public ObjectOutputStream objectOutputStream;
    @Override
    public void run() {
    }
}
