package Model.Unit;

import java.awt.*;

public class Soldier implements Unit {

    private int speed = 10;
    private int hp = 100;
    private int dmg = 5;

    public Soldier(){

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
