package Model.Unit;

import Model.XML.Area.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 5DV135 - Application Development in Java
 * Department of Computing Science, Umeå University
 *
 * The farmer unit. An implementation of Unit.
 *
 * @version 1.0 18 December 2019
 * @author Axel Jakobsson <c18ajn@cs.umu.se>
 */

public class Farmer implements Unit {

    private Image image;
    private int hp = 200;
    private int cost = 5;
    private int dmg = 3;
    private LinkedList<Tile> path;
    private Queue<Integer> queue;

    //Position
    private int x,y;


    /**
     * The constructor for the farmer-unit. The constructor sets the
     * the image of the unit.
     */
    public Farmer() {
        try {
            image = ImageIO.read(
                    this.getClass().getResourceAsStream("/GUI/images/Farmer.png"));
            image = image.getScaledInstance(20,30,Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        if (!queue.isEmpty()) {
            x = queue.poll();
            y = queue.poll();
            g.drawImage(image, x, y, null);
            return 0;
        } else {
            return dmg;
        }
    }

    /**
     * Set the queue that unit moves after.
     * @param queue Queue to be set.
     */
    @Override
    public void setPixelPositionQueue(Queue queue) {
        this.queue = queue;
    }

    /**
     * This method decreases the hp of the unit.
     * @param DMG The amount of hp taken away.
     */
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
