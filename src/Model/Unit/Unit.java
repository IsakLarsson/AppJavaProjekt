package Model.Unit;

import java.awt.*;

public interface Unit {


    void draw(Graphics g);

    void takeDMG(int DMG);

    int getHp();

    int getDmg();

    Image getImage();



}
