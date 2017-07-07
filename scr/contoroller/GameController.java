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
        if((move instanceof PawnAttack)){
            if (piecedest==null){
                if(piecestart.getPlayer().getId()==1)
                    piecedest=chessBoard.getPieceAt(move.getDestinationPosition().getCol(),move.getDestinationPosition().getRaw()+1);
                else
                    piecedest=chessBoard.getPieceAt(move.getDestinationPosition().getCol(),move.getDestinationPosition().getRaw()-1);
                if(piecedest==null)
                    return false;
                else if (piecedest.getPlayer().getId()==piecestart.getPlayer().getId())
                        return false;
                else {
                    ((PawnAttack) move).setEnPassant(true);
                    ((PawnAttack) move).setEnPassantCapturePosition(piecedest.getPosition());
                }
            }

        }else if(piecestart instanceof Pawn)
        {
            if (piecedest!=null)
                return false;
        }

        return true ;
    }

    public boolean isjump(Move move){
        int col = move.getDestinationPosition().getCol()-move.getStartPosition().getCol();
        int raw = move.getDestinationPosition().getRaw()-move.getStartPosition().getRaw();
        int j = getnum(col);
        int i= getnum(raw);
        col=move.getStartPosition().getCol()+j;
        raw=move.getStartPosition().getRaw()+i;
        Piece piece = chessBoard.getPieceAt(col-j,raw-i);
        if(piece instanceof Knight)
            return false;
        while ((raw!=move.getDestinationPosition().getRaw())||(col!=move.getDestinationPosition().getCol())){
            if(chessBoard.getPieceAt(col,raw)!=null)
                return true;
            col+=j;
            raw+=i;

        }
        return false;
    }
    public int getnum(int num){
        if (num>0)
            return 1;
        else if (num<0)
            return -1;
        else
            return 0;
    }
    public boolean ischecks(Move move ){
        Piece piecedest=chessBoard.getPieceAt(move.getDestinationPosition().getCol(),move.getDestinationPosition().getRaw());
        Piece piecestart=chessBoard.getPieceAt(move.getStartPosition().getCol(),move.getStartPosition().getRaw());
        if(piecedest instanceof  King)
                return true ;
        else return false ;

    }

    public Set<Move> getMovesForPieceAt(Position position){
        Set<Move> tmps = chessBoard.getPieceAt(position.getCol(),position.getRaw()).GenerateMoves(position);
        Set<Move> correctmoves =new HashSet<>() ;
        for (Move tmp : tmps) {
            if(!isjump(tmp))
                if(pieceCanMove(tmp,currentPlayer)){
                    if (!ischecks(tmp))
                        correctmoves.add(tmp) ;
            }
        }
        return correctmoves ;

    }
    public void makeMove(Move move){}

    public void rollback(){}



}
