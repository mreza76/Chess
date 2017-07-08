package model;

/**
 * Created by amirsaeed on 7/8/2017.
 */
public class CastelingMove extends Move {
    private boolean iscasteling;
    private Position kingposition;
    private Position Rockposition;

    public boolean isIscasteling() {
        return iscasteling;
    }

    public void setIscasteling(boolean iscasteling) {
        this.iscasteling = iscasteling;
    }

    public Position getKingposition() {
        return kingposition;
    }

    public void setKingposition(Position kingposition) {
        this.kingposition = kingposition;
    }

    public Position getRockposition() {
        return Rockposition;
    }

    public void setRockposition(Position rockposition) {
        Rockposition = rockposition;
    }

    CastelingMove(Position startPosition, Position destinationPosition) {
        super(startPosition, destinationPosition);
    }
}
