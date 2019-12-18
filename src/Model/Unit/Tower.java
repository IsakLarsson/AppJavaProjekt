package Model.Unit;

import Model.XML.Area.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import static java.awt.Color.RED;
import static java.awt.Color.red;

public class Tower {
    private int size = 20;
    private int dmg = 1;
    private int range = 100;
    private Image image;
    private Unit target;
    //Position
    private int xCord;
    private int yCord;

    public Tower() {}

    public Tower(int x, int y) {
        this.xCord = x;
        this.yCord = y;

        try {
            image = ImageIO.read(
                    this.getClass().getResourceAsStream("/GUI/images/Tower" +
                            ".jpg"));
            image = image.getScaledInstance(40,40,Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void shoot(Graphics graphics, Unit unit) {
        double a = Math.abs(xCord-unit.getX());
        double b = Math.abs(yCord-unit.getY());
        double distance = a*a + b*b;

        distance = Math.sqrt(distance); //here’s the hypotenuse.

        if (range >= distance) {

            if (target == null) {

                target = unit;

            }

            graphics.setColor(RED);
            graphics.drawLine(xCord + 10 ,yCord + 10,target.getX() + 10,
                    target.getY() + 10);
            target.setHp(target.getHp()-dmg);

            if (target.getHp() <= 0) {
                target = null;
            }

        }
        else {
            target = null;
        }
    }





    public void draw(Graphics g) {
        g.drawImage(image, xCord, yCord, null);
    }

    public int getHp() {
        return 0;
    }

    public int getDmg() {
        return dmg;
    }

    public int getX() {
        return 0;
    }

    public int getY() {
        return 0;
    }

    public Image getImage() {
        return null;
    }

    public void move(Queue q) {

    }

    public LinkedList<Tile> getTiles() {
        return null;
    }
}
