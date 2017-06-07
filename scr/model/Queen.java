package model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public class Queen extends Piece {
    public Queen(Player player, Position position) {
        super(player, position);
        setImage("file:scr\\view\\pieces\\queen"+player.getId()+".png");
    }

    @Override
    public Set<Move> GenerateMoves(Position CurrentPosition) {
        Set<Move> availabelMoves=new HashSet<>() ;

        //horizontal
        int i ;
        for (i=CurrentPosition.getRaw()+1;i<8;i++){
            Position position=new Position(i,CurrentPosition.getCol()) ;
            Move move=new Move(CurrentPosition,position) ;
            availabelMoves.add(move) ;
        }
        for (i=CurrentPosition.getRaw()+1;i>=0;i--){
            Position position=new Position(i,CurrentPosition.getCol()) ;
            Move move=new Move(CurrentPosition,position) ;
            availabelMoves.add(move) ;
        }
        int j ;
        //vertical
        for (j=CurrentPosition.getCol()+1;j<8;j++){
            Position position=new Position(j,CurrentPosition.getCol()) ;
            Move move=new Move(CurrentPosition,position) ;
            availabelMoves.add(move) ;
        }
        for (j=CurrentPosition.getCol()+1;j>=0;j--){
            Position position=new Position(j,CurrentPosition.getCol()) ;
            Move move=new Move(CurrentPosition,position) ;
            availabelMoves.add(move) ;
        }
        //skew
        i=CurrentPosition.getRaw()+1 ;
        j=CurrentPosition.getCol()+1 ;
        while (!(i>7)&&!(j>7)){
            Position position=new Position(i,j) ;
            Move move=new Move(CurrentPosition,position) ;
            availabelMoves.add(move) ;
            i++;
            j++;
        }
        while (!(i>7)&&!(j<0)){
            Position position=new Position(i,j) ;
            Move move=new Move(CurrentPosition,position) ;
            availabelMoves.add(move) ;
            i++;
            j--;
        }
        while (!(i<0)&&!(j>7)){
            Position position=new Position(i,j) ;
            Move move=new Move(CurrentPosition,position) ;
            availabelMoves.add(move) ;
            i--;
            j++;
        }
        while (!(i<0)&&!(j<0)) {
            Position position = new Position(i, j);
            Move move = new Move(CurrentPosition, position);
            availabelMoves.add(move);
            i--;
            j--;
        }
        return availabelMoves ;
    }

    @Override
    public String toString() {
        return null;
    }
}
