/**
 *
 * Soldier
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

public class Soldier implements Unit {

    private Image image;
    private int hp = 1000;
    private int dmg = 3;
    private int cost = 10;
    private Queue<Integer> queue;
    private LinkedList<Tile> path;


    private int x,y;


    /**
     * The constructor for the soldier-unit. The constructor sets the
     * the image of the unit.
     */
    public Soldier(){
        try {
            image = ImageIO.read(
                    this.getClass().getResourceAsStream("/GUI/images/Gubbe.png"));
            image = image.getScaledInstance(20,30,Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method decreases the hp of the unit.
     * @param DMG The amount of hp taken away.
     */
    public void takeDMG(int DMG) {
        hp = hp - DMG;
    }

    /**
     * Sets units pathList so the unit konws where to move.
     * @param path The list of path tiles.
     */
    @Override
    public void setTileQueue(LinkedList<Tile> path) {
        this.path = path;
    }

    /**
     * Moves unit and draws unit at the new position.
     * @param g Graphics unit is to be drawn on.
     * @return Dmg to base if unit has reached goal.
     */
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

    @Override
    public Image getImage(){
        return image;
    }

    /**
     * Set the queue that unit moves after.
     * @param q Queue to be set.
     */
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
