package Model.Unit;

import Model.XML.Area.Tile;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class Teleporter implements Unit{

    public Teleporter(){

    }

    @Override
    public void setTileQueue(LinkedList<Tile> tiles) {

    }

    @Override
    public int move(Graphics g) {
        return 0;
    }

    @Override
    public int getHp() {
        return 0;
    }

    @Override
    public int getDmg() {
        return 0;
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
    public void setPixelPositionQueue(Queue q) {

    }

    @Override
    public LinkedList<Tile> getPath() {
        return null;
    }

    @Override
    public void setHp(int hp) {

    }

    @Override
    public Queue getQueue() {
        return null;
    }

    @Override
    public void setGoal(Tile goal) {

    }
}
