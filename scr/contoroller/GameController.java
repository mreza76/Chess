package contoroller;

import model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameController {

    private ArrayList<Move> moves=new ArrayList<>();
    private ChessBoard chessBoard;
    private Piece selectedPiece;
    private boolean isofline=true;

    public boolean isIsofline() {
        return isofline;
    }

    public void setIsofline(boolean isofline) {
        this.isofline = isofline;
    }

    private Player currentPlayer;
    GameController(ChessBoard chessBoard,Player player){
        this.chessBoard=chessBoard;
        currentPlayer=player;
//        startGame();
    }
    private void Castling(Piece p, Move m){}
    public boolean isInCheck(Player player){
        return false;
    }
    public boolean isGameOver(){
        return false;
    }
    public void startGame(){
        if (currentPlayer.getId()==1)
            beginTurn();
        else
            endTurn();
    }
    public void beginTurn(){
        chessBoard.setfclick(true);
    }
    public void endTurn(){
        chessBoard.setfclick(false);
//        chessBoard.getUpdate();
        beginTurn();
    }
    public Piece pawnconvert(Piece piece){
        Position current =piece.getPosition();
        Player tplayer=piece.getPlayer();
        if(piece.getPlayer().getId()==1) {
            if (piece.getPosition().getRaw()==0) {
                piece = new Queen(tplayer, current);
            }
        }
        if(piece.getPlayer().getId()==2) {
            if (piece.getPosition().getRaw()==7) {
                piece = new Queen(tplayer, current);
            }
        }
        return piece;
    }
    public boolean pieceCanMove(Move move, Player movingPlayer){
        Piece piecedest=chessBoard.getPieceAt(move.getDestinationPosition().getCol(),move.getDestinationPosition().getRaw());
        Piece piecestart=chessBoard.getPieceAt(move.getStartPosition().getCol(),move.getStartPosition().getRaw());
        if(piecedest!=null) {
            if (piecedest.getPlayer().getId() == piecestart.getPlayer().getId()) {
                return false;
            }

        }
        return true ;
    }

    public Set<Move> getMovesForPieceAt(Position position){
        Set<Move> tmps = chessBoard.getPieceAt(position.getCol(),position.getRaw()).GenerateMoves(position);
        Set<Move> correctmoves =new HashSet<>() ;
        for (Move tmp : tmps) {
            if(pieceCanMove(tmp,currentPlayer)){
                correctmoves.add(tmp) ;
            }
        }
        return correctmoves ;

    }
    public void makeMove(Move move){}
    public void rollback(){}



}
