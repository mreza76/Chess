package contoroller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.*;
import netmork.NetworkConnection;

import java.util.ArrayList;
import java.util.HashMap;
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
    public void FirstClick(Tile tile){}
    public void ScondClick(Tile tile){}
    public void setUpdate(){}
    public void setNetworkConnection(NetworkConnection networkConnection){
        this.networkConnection=networkConnection;
    }
    ChessBoard(){
        whitePositions = new HashMap<>();
        blackPositions = new HashMap<>();
        tiles= new Tile[8][8];
        gridPane= new GridPane();
        for (int raw = 0; raw <8 ; raw++) {
            for (int col = 0; col <8 ; col++) {
                Tile tile = new Tile(new Position(raw,col));
                tiles[raw][col]=tile;
                gridPane.add(tile.getPane(),
                        1 + tile.getPosition().getCol(),
                        1 + tile.getPosition().getRaw());
                tile.getPane().setOnMouseClicked(tileListener(tile));
            }
        }
        Player white = new Player(1,"white");
        Player black = new Player(2,"black");
        addPieces(white);
        addPieces(black);
    }
    public void addPieces(Player player){
        int col;
        if(player.getId()==1)
            col =6;
        else
            col=1;
        for (int raw = 0; raw <8 ; raw++) {
            Position position = new Position(raw ,col);
            placePiece(new Pawn(player,position),position);
        }
        if(player.getId()==1)
            col =7;
        else
            col=0;
        Position position = new Position(3,col);
        placePiece(new King(player,position),position);
        position= new Position(0,col);
        placePiece(new Rock(player,position),position);
        position= new Position(7,col);
        placePiece(new Rock(player,position),position);
        position= new Position(1,col);
        placePiece(new Knight(player,position),position);
        position = new Position(6,col);
        placePiece(new Knight(player,position),position);
        position = new Position(2,col);
        placePiece(new Bishop(player,position),position);
        position = new Position(5,col);
        placePiece(new Bishop(player,position),position);
        position = new Position(4,col);
        placePiece(new Queen(player,position),position);
    }
    public EventHandler<? super MouseEvent> tileListener(Tile tile) {

        return null;
    }
    public void removePiece(Piece piece){}
    public void placePiece(Piece piece, Position position){
            if (piece.getPlayer().getId() == 1)
                whitePositions.put(piece, position);
            else
                blackPositions.put(piece, position);
        tiles[piece.getPosition().getRaw()][piece.getPosition().getCol()].setPiece(piece);
    }
    public Piece getPieceAt(int row, int col){
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
