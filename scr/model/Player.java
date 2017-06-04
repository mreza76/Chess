package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public class Player {
    private int id;
    private String name;
    private List<Piece> pieces;

    public Player(int id,String name) {
        this.id = id;
        this.name=name;
    }

    public void setPiece(Piece piece){
        pieces.add(piece);
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public int getId() {
        return id;
    }
    public String getName(){
        return name;
    }
}
