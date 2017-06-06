package model;

import javafx.scene.image.Image;

import java.util.Set;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public class Bishop extends Piece {
    public Bishop(Player player, Position position) {
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
