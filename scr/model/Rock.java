package model;

import javafx.scene.image.Image;

import java.lang.management.MemoryManagerMXBean;
import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public class Rock extends Piece {
    public Rock(Player player, Position position) {
        super(player, position);
        setImage("file:scr\\view\\pieces\\rock"+player.getId()+".png");
    }

    @Override
    public Set<Move> GenerateMoves(Position CurrentPosition) {
        Set<Move> availabelMoves=new HashSet<>() ;
        int i ;
        for (i=CurrentPosition.getRaw()+1;i<8;i++){
            Position position=new Position(CurrentPosition.getCol(),i) ;
            Move move=new Move(CurrentPosition,position) ;
            availabelMoves.add(move) ;
        }
        for (i=CurrentPosition.getRaw()-1;i>=0;i--){
            Position position=new Position(CurrentPosition.getCol(),i) ;
            Move move=new Move(CurrentPosition,position) ;
            availabelMoves.add(move) ;
        }
        int j ;
        //vertical
        for (j=CurrentPosition.getCol()+1;j<8;j++){
            Position position=new Position(j,CurrentPosition.getRaw()) ;
            Move move=new Move(CurrentPosition,position) ;
            availabelMoves.add(move) ;
        }
        for (j=CurrentPosition.getCol()-1;j>=0;j--){
            Position position=new Position(j,CurrentPosition.getRaw()) ;
            Move move=new Move(CurrentPosition,position) ;
            availabelMoves.add(move) ;
        }
        return availabelMoves ;
    }

    @Override
    public String toString() {
        return null;
    }
}
