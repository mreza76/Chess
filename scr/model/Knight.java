package model;

import javafx.scene.image.Image;

import java.util.Set;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public class Knight extends Piece {
    public Knight(Player player, Position position) {
        super(player, position);
        setImage("file:scr\\view\\pieces\\knight"+player.getId()+".png");
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
