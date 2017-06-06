package model;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.scene.text.Font;

import java.time.Instant;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public class Tile {
    private Position position;
    private Color color;
    private Piece piece;
    private StackPane pane= new StackPane();
    public Tile(Position position){
        this.position=position;
        int raw = position.getRaw();
        int col = position.getCol();
        if ((raw+col)%2==0){
            color=Color.WHITE;
        }
        else{
            color=Color.BLACK;
        }
        Background background = new Background(new BackgroundFill(color,  new CornerRadii(0), new Insets(0)));
        pane.setBackground(background);

    }
    public Piece getPiece(){
        return piece;
    }
    public void setPiece(Piece piece){
        this.piece=piece;
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(new ImagePattern(piece.getImage()));
    }
    public void setPosition(Position position){

    }
    public Position getPosition(){
        return position;
    }

    public Node getPane() {
        pane.getChildren().clear();
        pane.getChildren().addAll(new Rectangle(100, 100, Color.TRANSPARENT));
        return pane;
    }
}
