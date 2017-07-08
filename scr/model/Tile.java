package model;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
    private int width=80;
    private int height=80;
    private Piece piece;
    private StackPane pane= new StackPane();
    private Background background;
    private boolean gotpiece;
    private Rectangle rectangle;
    public void Highlight(){
        background=new Background(new BackgroundFill(Color.CORNFLOWERBLUE,  new CornerRadii(0), new Insets(0)));
        pane.setBackground(background);
    }

    public void HighlightAttack(){
        background=new Background(new BackgroundFill(Color.RED,  new CornerRadii(0), new Insets(0)));
        pane.setBackground(background);
    }
    public Tile(Position position){
        //set position
        rectangle= new Rectangle(width,height);
        rectangle.setHeight(50);
        rectangle.setWidth(50);
        this.position=position;
        int raw = position.getRaw();
        int col = position.getCol();
        //set color
        if ((raw+col)%2==0){
            color=Color.MOCCASIN;
        }
        else{
            color=Color.CHOCOLATE;
        }
        background = new Background(new BackgroundFill(color,  new CornerRadii(0), new Insets(0)));
        rectangle.setFill(Color.TRANSPARENT);
        pane.setBackground(background);
        pane.getChildren().add(rectangle);

    }
    public Piece getPiece(){
        try {
            return piece;
        }catch (NullPointerException e){
            return null ;
        }

    }

    public void setPiece(Piece piece){
        this.piece = piece;
//        pane.getChildren().add(new ImageView(piece.getImage()));
        rectangle.setFill(new ImagePattern(piece.getImage()));
    }

    public void setPosition(Position position){
        this.position=position;
    }
    public void removepieice(){
//        pane.getChildren().clear();
        rectangle.setFill(Color.TRANSPARENT);
        piece=null;
    }
    //change the color of tile when tile is selected
    public void selected(){
        background=new Background(new BackgroundFill(Color.LIGHTBLUE,  new CornerRadii(0), new Insets(0)));
        pane.setBackground(background);
    }
    //reset the color of the tile
    public void unselected(){
        background=new Background(new BackgroundFill(color,  new CornerRadii(0), new Insets(0)));
        pane.setBackground(background);
    }
    public Position getPosition(){
        return position;
    }
    //get tile view
    public Node getPane() {
        pane.getChildren().addAll(new Rectangle(width, height, Color.TRANSPARENT));
        return pane;
    }
    //say if the tile got any piece in it or not
    public boolean isGotpiece() {
        if (piece!=null)
            gotpiece=true;
        else
            gotpiece=false;
        return gotpiece;
    }
}
