package netmork;

import contoroller.ChessBoard;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public class Client extends NetworkConnection {
    private String ip;
    private int port;

    public Client(String ip, int port,ChessBoard chessBoard) throws IOException {
        super(chessBoard);
        this.ip=ip;
        this.port=port;
        socket=new Socket("127.0.0.1",8080);
        System.out.println("Socket connected");
        inputStream  =socket.getInputStream();
        outputStream=socket.getOutputStream();
    }

    @Override
    public boolean isServer() {
        return false;
    }

    @Override
    public String getIP() {
        return ip;
    }

    @Override
    public int getPort() {
        return port;
    }
}
