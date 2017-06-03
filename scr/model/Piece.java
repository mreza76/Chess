package model;

import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

import java.util.Set;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public abstract class Piece {
    private Player player;
    private Position position;
    private int id;
    private Image image;
    Piece(Player player, Position position){}

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    public abstract Set<Move>GenerateMoves(Position CurrentPosition);

    @Override
    public abstract String toString();
}
