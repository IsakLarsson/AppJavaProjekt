package Model;

import java.util.Objects;
import java.util.Stack;

public class Position {
    private int x;
    private int y;

    /**
     * Constructs a new position and sets its coordinates
     *
     * @param x X-value
     * @param y Y-value
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the current x-value
     *
     * @return X-value of this position
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the current y-value
     *
     * @return Y-value of this position
     */
    public int getY() {
        return y;
    }

    /**
     * Finds the position south of a given position
     *
     * @return The position south of a given position
     */
    public Position getPosToSouth() {

        Position p = new Position(x, y + 1);
        return p;
    }

    /**
     * Finds the position north of a given position
     *
     * @return The position north of a given position
     */
    public Position getPosToNorth() {
        Position p = new Position(x, y - 1);
        return p;
    }

    /**
     * Finds the position west of a given position
     *
     * @return The position west of a given position
     */
    public Position getPosToWest() {
        Position p = new Position(x - 1, y);
        return p;
    }

    /**
     * Finds the position east of a given position
     *
     * @return The position east of a given position
     */
    public Position getPosToEast() {
        Position p = new Position(x + 1, y);
        return p;
    }

}


