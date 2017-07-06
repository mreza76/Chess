package contoroller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import netmork.Client;
import netmork.NetworkConnection;
import netmork.Server;

import java.util.Scanner;

/**
 * Created by amirsaeed on 5/27/2017.
 */
public class ChessGui extends Application{
    private NetworkConnection networkConnection;
    private ChessBoard chessBoard;
    private Pane root;
    @Override
    public void start(Stage stage) throws Exception {

        root=new Pane();
        chessBoard = new ChessBoard();
        Scanner scanner= new Scanner(System.in);
        int a= scanner.nextInt();
        if(a==1){
            networkConnection= new Server(8080,chessBoard);
        }
        else
            networkConnection= new Client("127.0.0.1",8080,chessBoard);
        networkConnection.startConnection();
        root.getChildren().add(chessBoard.getGridPane());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        chessBoard.setNetworkConnection(networkConnection);
        if(networkConnection.isServer())
            stage.setTitle("White");
        else
            stage.setTitle("Black");
        stage.show();
    }
}
