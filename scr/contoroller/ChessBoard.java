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
    private Player white;
    private Player black;
    private GridPane gridPane;
    private NetworkConnection networkConnection;

    public GridPane getGridPane() {
        return gridPane;
    }

    private Tile[][]tiles;
    private boolean fclick=true;
    private Tile start;
    private GameController gameController;
    private Map<Piece, Position> whitePositions;
    private Map<Piece, Position> blackPositions;
    public void FirstClick(Tile tile){}
    public void ScondClick(Tile tile){}
    public void setUpdate(){}
    public void setNetworkConnection(NetworkConnection networkConnection){
        this.networkConnection=networkConnection;
        if (networkConnection.isServer())
            gameController=new GameController(this,white);
        else
            gameController=new GameController(this,black);
    }

    public void setfclick(boolean fclick){
        this.fclick=fclick;
    }
    ChessBoard(){
        white = new Player(1,"white");
        black = new Player(2,"black");
        whitePositions = new HashMap<>();
        blackPositions = new HashMap<>();
        //add tiles
        tiles= new Tile[8][8];
        gridPane= new GridPane();
        for (int raw = 0; raw <8 ; raw++) {
            for (int col = 0; col <8 ; col++) {
                Tile tile = new Tile(new Position(col,raw));
                tiles[col][raw]=tile;
                gridPane.add(tile.getPane(),
                         tile.getPosition().getCol(),
                         tile.getPosition().getRaw());
                tile.getPane().setOnMouseClicked(tileListener(tile));
            }
        }

        //add pieces
        addPieces(white);
        addPieces(black);
//        gameController.startGame();
    }
    // add all pieces at first
    public void addPieces(Player player){
        int raw;
        if(player.getId()==1)
            raw =6;
        else
            raw=1;
        //add pawn
        for (int col = 0; col <8 ; col++) {
            Position position = new Position(col ,raw);
            placePiece(new Pawn(player,position),position);
        }
        if(player.getId()==1)
            raw =7;
        else
            raw=0;
        //add kings
        Position position = new Position(3,raw);
        placePiece(new King(player,position),position);
        //add rocks
        position= new Position(0,raw);
        placePiece(new Rock(player,position),position);
        position= new Position(7,raw);
        placePiece(new Rock(player,position),position);
        //add knights
        position= new Position(1,raw);
        placePiece(new Knight(player,position),position);
        position = new Position(6,raw);
        placePiece(new Knight(player,position),position);
        //add bishops
        position = new Position(2,raw);
        placePiece(new Bishop(player,position),position);
        position = new Position(5,raw);
        placePiece(new Bishop(player,position),position);
        //add queens
        position = new Position(4,raw);
        placePiece(new Queen(player,position),position);
    }
    //it will be called every time ,any time mouse clicked
    public EventHandler<? super MouseEvent> tileListener(Tile tile) {
        return event -> {

            if(fclick ){
                fclick=false;
                firstclick(tile);
            }
            else{
                secondclick(tile);

            }
        };
    }
    public void firstclick(Tile tile){
        if (tile.isGotpiece()&&start==null){
            start= tile;
            start.selected();
            for (Move move : gameController.getMovesForPieceAt(tile.getPosition())) {
                    tiles[move.getDestinationPosition().getCol()][move.getDestinationPosition().getRaw()].Highlight();
                    if(tiles[move.getDestinationPosition().getCol()][move.getDestinationPosition().getRaw()].isGotpiece()){
                        if(tiles[move.getDestinationPosition().getCol()][move.getDestinationPosition().getRaw()].getPiece().getPlayer().getId()!=tiles[move.getStartPosition().getCol()][move.getStartPosition().getRaw()].getPiece().getPlayer().getId()){
                            tiles[move.getDestinationPosition().getCol()][move.getDestinationPosition().getRaw()].HighlightAttack();
                        }
                    }
            }
        }
        else
            fclick= true;
    }
    public void secondclick(Tile tile){
//        check if anything has change
        boolean flag=false;
        if(tile!=start) {
            Piece piece = start.getPiece();
//            check if move can be done or not
                    for (Move move : gameController.getMovesForPieceAt(start.getPosition())) {
                        if (move.getDestinationPosition().getCol() == tile.getPosition().getCol()) {
                            if (move.getDestinationPosition().getRaw() == tile.getPosition().getRaw()) {
                                MovePiece(piece, move);
                                flag = true;
                                break;
                            }

                        } else {
                            fclick = false;
                        }
                    }
        }else
            flag=true;
        if(flag==true){
            for (int raw = 0; raw < 8; raw++) {
                for (int col = 0; col < 8; col++) {
                    tiles[col][raw].unselected();
                }
            }
            start=null;
            fclick=true;
        }

    }
    public void removePiece(Piece piece){
        tiles[piece.getPosition().getCol()][piece.getPosition().getRaw()].removepieice();
    }
    //place piece in right position
    public void placePiece(Piece piece, Position position){
            if (piece.getPlayer().getId() == 1)
                whitePositions.put(piece, position);
            else
                blackPositions.put(piece, position);
        tiles[piece.getPosition().getCol()][piece.getPosition().getRaw()].setPiece(piece);
    }
    public Piece getPieceAt(int col, int row){
        return tiles[col][row].getPiece();
    }
    public void getUpdate(String data){
        System.out.println(data.charAt(0));
       Position startposition=new Position();
       startposition.setCol(Character.getNumericValue(data.charAt(0)));
       startposition.setRaw(Character.getNumericValue(data.charAt(1)));
        System.out.println(startposition.toString());
       Position destposition=new Position();
       destposition.setCol(Character.getNumericValue(data.charAt(2)));
       destposition.setRaw(Character.getNumericValue(data.charAt(3)));
        System.out.println(destposition.toString());
       Move move= new Move(startposition,destposition);
       MovePiece(getPieceAt(startposition.getCol(),startposition.getRaw()),move);
    }
    public void sendupdate(Move move){
        networkConnection.setdata(move.toString());
    }
    public void replacePieceAt(Position position, Piece newPiece){
    }
    public ArrayList<Tile>MovesForPiece(){
        return null;
    }
    public Tile getTileAt(Position position){
        return tiles[position.getCol()][position.getRaw()];
    }

    public void MovePiece(Piece piece,Move move){
        tiles[piece.getPosition().getCol()][piece.getPosition().getRaw()].removepieice();
        if(getTileAt(move.getDestinationPosition()).getPiece()!=null){
            removePiece(getPieceAt(move.getDestinationPosition().getCol(),move.getDestinationPosition().getRaw()));
        }
        piece.setPosition(move.getDestinationPosition());
        if (piece.getPlayer().getId() == 1)
            whitePositions.replace(piece, move.getStartPosition(),move.getDestinationPosition());
        else
            blackPositions.replace(piece, move.getStartPosition(),move.getDestinationPosition());
        tiles[piece.getPosition().getCol()][piece.getPosition().getRaw()].setPiece(piece);
        if (piece instanceof Pawn){
            piece=gameController.pawnconvert(piece);
            tiles[piece.getPosition().getCol()][piece.getPosition().getRaw()].removepieice();
            if(getTileAt(move.getDestinationPosition()).getPiece()!=null){
                removePiece(getPieceAt(move.getDestinationPosition().getCol(),move.getDestinationPosition().getRaw()));
            }
            piece.setPosition(move.getDestinationPosition());
            if (piece.getPlayer().getId() == 1)
                whitePositions.replace(piece, move.getStartPosition(),move.getDestinationPosition());
            else
                blackPositions.replace(piece, move.getStartPosition(),move.getDestinationPosition());
            tiles[piece.getPosition().getCol()][piece.getPosition().getRaw()].setPiece(piece);
        }
        System.out.println(move.getDestinationPosition().toString());
        //System.out.println(move.toString());
        sendupdate(move);
        //        gameController.endTurn();
    }
    public void reset(){}

}
