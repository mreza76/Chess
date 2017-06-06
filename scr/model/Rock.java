package model;

import javafx.scene.image.Image;

import java.util.Set;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public class Rock extends Piece {
    public Rock(Player player, Position position) {
        super(player, position);
        setImage("file:scr\\view\\pieces\\rock"+player.getId()+".png");
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
