/**
 *
 * Farmer
 * Version 1.0
 *
 */

package Model.Unit;

import Model.XML.Area.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Farmer implements Unit {

    private Image image;
    private int hp = 200;
    private int cost = 5;
    private int dmg = 3;
    private LinkedList<Tile> path;
    private Queue<Integer> queue;

    //Position
    private int x,y;

    public Farmer() {
        try {
            image = ImageIO.read(
                    this.getClass().getResourceAsStream("/GUI/images/Farmer.png"));
            image = image.getScaledInstance(20,30,Image.SCALE_SMOOTH);
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

    @Override
    public Queue getQueue() {
        return this.queue;
    }

    @Override
    public int getCost(){
        return cost;
    }

}
