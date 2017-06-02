package model;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public class Tile {
    private Position position;
    private Color color;
    private Piece piece;
    Tile(Position position){}
    public Piece getPiece(){
        return piece;
    }
    public void setPiece(Piece piece){
        this.piece=piece;
    }
    public void setPosition(Position position){}
    public Position getPosition(){
        return position;
    }
}
