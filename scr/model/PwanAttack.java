package model;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public class PwanAttack extends Move{
    private boolean isEnPassant;
    private Position enPassantCapturePosition;

    PwanAttack(Position startPosition, Position destinationPosition) {
        super(startPosition, destinationPosition);
    }

    public PwanAttack() {
    }
}
