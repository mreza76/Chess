package contoroller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.*;
import netmork.NetworkConnection;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public class ChessBoard {
    private Player[] players;
    private GridPane gridPane;
    private NetworkConnection networkConnection;

    public GridPane getGridPane() {
        return gridPane;
    }

    private Tile[][]tiles;
    private GameController gameController;
    private Map<Piece, Position> whitePositions;
    private Map<Piece, Position> blackPositions;
    private EventHandler<? super MouseEvent> tileListener(Tile tile) {
        return null;
    }
    public void FirstClick(Tile tile){}
    public void ScondClick(Tile tile){}
    public void setUpdate(){}
    public void setNetworkConnection(NetworkConnection networkConnection){
        this.networkConnection=networkConnection;
    }
    ChessBoard(){
        tiles= new Tile[8][8];
        gridPane= new GridPane();
       // gridPane.setStyle("-fx-background-color : goldenrod;");
        for (int raw = 0; raw <8 ; raw++) {
            for (int col = 0; col <8 ; col++) {
                Tile tile = new Tile(new Position(raw,col));
                tiles[raw][col]=tile;
                gridPane.add(tile.getPane(),
                        1 + tile.getPosition().getCol(),
                        1 + tile.getPosition().getRaw());
            }

        }
    }
    private void removePiece(Piece p){}
    private void placePiece(Piece p, Position pos){}
    private Piece getPieceAt(int row, int col){
        return null;
    }
    public void getUpdate(Move move,Piece piece){}
    public void replacePieceAt(Position pos, Piece newPiece){}
    public ArrayList<Tile>MovesForPiece(){
        return null;
    }
    public void MovePiece(Piece piece,Move move){}
    public void reset(){}

}
