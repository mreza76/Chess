package model;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public class PawnAttack extends Move{
    private boolean isEnPassant;
    private Position enPassantCapturePosition;

    public boolean isEnPassant() {
        return isEnPassant;
    }

    public void setEnPassant(boolean enPassant) {
        isEnPassant = enPassant;
    }

    public Position getEnPassantCapturePosition() {
        return enPassantCapturePosition;
    }

    public void setEnPassantCapturePosition(Position enPassantCapturePosition) {
        this.enPassantCapturePosition = enPassantCapturePosition;
    }

    PawnAttack(Position startPosition, Position destinationPosition) {
        super(startPosition, destinationPosition);
    }

}
