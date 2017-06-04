package model;

import java.util.Set;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public class Queen extends Piece {
    public Queen(Player player, Position position) {
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
