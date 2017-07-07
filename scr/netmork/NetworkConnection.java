package netmork;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import contoroller.ChessBoard;

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

    public abstract boolean isServer();
    private String data;
    public abstract String getIP();
    public abstract int getPort();
    private ChessBoard chessBoard;
    public Socket socket;
    public InputStream inputStream ;
    OutputStream outputStream ;
    public NetworkConnection(ChessBoard chessBoard) {
        this.chessBoard=chessBoard;
    }
    public void startConnection() throws Exception {
        start();
    }
    public void setdata(String data){
        this.data=data;
    }
    public void send() {
        while (data==null){
            System.out.println("wait for move");
        }
        try {
            outputStream=socket.getOutputStream();
            outputStream.write(data.getBytes());
            System.out.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        data=null;
    }
    public  void get(){
        data = null;
        try {
            while (data==null) {
                inputStream = socket.getInputStream();
                byte[] buffer = new byte[256];
                inputStream.read(buffer);
                data = new String(buffer);
            }
            System.out.println(data);
        } catch (IOException e){
            e.printStackTrace();
        }
        chessBoard.getUpdate(data);
        data=null;
    }
    public void closeConnection() throws Exception {
    }

    @Override
    public void run() {
        while (socket.isConnected()){
            if(isServer()){
                send();
                get();
            }
            else{
                get();
                send();
            }
        }
    }
}
