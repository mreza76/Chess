package contoroller;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.*;
import netmork.NetworkConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    private boolean offlinemode=false;
    private Tile start;
    private GameController gameController;
    private Map<Piece, Position> whitePositions;
    private Map<Piece, Position> blackPositions;

    public Piece getking(int id){
        if (id ==1){
            for (Piece piece : whitePositions.keySet()) {
                if (piece instanceof King)
                    return piece;
            }
        }
        else
            for (Piece piece : blackPositions.keySet()) {
                if (piece instanceof King)
                    return piece;
            }
        return null;
    }
    public Player getplayer(int player){
        if (player==1)
            return white;
        else
            return black;
    }

    public GameController getGameController() {
        return gameController;
    }
    public void setGameController() {
        this.gameController = new GameController(this,white);
    }
    public void setMode(boolean OfflineMode){
        gameController.setIsofline(OfflineMode);
    }
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
        Position position = new Position(4,raw);
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
        position = new Position(3,raw);
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
        if(tile.isGotpiece()) {
            Piece piece = getPieceAt(tile.getPosition().getCol(), tile.getPosition().getRaw());
            if (gameController.checkTurn(piece)) {
                if (tile.isGotpiece() && start == null) {
                    start = tile;
                    start.selected();
                    for (Move move : gameController.getMovesForPieceAt(tile.getPosition())) {
                        tiles[move.getDestinationPosition().getCol()][move.getDestinationPosition().getRaw()].Highlight();
                        if (tiles[move.getDestinationPosition().getCol()][move.getDestinationPosition().getRaw()].isGotpiece()) {
                            if (tiles[move.getDestinationPosition().getCol()][move.getDestinationPosition().getRaw()].getPiece().getPlayer().getId() != tiles[move.getStartPosition().getCol()][move.getStartPosition().getRaw()].getPiece().getPlayer().getId()) {
                                tiles[move.getDestinationPosition().getCol()][move.getDestinationPosition().getRaw()].HighlightAttack();
                            }
                        }
                        if (move instanceof PawnAttack) {
                            tiles[move.getDestinationPosition().getCol()][move.getDestinationPosition().getRaw()].HighlightAttack();
                        }
                    }
                }
            }
            else fclick=true;
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
        if(piece.getPlayer().getId()==1)
            whitePositions.remove(piece,piece.getPosition());
        else
            blackPositions.remove(piece,piece.getPosition());
//        piece.setPosition(null);
    }
    //place piece in right position
    public void placePiece(Piece piece, Position position){
            if (piece.getPlayer().getId() == 1)
                whitePositions.put(piece, position);
            else
                blackPositions.put(piece, position);
        tiles[piece.getPosition().getCol()][piece.getPosition().getRaw()].setPiece(piece);
    }
    public Piece getPieceAt(int col, int raw){
//            return tiles[col][raw].getPiece();
        int a=0;
        Piece piece1=null;
        for (Piece piece : whitePositions.keySet()) {
            if (piece.getPosition().getRaw()==raw)
                if (piece.getPosition().getCol()==col) {
                    a++;
                    piece1=piece;
                }
        }
        for (Piece piece : blackPositions.keySet()) {
            if (piece.getPosition().getRaw()==raw)
                if (piece.getPosition().getCol()==col) {
                    a++;
                    piece1=piece;
                }
        }
        if (a==1)
            return piece1;
        return null;
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
    public void replacePieceAt(Position position, Piece piece){
        if (piece.getPlayer().getId() == 1)
            whitePositions.replace(piece, piece.getPosition(),position);
        else
            blackPositions.replace(piece, piece.getPosition(),position);
        piece.setPosition(position);
//        tiles[piece.getPosition().getCol()][piece.getPosition().getRaw()].setPiece(piece);
    }
    public ArrayList<Tile>MovesForPiece(){
        return null;
    }
    public Tile getTileAt(Position position){
        return tiles[position.getCol()][position.getRaw()];
    }

    public void MovePiece(Piece piece,Move move){
        if (piece instanceof  Rock)
            gameController.allpiec.add(piece);

        if (piece instanceof  King)
            gameController.allpiec.add(piece);

        if(!(move instanceof CastelingMove)) {
            tiles[piece.getPosition().getCol()][piece.getPosition().getRaw()].removepieice();
            if (getTileAt(move.getDestinationPosition()).getPiece() != null) {
                removePiece(getPieceAt(move.getDestinationPosition().getCol(), move.getDestinationPosition().getRaw()));
            }
            if (move instanceof PawnAttack) {
                if (((PawnAttack) move).isEnPassant())
                    removePiece(getPieceAt(((PawnAttack) move).getEnPassantCapturePosition().getCol(), ((PawnAttack) move).getEnPassantCapturePosition().getRaw()));
            }
            piece.setPosition(move.getDestinationPosition());
            if (piece.getPlayer().getId() == 1)
                whitePositions.replace(piece, move.getStartPosition(), move.getDestinationPosition());
            else
                blackPositions.replace(piece, move.getStartPosition(), move.getDestinationPosition());
            tiles[piece.getPosition().getCol()][piece.getPosition().getRaw()].setPiece(piece);
            if (piece instanceof Pawn) {
                piece = gameController.pawnconvert(piece);

                if (piece.getPlayer().getId() == 1)
                    whitePositions.replace(piece, move.getStartPosition(), move.getDestinationPosition());
                else
                    blackPositions.replace(piece, move.getStartPosition(), move.getDestinationPosition());
                tiles[piece.getPosition().getCol()][piece.getPosition().getRaw()].setPiece(piece);

            }
        }else{
            Piece piece1=getPieceAt(move.getDestinationPosition().getCol(),move.getDestinationPosition().getRaw());
            if (piece.getPlayer().getId() == 1) {
                tiles[piece.getPosition().getCol()][piece.getPosition().getRaw()].removepieice();
                tiles[piece1.getPosition().getCol()][piece1.getPosition().getRaw()].removepieice();
                whitePositions.replace(piece, ((CastelingMove) move).getKingposition(), move.getStartPosition());
                whitePositions.replace(piece1, ((CastelingMove) move).getRockposition(), move.getDestinationPosition());
                piece.setPosition(((CastelingMove) move).getKingposition());
                piece1.setPosition(((CastelingMove) move).getRockposition());
            }
            else {
                tiles[piece.getPosition().getCol()][piece.getPosition().getRaw()].removepieice();
                tiles[piece1.getPosition().getCol()][piece1.getPosition().getRaw()].removepieice();
                blackPositions.replace(piece, ((CastelingMove) move).getKingposition(), move.getStartPosition());
                blackPositions.replace(piece1, ((CastelingMove) move).getRockposition(), move.getDestinationPosition());
                piece.setPosition(((CastelingMove) move).getKingposition());
                piece1.setPosition(((CastelingMove) move).getRockposition());
            }
            tiles[piece.getPosition().getCol()][piece.getPosition().getRaw()].setPiece(piece);
            tiles[piece1.getPosition().getCol()][piece1.getPosition().getRaw()].setPiece(piece1);
        }
        gameController.changeTurn();
        if(!gameController.getIsofline())
            sendupdate(move);
        if(gameController.isGameOver())
            System.out.println("youuuuuu loseeeer");
    }

    public Set<Piece> getmap(int id) {
        if(id==1)
            return whitePositions.keySet();
        else
            return blackPositions.keySet();
    }

    public void reset(){}

}
