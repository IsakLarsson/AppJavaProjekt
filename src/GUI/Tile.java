package GUI;

import Model.Position;

import java.awt.*;

public class Tile {

    private Position position;
    private int xSize;
    private int ySize;

    private Color color;

    //add more attributes like path, towerarea, goal, start etc

    public Tile(int xCoordinate, int yCoordinate, int xSize,
                int ySize, Color color){

        position = new Position(xCoordinate, yCoordinate);
        this.color = color;
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public int getxCoordinate() {
        return position.getX();
    }

    public int getyCoordinate() {
        return position.getY();
    }

    public Color getColor() {
        return color;
    }

    public int getxSize() {
        return xSize;
    }

    public int getySize() {
        return ySize;
    }
}
