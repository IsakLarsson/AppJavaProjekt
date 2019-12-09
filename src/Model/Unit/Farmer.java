package Model.Unit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

import static java.awt.Color.white;

public class Farmer implements Unit {

    private Image image;
    private int hp = 50;
    private int speed = 20;
    private int dmg = 1;
    private int size = 10;

    //Position
    private int x,y;

    public Farmer() {
        try {
            image = ImageIO.read(
                    this.getClass().getResourceAsStream("/GUI/images/Farmer.png"));
            image = image.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void draw(Graphics g, int x, int y) {
        g.setColor(white);
        g.fillOval(x, y, size, size);
    }

    public void takeDMG(int DMG) {
        hp = hp - DMG;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public int getDmg() {
        return dmg;
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void move() {
        x = x+speed;
    }

    public int getSpeed(){
        return speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
