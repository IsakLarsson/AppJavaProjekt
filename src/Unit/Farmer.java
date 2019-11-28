package Unit;

import java.awt.*;

import static java.awt.Color.white;

public class Farmer implements Unit {

    //Position
    int x,y;

    public Farmer() {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        g.setColor(white);
        g.fillRect(x, y,
                UNITSIZE, UNITSIZE);
    }
}
