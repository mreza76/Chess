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
    @Override
    public void start(Stage stage) throws Exception {
        chessBoard = new ChessBoard();
        Pane pane = chessBoard.getGridPane();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
