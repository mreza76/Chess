package model;
import javafx.scene.image.Image;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by amirsaeed on 6/2/2017.
 */
public class Pawn extends Piece {
    Position position=new Position() ;
    Move move=new Move() ;
    PawnAttack pawnAttack;
    private boolean end=false ;

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public Pawn(Player player, Position position) {
        super(player, position);
        setImage("file:scr\\view\\pieces\\pawn"+player.getId()+".png");
        this.setPlayer(player);
    }
    @Override
    public Set<Move> GenerateMoves(Position CurrentPosition) {
        int i=CurrentPosition.getRaw() ;
        Set<Move> availbaleMoves=new HashSet<>() ;
        //Moves

        if(getPlayer().getId()==2) {
            if (CurrentPosition.getRaw()+1 < 8){
            position = new Position(CurrentPosition.getCol(),CurrentPosition.getRaw()+1 );
            move = new Move(CurrentPosition, position);
            availbaleMoves.add(move);
            }
            if (CurrentPosition.getRaw() == 1){
            position = new Position(CurrentPosition.getCol(),CurrentPosition.getRaw()+2 );
            move = new Move(CurrentPosition, position);
            availbaleMoves.add(move);
            }
        }
        else {
            if (CurrentPosition.getRaw()-1 >= 0){
            position = new Position(CurrentPosition.getCol(),CurrentPosition.getRaw()-1 );
            move = new Move(CurrentPosition, position);
            availbaleMoves.add(move);
            }
            if (CurrentPosition.getRaw() == 6 ){
            position = new Position(CurrentPosition.getCol(),CurrentPosition.getRaw()-2 );
            move = new Move(CurrentPosition, position);
            availbaleMoves.add(move);
            }
        }

        // check Attack Condition

        if (getPlayer().getId()==1) {
            if (CurrentPosition.getCol()-1 >= 0 && CurrentPosition.getRaw()-1 >= 0){
            position = new Position(CurrentPosition.getCol()-1, CurrentPosition.getRaw() - 1);
            pawnAttack = new PawnAttack(CurrentPosition, position);
            availbaleMoves.add(pawnAttack);
            }
            if (CurrentPosition.getRaw()-1 >= 0 && CurrentPosition.getCol()+1 < 8){
            position = new Position(CurrentPosition.getCol()+1, CurrentPosition.getRaw() - 1);
            pawnAttack = new PawnAttack(CurrentPosition, position);
            availbaleMoves.add(pawnAttack);
            }
        }
        else {
            if (CurrentPosition.getCol()+1 < 8 && CurrentPosition.getRaw()+1 < 8){
            position = new Position(CurrentPosition.getCol() + 1, CurrentPosition.getRaw() + 1);
            pawnAttack = new PawnAttack(CurrentPosition, position);

            availbaleMoves.add(pawnAttack);
            }
            if (CurrentPosition.getRaw()+1 <8 && CurrentPosition.getCol()-1 >= 0) {
                position = new Position(CurrentPosition.getCol() - 1, CurrentPosition.getRaw() + 1);
                pawnAttack = new PawnAttack(CurrentPosition, position);
                availbaleMoves.add(pawnAttack);
            }
        }
        return availbaleMoves ;
    }
    @Override
    public String toString() {
        return null;
    }
}
