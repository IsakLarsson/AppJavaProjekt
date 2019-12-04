package XML;

import GUI.Tile;

import java.awt.*;

public class TowerArea extends Tile {

    public TowerArea(int xCoordinate, int yCoordinate, int size) {
        super(xCoordinate, yCoordinate, size);
        setColor(Color.red);
    }
}
