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
    private Image image;
    Piece(Player player, Position position){
        this.player=player;
        this.position=position;
    }

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
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    public abstract Set<Move>GenerateMoves(Position CurrentPosition);

    @Override
    public String toString() {
        return "Piece{" +
                "player=" + player.getName() +
                ", position=" + position +
                '}';
    }
}
