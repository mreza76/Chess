package model;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public class Move {
    private Position startPosition;
    private Position destinationPosition;
    public Move(Position startPosition, Position destinationPosition){
        this.destinationPosition=destinationPosition;
        this.startPosition=startPosition;
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

    @Override
    public String toString() {
        return startPosition.toString() + destinationPosition.toString();
    }

    public Move() {
    }
}
