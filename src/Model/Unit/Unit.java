package Model.Unit;

import Model.XML.Area.Tile;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;


public interface Unit {


    void setTileQueue(LinkedList<Tile> tiles);

    int move(Graphics g);

    int getHp();

    int getDmg();

    int getX();

    int getY();

    Image getImage();

    void setPixelPositionQueue(Queue q);

    LinkedList<Tile> getPath();

    void setHp(int hp);

    Queue getQueue();

    void setGoal(Tile goal);
}
