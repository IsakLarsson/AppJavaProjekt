package Model.Unit;

import Model.XML.Area.Tile;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

import static java.awt.Color.red;

public class Tower implements Unit {


    private int size = 20;
    private int dmg = 10;
    private int range = 50;
    //Position
    private int xCord;
    private int yCord;

    public Tower() {
    }



    @Override
    public void setTileQueue(LinkedList<Tile> tiles) {

    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public int getHp() {
        return 0;
    }

    @Override
    public int getDmg() {
        return dmg;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public Image getImage() {
        return null;
    }

    @Override
    public void move(Queue q) {

    }

    @Override
    public LinkedList<Tile> getTiles() {
        return null;
    }

    public void drawProjectile (Graphics g) {

    }
}
