package Model.Unit;

import java.awt.*;

import static java.awt.Color.red;

public class Tower implements Unit {

    //Position
    private int size = 20;
    private int dmg = 10;
    private int x,y;

    public Tower() {
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        g.setColor(red);
        g.fillRect(x * size, y * size,
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
