package contoroller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import netmork.NetworkConnection;

/**
 * Created by amirsaeed on 5/27/2017.
 */
//it's just a test for mohammadreza safi
public class ChessGui extends Application{
    private NetworkConnection networkConnection;
    private ChessBoard chessBoard;
    private Pane root;
    @Override
    public void start(Stage stage) throws Exception {
        root=new Pane();
        chessBoard = new ChessBoard();
        root.getChildren().add(chessBoard.getGridPane());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
