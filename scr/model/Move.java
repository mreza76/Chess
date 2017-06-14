package model;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public class Move {
    private Position startPosition;
    private Position destinationPosition;
    Move(Position startPosition,Position destinationPosition){
        this.destinationPosition=destinationPosition;
        this.startPosition=startPosition;
    }
    @Override
    public String toString() {
        return super.toString();
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Position startPosition) {
        this.startPosition = startPosition;
    }

    public Position getDestinationPosition() {
        return destinationPosition;
    }

    public void setDestinationPosition(Position destinationPosition) {
        this.destinationPosition = destinationPosition;
    }
}
