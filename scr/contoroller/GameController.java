package contoroller;

import model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameController {
    private int turn=1;
    private ArrayList<Move> moves=new ArrayList<>();
    private ChessBoard chessBoard;
    private Piece selectedPiece;
    private boolean isofline=false;
    private Player currentPlayer;

    public boolean checkTurn(Piece piece){
        if(piece.getPlayer().getId()==getCurrentPlayer().getId()){
            if(isofline)
                return true;
            else if(getCurrentPlayer().getId()==turn)
                return true;
        }
         return false;
    }
    public boolean getIsofline() {
        return isofline;
    }

    public void setIsofline(boolean isofline) {
        this.isofline = isofline;
    }

    GameController(ChessBoard chessBoard,Player player){
        this.chessBoard=chessBoard;
        currentPlayer=player;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private void Castling(Piece p, Move m){}
    public boolean isInCheck(Player player){
        return false;
    }
    public boolean isGameOver(){
        return false;
    }
//    public void startGame(){
//        if (currentPlayer.getId()==1)
//
//    }
    public void changeTurn(){
        if(isofline) {
            if (currentPlayer.getId() == 1)
                currentPlayer = chessBoard.getplayer(2);
            else
                currentPlayer = chessBoard.getplayer(1);
        }else{
            if(turn==1)
                turn=2;
            else
                turn=1;
        }
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
