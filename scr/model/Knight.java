package model;
import javafx.scene.image.Image;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public class Knight extends Piece {
    public Knight(Player player, Position position) {
        super(player, position);
        setImage("file:scr\\view\\pieces\\knight"+player.getId()+".png");
    }

    @Override
    public Set<Move> GenerateMoves(Position CurrentPosition) {
        Set<Move> availabelMoves = new HashSet<>() ;

        int y = CurrentPosition.getRaw();
        int x = CurrentPosition.getCol() ;

        //...knight can move on eight place...//

        if ( (y+2 < 8) && (x+1 <8)){
         Position position = new Position(y+2 ,x+1) ;
         Move move = new Move(CurrentPosition,position);
            availabelMoves.add(move);
        }

        if ( (y+2 < 8) && (x-1 >= 0) ){
            Position position = new Position(y+2 ,x-1) ;
            Move move = new Move(CurrentPosition,position);
            availabelMoves.add(move);
        }
        if ( (y-2 >= 0 ) && ( x-1 >= 0) ){
            Position position = new Position(y-2 ,x-1) ;
            Move move = new Move(CurrentPosition,position);
            availabelMoves.add(move);
        }
        if ( (y-2  >= 0) && (x+1 <8) ){
            Position position = new Position(y-2 ,x+1) ;
            Move move = new Move(CurrentPosition,position);
            availabelMoves.add(move);
        }
        if ((y+1 <8 ) && (x+2 < 8)){
            Position position = new Position(y+1 ,x+2) ;
            Move move = new Move(CurrentPosition,position);
            availabelMoves.add(move);
        }
        if ((y+1 < 8) && (x-2 >= 0 )){
            Position position = new Position(y+1 ,x-2) ;
            Move move = new Move(CurrentPosition,position);
            availabelMoves.add(move);
        }
        if ((y-1 >= 0) && (x+2 < 8 )){
            Position position = new Position(y-1 ,x+2) ;
            Move move = new Move(CurrentPosition,position);
            availabelMoves.add(move);
        }
        if ((y-1 >= 0) && (x-2 >= 0)){
            Position position = new Position(y-1 ,x-2) ;
            Move move = new Move(CurrentPosition,position);
            availabelMoves.add(move);
        }
            return availabelMoves;
    }

    @Override
    public String toString() {
        return null;
    }
}
