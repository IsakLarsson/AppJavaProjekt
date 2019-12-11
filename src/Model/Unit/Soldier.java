package Model.Unit;

import Model.XML.Area.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

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

    public void takeDMG(int DMG) {
        hp = hp - DMG;
    }

    @Override
    public void setTileQueue(LinkedList<Tile> tiles) {

    }

    @Override
    public void draw(Graphics g) {

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
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    public int getSpeed(){
        return speed;
    }

    @Override
    public Image getImage(){
        return image;
    }

    @Override
    public void setPixelPositionQueue(Queue q) {

    }

    @Override
    public LinkedList<Tile> getPath() {
        return null;
    }

}
