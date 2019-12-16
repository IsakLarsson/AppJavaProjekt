package Model.Unit;

import Model.XML.Area.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Teleporter implements Unit{

    private Image image;
    private int hp = 50;
    private int cost = 20;
    private int speed = 20;
    private int dmg = 5;
    private LinkedList<Tile> path;
    private Queue<Integer> queue;

    private int x, y;

    public Teleporter(){
        try {
            image = ImageIO.read(
                    this.getClass().getResourceAsStream("/GUI/images/Teleporter.png"));
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
    public int move(Graphics g) {
        if (!queue.isEmpty()) {
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

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void takeDMG(int dmg) {
        this.hp = hp - dmg;
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

}
