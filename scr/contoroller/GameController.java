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
        if(isofline)
            for (Piece piece : chessBoard.getmap(currentPlayer.getId())) {
                if (!getMovesForPieceAt(piece.getPosition()).isEmpty()) {
                    return false;
                }
            }
        else
            for (Piece piece : chessBoard.getmap(turn))
                if (!getMovesForPieceAt(piece.getPosition()).isEmpty())
                    return false;
        return true;
    }

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
    public boolean pieceCanMove(Move move){
        Piece piecedest=chessBoard.getPieceAt(move.getDestinationPosition().getCol(),move.getDestinationPosition().getRaw());
        Piece piecestart=chessBoard.getPieceAt(move.getStartPosition().getCol(),move.getStartPosition().getRaw());
        if(piecedest!=null) {
            if (piecedest.getPlayer().getId() == piecestart.getPlayer().getId()) {
                if(!(move instanceof CastelingMove))
                    return false;
            }
        }
        if((move instanceof PawnAttack)){
            if (piecedest==null){
                if(piecestart.getPlayer().getId()==1)
                    piecedest=chessBoard.getPieceAt(move.getDestinationPosition().getCol(),move.getDestinationPosition().getRaw()+1);
                else
                    piecedest=chessBoard.getPieceAt(move.getDestinationPosition().getCol(),move.getDestinationPosition().getRaw()-1);
                if(!(piecedest instanceof Pawn))
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
    public boolean checkmove(Piece kings[],Move move){
        Position destinionposition= move.getDestinationPosition();
        Position startposition=move.getStartPosition();
        for(int i=0;i<2;i++){
            if(destinionposition.getRaw()==kings[i].getPosition().getRaw())
                if(destinionposition.getCol()==kings[i].getPosition().getCol()) {
                    return true;
                }
        }
        return false;
    }
    public boolean ischeck(Move move){
        Position destinionposition= move.getDestinationPosition();
        Position startposition=move.getStartPosition();
        selectedPiece=chessBoard.getPieceAt(move.getStartPosition().getCol(),move.getStartPosition().getRaw());
        Piece []kings= new King[2];
        kings[0]=chessBoard.getking(1);
        kings[1]=chessBoard.getking(2);
//        if(checkmove(kings,move))
//            return true;

//        chessBoard.replacePieceAt(destinionposition,selectedPiece);
//        kings[0]=chessBoard.getking(1);
//        kings[1]=chessBoard.getking(2);
//        for(int j=1;j<=2;j++) {
//            for (Piece piece : chessBoard.getmap(j)) {
////                if (piece != selectedPiece) {
//                    Position position=piece.getPosition();
//                    if ((position.getRaw()!= destinionposition.getRaw())||(position.getCol()!= destinionposition.getCol())) {
//                            for (Move move1 : piece.GenerateMoves(piece.getPosition())) {
//                                boolean flag =true;
//                                if (piece instanceof Pawn) {
//                                    if (!(move1 instanceof PawnAttack))
//                                        flag = false;
//                                }
//                                        if (!isjump(move1)&&flag)
//                                            if (pieceCanMove(move1))
//                                                if (checkmove(kings, move1)) {
//                                                    chessBoard.replacePieceAt(startposition, selectedPiece);
//                                                    return true;
//                                        }
//                            }
//                        }
//                //}
//            }
//        }
//        chessBoard.replacePieceAt(startposition,selectedPiece);
//        return false;



//        check attacking king
        for(int i=0;i<2;i++){
            if(destinionposition.getRaw()==kings[i].getPosition().getRaw())
                if(destinionposition.getCol()==kings[i].getPosition().getCol()) {
                    System.out.println(111111);
                    return true;
                }
        }
        chessBoard.replacePieceAt(destinionposition,selectedPiece);
        kings[0]=chessBoard.getking(1);
        kings[1]=chessBoard.getking(2);
        if (selectedPiece instanceof King){
            Position position=move.getDestinationPosition();
//            chessBoard.replacePieceAt(move.getDestinationPosition(),selectedPiece);
            if(selectedPiece.getPlayer().getId()==1)
                for (Piece piece : chessBoard.getmap(2)) {
                    for (Move move1 : piece.GenerateMoves(piece.getPosition())) {
                        if(move1.getDestinationPosition().getCol()==position.getCol())
                            if(move1.getDestinationPosition().getRaw()==position.getRaw()) {
                                boolean flag =true;
                                if (piece instanceof Pawn) {
                                    if (!(move1 instanceof PawnAttack))
                                        flag = false;
                                }
                                if (!isjump(move1)&&flag) {
                                    chessBoard.replacePieceAt(move.getStartPosition(), selectedPiece);
                                    System.out.println(222222);
                                    return true;
                                }
                            }
                    }
                }
            else
                for (Piece piece : chessBoard.getmap(1)) {
                    for (Move move1 : piece.GenerateMoves(piece.getPosition())) {
                        if(move1.getDestinationPosition().getCol()==position.getCol())
                            if(move1.getDestinationPosition().getRaw()==position.getRaw())
                                if(!isjump(move1)) {
                                    chessBoard.replacePieceAt(move.getStartPosition(),selectedPiece);
                                    System.out.println(333333);
                                    return true;
                                }
                    }
                }
        }
        else{
//            chessBoard.replacePieceAt(move.getDestinationPosition(),selectedPiece);
            if(selectedPiece.getPlayer().getId()==1)
                for (Piece piece : chessBoard.getmap(2)) {
                    for (Move move1 : piece.GenerateMoves(piece.getPosition())) {
                        if(move1.getDestinationPosition().getCol()==kings[0].getPosition().getCol())
                            if(move1.getDestinationPosition().getRaw()==kings[0].getPosition().getRaw())
                                if (pieceCanMove(move1)){
                                    boolean flag =true;
                                    if (piece instanceof Pawn) {
                                        if (!(move1 instanceof PawnAttack))
                                            flag = false;
                                    }
                                    if(!isjump(move1)&&flag) {
                                        if (move1.getStartPosition().getCol() != move.getDestinationPosition().getCol() || move1.getStartPosition().getRaw() != move.getDestinationPosition().getRaw()) {
//                                            if () {
                                            chessBoard.replacePieceAt(move.getStartPosition(), selectedPiece);
                                            System.out.println(444444);
                                            return true;
                                        }
//                                        }
                                    }
                                }
                    }
//                    chessBoard.replacePieceAt(move.getStartPosition(),selectedPiece);
                }
            else
                for (Piece piece : chessBoard.getmap(1)) {
                    for (Move move1 : piece.GenerateMoves(piece.getPosition())) {
                        if(move1.getDestinationPosition().getCol()==kings[1].getPosition().getCol())
                            if(move1.getDestinationPosition().getRaw()==kings[1].getPosition().getRaw()){
                                boolean flag =true;
                                    if (piece instanceof Pawn) {
                                        if (!(move1 instanceof PawnAttack))
                                            flag = false;
                                    }
                                if(!isjump(move1)&&flag) {
                                    if (move1.getStartPosition().getCol() != move.getDestinationPosition().getCol() || move1.getStartPosition().getRaw() != move.getDestinationPosition().getRaw()) {
//                                        if (move1.getStartPosition().getRaw()!=move.getDestinationPosition().getRaw()) {
                                        chessBoard.replacePieceAt(move.getStartPosition(), selectedPiece);
                                        System.out.println(5555555);
                                        return true;
                                    }
                                }
//                                        }
                                }
                    }
                }
//            chessBoard.replacePieceAt(move.getStartPosition(),selectedPiece);
        }
        chessBoard.replacePieceAt(move.getStartPosition(),selectedPiece);
        return false;
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

    public Set<Move> getMovesForPieceAt(Position position){
        Set<Move> tmps = chessBoard.getPieceAt(position.getCol(),position.getRaw()).GenerateMoves(position);
        Set<Move> correctmoves =new HashSet<>() ;
        for (Move tmp : tmps) {
            if(!isjump(tmp))
                if (!ischeck(tmp))
                    if(pieceCanMove(tmp)){
                        correctmoves.add(tmp) ;
            }
        }
        return correctmoves ;

    }
    public void makeMove(Move move){}

    public void rollback(){}



}
