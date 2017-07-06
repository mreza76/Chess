package netmork;

import java.io.*;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public abstract class NetworkConnection extends Thread {
    public NetworkConnection() {
        try {
            outputStream=socket.getOutputStream();
            inputStream =socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void startConnection() throws Exception {
        start();
    }
    public void send(String data) {
        try {
            outputStream.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  String get(){
        String line = null;
        try {
            byte []buffer=new byte[256];
            inputStream.read(buffer);
        line= new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
    public void closeConnection() throws Exception {
    }

    public abstract boolean isServer();
    public abstract String getIP();
    public abstract int getPort();
    public Socket socket;
    public InputStream inputStream ;
    OutputStream outputStream ;
    @Override
    public void run() {
    }
}
