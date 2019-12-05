package Model.Unit;

import java.awt.*;

import static java.awt.Color.red;

public class Tower implements Unit {

    //Position
    int x,y;

    public Tower() {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        g.setColor(red);
        g.fillRect(x * UNITSIZE, y * UNITSIZE,
                UNITSIZE, UNITSIZE);
    }

    public void drawProjectile (Graphics g) {

    }
}
