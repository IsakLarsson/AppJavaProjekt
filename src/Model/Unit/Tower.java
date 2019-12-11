package Model.Unit;

import Model.XML.Area.Tile;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

import static java.awt.Color.red;

public class Tower {


    private int size = 20;
    private int dmg = 10;
    private int range = 50;
    //Position
    private int xCord;
    private int yCord;

    public Tower() {
    }



    public void setTileQueue(LinkedList<Tile> tiles) {

    }


    public void draw(Graphics g) {

    }

    public int getHp() {
        return 0;
    }

    public int getDmg() {
        return dmg;
    }

    public int getX() {
        return 0;
    }

    public int getY() {
        return 0;
    }

    public Image getImage() {
        return null;
    }

    public void move(Queue q) {

    }

    public LinkedList<Tile> getTiles() {
        return null;
    }

    public void drawProjectile (Graphics g) {

    }
}
