package model;

/**
 * Created by amirsaeed on 6/2/2017.
 */
public class Position {
    private int raw;
    private int col;

    public int getRaw() {
        return raw;
    }

    public int getCol() {
        return col;
    }

    public Position(int col, int raw) {
        this.raw = raw;
        this.col = col;
    }
}
