package Model.Unit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Soldier implements Unit {

    private Image image;
    private int speed = 10;
    private int hp = 100;
    private int dmg = 5;

    public Soldier(){
        try {
            image = ImageIO.read(
                    this.getClass().getResourceAsStream("/GUI/images/Gubbe.png"));
            image = image.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
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

    public int getSpeed(){
        return speed;
    }

    public Image getImage(){
        return image;
    }
}
