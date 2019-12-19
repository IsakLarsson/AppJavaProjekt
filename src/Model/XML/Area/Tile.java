package Model.XML.Area;

import Model.Logic.Position;

import java.awt.*;

public class Tile {

    private Position position;
    private int size;
    private Color color;
    private Image texture;

    //add more attributes like path, towerarea, goal, start etc

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
