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
    private int cost = 10;
    private Queue<Integer> queue;
    private LinkedList<Tile> path;
    private Tile goal;


    private int x,y;

    public Soldier(){
        try {
            image = ImageIO.read(
                    this.getClass().getResourceAsStream("/GUI/images/Gubbe.png"));
            image = image.getScaledInstance(15,20,Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void takeDMG(int DMG) {
        hp = hp - DMG;
    }

    @Override
    public void setTileQueue(LinkedList<Tile> path) {
        this.path = path;
    }

    @Override
    public int move(Graphics g) {
        if (queue.size() > 1) {
            x = queue.poll();
            y = queue.poll();
            g.drawImage(image, x, y, null);
            return 0;
        } else {
            return dmg;
        }
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
        return x;
    }

    @Override
    public int getY() {
        return y;
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
        this.queue = q;
    }

    @Override
    public LinkedList<Tile> getPath() {
        return path;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public Queue getQueue() {
        return queue;
    }

    public int getCost(){
        return cost;
    }

}
