package Model.Unit;

import java.awt.*;

public interface Unit {


    void draw(Graphics g, int x, int y);

    int getHp();

    int getDmg();

    int getX();

    int getY();

    Image getImage();

    void move();
}
