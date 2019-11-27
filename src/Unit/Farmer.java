package Unit;

import java.awt.*;

import static java.awt.Color.white;

public class Farmer implements Unit {
    //Position
    int x,y;

    public Farmer(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g, int x, int y) {
        g.setColor(white);
        g.fill3DRect(x * UNITSIZE, y * UNITSIZE,
                UNITSIZE, UNITSIZE, true);
    }
}
