package model;

import javafx.scene.image.Image;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public class Pawn extends Piece {
    public Pawn(Player player, Position position) {
        super(player, position);
        setImage("file:scr\\view\\pieces\\pawn"+player.getId()+".png");
    }

    @Override
    public Set<Move> GenerateMoves(Position CurrentPosition) {
        int i=CurrentPosition.getRaw() ;
        Set<Move> availbaleMoves=new HashSet<>() ;

        //Moves
        Position position=new Position(i+1,CurrentPosition.getCol()) ;
        Move move=new Move(CurrentPosition,position) ;
        availbaleMoves.add(move) ;
        position=new Position(i+2,CurrentPosition.getCol()) ;
        move=new Move(CurrentPosition,position) ;
        availbaleMoves.add(move) ;
        position=new Position(i-1,CurrentPosition.getCol()) ;
        move=new Move(CurrentPosition,position) ;
        availbaleMoves.add(move) ;
        position=new Position(i-2,CurrentPosition.getCol()) ;
        move=new Move(CurrentPosition,position) ;
        availbaleMoves.add(move) ;
        //Attacks
        position=new Position(i-1,CurrentPosition.getCol()-1) ;
        PwanAttack pwanAttack=new PwanAttack(CurrentPosition,position) ;
        availbaleMoves.add(pwanAttack) ;
        position=new Position(i-1,CurrentPosition.getCol()+1) ;
        pwanAttack=new PwanAttack(CurrentPosition,position) ;
        availbaleMoves.add(pwanAttack) ;
        position=new Position(i+1,CurrentPosition.getCol()+1) ;
        pwanAttack=new PwanAttack(CurrentPosition,position) ;
        availbaleMoves.add(pwanAttack) ;
        position=new Position(i+1,CurrentPosition.getCol()-1) ;
        pwanAttack=new PwanAttack(CurrentPosition,position) ;
        availbaleMoves.add(pwanAttack) ;
        return availbaleMoves ;
    }

    @Override
    public String toString() {
        return null;
    }
}
