package model;

import javafx.scene.image.Image;

import java.util.Set;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public class Pawn extends Piece {
    public Pawn(Player player, Position position) {
        super(player, position);

    }

    @Override
    public Set<Move> GenerateMoves(Position CurrentPosition) {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }
}
