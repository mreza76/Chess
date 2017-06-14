package contoroller;

import model.Move;
import model.Piece;
import model.Player;
import model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GameController {
    private ArrayList<Move> moves=new ArrayList<>();
    private ChessBoard chessBoard;
    private Piece selectedPiece;
    private Player currentPlayer;
    GameController(ChessBoard chessBoard){
        this.chessBoard=chessBoard;
    }
    private void Castling(Piece p, Move m){}
    public boolean isInCheck(Player player){
        return false;
    }
    public boolean pieceCanMove(Move move, Player movingPlayer){
        return false;
    }
    public boolean isGameOver(){
        return false;
    }
    public void startGame(){}
    public void beginTurn(){}
    public void endTurn(){}
    public Set<Move> getMovesForPieceAt(Position position){
        return chessBoard.getPieceAt(position.getCol(),position.getRaw()).GenerateMoves(position);
    }
    public void makeMove(Move move){}
    public void rollback(){}



}
