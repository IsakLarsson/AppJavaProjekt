package Model.Unit;

import Model.XML.Area.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import static java.awt.Color.white;

public class Farmer implements Unit {

    private Boolean hit;
    private Image image;
    private int hp = 500;
    private int speed = 20;
    private int dmg = 1;
    private int size = 10;
    private LinkedList<Tile> path;
    private Queue<Integer> queue;

    //Position
    private int x,y;

    public Farmer() {
        try {
            image = ImageIO.read(
                    this.getClass().getResourceAsStream("/GUI/images/Farmer.png"));
            image = image.getScaledInstance(15,20,Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setTileQueue(LinkedList<Tile> path) {
        this.path = path;
    }

    @Override
    public void draw(Graphics g) {
        if (queue.size() > 1) {
            x = queue.poll();
            y = queue.poll();
            g.drawImage(image, x, y, null);
        }
    }

    @Override
    public void setPixelPositionQueue(Queue queue) {
        this.queue = queue;
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

    public int getSpeed(){
        return speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public LinkedList<Tile> getPath() {
        return path;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

}
