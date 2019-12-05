package Model.Unit;

import java.awt.*;

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
    public void draw(Graphics g, int x, int y) {
        g.setColor(red);
        g.fillRect(xCord * size, yCord * size,
                size, size);
    }

    @Override
    public void takeDMG(int DMG) {

    }

    @Override
    public int getHp() {
        return 0;
    }

    @Override
    public int getDmg() {
        return dmg;
    }

    public void drawProjectile (Graphics g) {

    }
}
