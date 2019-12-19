package Model.XML.Area;

import Model.Logic.Position;

import java.awt.*;

/**
 * 5DV135 - Application Development in Java
 * Department of Computing Science, Umeå University
 *
 * The abstract class from which every Area extends.
 *
 * @version 1.0 18 December 2019
 * @author Isak Larsson <id17ian@cs.umu.se>
 */

public class Tile {

    private Position position;
    private int size;
    private Color color;
    private Image texture;

    /**
     * Constructor for the Tile.
     * @param xCoordinate x coordinate where tile should spawn.
     * @param yCoordinate y coordinate where tile should spawn.
     * @param size Size of the tile representation.
     */
    public Tile(int xCoordinate, int yCoordinate, int size){

        this.position = new Position(xCoordinate, yCoordinate);
        this.size = size;
    }

    public int getxCoordinate() {
        return position.getX();
    }

    public int getyCoordinate() {
        return position.getY();
    }

    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Image getTexture() {
        return texture;
    }
}
