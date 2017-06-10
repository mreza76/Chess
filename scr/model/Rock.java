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
        int i ;
        Set<Move> availableMoves=new HashSet<>() ;
        for (i=CurrentPosition.getRaw()+1;i<8;i++){
            Position position =new Position(i,CurrentPosition.getCol()) ;
            Move move=new Move(CurrentPosition,position) ;
            availableMoves.add(move) ;
        }
        for (i=CurrentPosition.getRaw()-1;i>=0;i--){
            Position position =new Position(i,CurrentPosition.getCol()) ;
            Move move=new Move(CurrentPosition,position) ;
            availableMoves.add(move) ;
        }
        for (i=CurrentPosition.getCol()+1;i<8;i++){
            Position position =new Position(CurrentPosition.getRaw(),i) ;
            Move move=new Move(CurrentPosition,position) ;
            availableMoves.add(move) ;
        }
        for (i=CurrentPosition.getCol()-1;i>=0;i--){
            Position position =new Position(CurrentPosition.getRaw(),i) ;
            Move move=new Move(CurrentPosition,position) ;
            availableMoves.add(move) ;
        }
        return availableMoves ;
    }

    @Override
    public String toString() {
        return null;
    }
}
