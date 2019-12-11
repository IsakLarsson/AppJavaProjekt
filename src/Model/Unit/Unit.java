package Model.Unit;

import Model.XML.Area.Tile;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;


public interface Unit {


    void setTileQueue(LinkedList<Tile> tiles);

    void draw(Graphics g);

    int getHp();

    int getDmg();

    int getX();

    int getY();

    Image getImage();

    void setPixelPositionQueue(Queue q);

    LinkedList<Tile> getPath();
}
