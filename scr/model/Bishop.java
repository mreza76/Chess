package model;

import javafx.scene.image.Image;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public class Bishop extends Piece {
    int i , j ;
    public Bishop(Player player, Position position) {
        super(player, position);
        setImage("file:scr\\view\\pieces\\bishop"+player.getId()+".png");
    }

    @Override
    public Set<Move> GenerateMoves(Position CurrentPosition) {
        Set<Move> availabelMoves=new HashSet<>() ;


            i=CurrentPosition.getRaw()+1 ;
            j=CurrentPosition.getCol()+1 ;
            while (!(i > 7) && !(j > 7)) {
                Position position = new Position(j, i);
                Move move = new Move(CurrentPosition, position);
                availabelMoves.add(move);
                i++;
                j++;
            }
            i=CurrentPosition.getRaw()+1 ;
            j=CurrentPosition.getCol()-1 ;
            while (!(i > 7) && !(j < 0)) {
                Position position = new Position(j, i);
                Move move = new Move(CurrentPosition, position);
                availabelMoves.add(move);
                i++;
                j--;
            }

            i=CurrentPosition.getRaw()-1 ;
            j=CurrentPosition.getCol()+1 ;
            while (!(i < 0) && !(j > 7)) {
                Position position = new Position(j, i);
                Move move = new Move(CurrentPosition, position);
                availabelMoves.add(move);
                i--;
                j++;
            }

            i=CurrentPosition.getRaw()-1 ;
            j=CurrentPosition.getCol()-1 ;
            while (!(i < 0) && !(j < 0)) {
                Position position = new Position(j, i);
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
