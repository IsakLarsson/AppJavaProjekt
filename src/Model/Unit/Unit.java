package Model.Unit;

import java.awt.*;

public interface Unit {

    int UNITSIZE = 20, FARMERCOST = 10,
            SOLDIERCOST = 15, TELEPORTERcost = 20;


    void draw(Graphics g, int x, int y);
}
