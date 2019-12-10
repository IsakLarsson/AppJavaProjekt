package Model.XML.Area;

import java.awt.*;

public class Path extends Tile {

    public Path(int xCoordinate, int yCoordinate, int size) {
        super(xCoordinate, yCoordinate, size);
        setColor(Color.orange);
    }

    public Path(Tile tile) {
        super(tile.getxCoordinate(),tile.getyCoordinate(),tile.getSize());
    }

}
