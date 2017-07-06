package netmork;

import contoroller.ChessBoard;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public class Server extends NetworkConnection {
    private int port;
    private ServerSocket serverSocket;
    @Override
    public boolean isServer() {
        return true;
    }

    @Override
    public String getIP() {
        return null;
    }

    @Override
    public int getPort() {
        return port;
    }

    public Server(int port,ChessBoard chessBoard) throws IOException {
        super(chessBoard);
        this.port=port;
        serverSocket = new ServerSocket(port);
        socket= serverSocket.accept();
        System.out.println("socket connected");
        InputStream inputStream  =socket.getInputStream();
        OutputStream outputStream=socket.getOutputStream();
    }
}
