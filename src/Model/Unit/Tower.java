package Model.Unit;

import Model.XML.Area.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import static java.awt.Color.RED;

public class Tower {
    private int dmg = 1;
    private int range = 100;
    private Image image;
    private Unit target;
    //Position
    private int xCord;
    private int yCord;

    public Tower() {}

    public Tower(int x, int y, int size) {
        this.xCord = x;
        this.yCord = y;

        try {
            image = ImageIO.read(
                    this.getClass().getResourceAsStream("/View/images/Tower" +
                            ".png"));
            image = image.getScaledInstance(size,size,Image.SCALE_AREA_AVERAGING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void shoot(Graphics graphics, Unit unit) {
        double a = Math.abs(xCord-unit.getX());
        double b = Math.abs(yCord-unit.getY());
        double distance = a*a + b*b;

        distance = Math.sqrt(distance);

        if (range >= distance) {

            if (target == null) {
                target = unit;
            }

            ((Graphics2D) graphics).setStroke(new BasicStroke(3));
            graphics.setColor(RED);
            graphics.drawLine(xCord + 15 ,yCord + 15,target.getX() + 15,
                    target.getY() + 15);
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
