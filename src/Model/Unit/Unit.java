package Model.Unit;

import java.awt.*;

public interface Unit {


    void draw(Graphics g, int x, int y);

    void takeDMG(int DMG);

    int getHp();

    int getDmg();



}
