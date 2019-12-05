package Model.Unit;

import java.awt.*;

import static java.awt.Color.white;

public class Farmer implements Unit {


    private int hp = 50;
    private int speed = 20;
    private int dmg = 1;
    private int size = 20;

    //Position
    private int x,y;

    public Farmer() {
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void takeDMG(int DMG) {
        hp = hp - DMG;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public int getDmg() {
        return dmg;
    }

    public int getSpeed(){
        return speed;
    }
}
