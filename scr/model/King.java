package model;
import javafx.scene.image.Image;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public class King extends Piece {
    public King(Player player, Position position) {
        super(player, position);
        setImage("file:scr\\view\\pieces\\king"+player.getId()+".png");
    }

    @Override
    public Set<Move> GenerateMoves(Position CurrentPosition)
    {
        Set<Move> availabelMoves = new HashSet<>();

        int y = CurrentPosition.getCol();
        int x = CurrentPosition.getRaw();
        if((getPlayer().getId()==1&&y==4&&x==7)||(getPlayer().getId()==2&&y==4&&x==0)){
            Position position = new Position(y-4,x);
            CastelingMove castelingMove = new CastelingMove(CurrentPosition,position);
            position = new Position(y-2,x);
            ((CastelingMove)castelingMove).setKingposition(position);
            position = new Position(y-1,x);
            ((CastelingMove)castelingMove).setRockposition(position);
            availabelMoves.add(castelingMove);
            position = new Position(y+3,x);
            castelingMove = new CastelingMove(CurrentPosition,position);
            position = new Position(y+2,x);
            ((CastelingMove)castelingMove).setKingposition(position);
            position = new Position(y+1,x);
            ((CastelingMove)castelingMove).setRockposition(position);
            availabelMoves.add(castelingMove);
        }
        if (x+1 < 8){
            Position position = new Position(y,x+1);
            Move move = new Move(CurrentPosition,position);
            availabelMoves.add(move);
        }
        if (x-1 >=0){
            Position position = new Position(y , x-1);
            Move move = new Move(CurrentPosition,position);
            availabelMoves.add(move);
        }
        if ( y+1 < 8){
            Position position = new Position(y+1 ,x);
            Move move = new Move(CurrentPosition,position);
            availabelMoves.add(move);
        }
        if (y-1 >= 0){
            Position position=new Position(y-1,x);
            Move move = new Move(CurrentPosition,position);
            availabelMoves.add(move);
        }
        if (y-1 >=0 && x-1 >= 0){
            Position position = new Position(y-1,x-1);
            Move move = new Move(CurrentPosition,position);
            availabelMoves.add(move);
        }
        if (y+1 <8 && x-1 >=0){
            Position position = new Position(y+1,x-1);
            Move move = new Move(CurrentPosition,position);
            availabelMoves.add(move);
        }
        if ( y-1 >=0 && x+1 <8){
            Position position = new Position(y-1,x+1);
            Move move = new Move(CurrentPosition,position);
            availabelMoves.add(move);
        }
        if (y+1 <8 && x+1 <8){
            Position position = new Position(y+1,x+1);
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
