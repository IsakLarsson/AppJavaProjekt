package Model.Unit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import static java.awt.Color.RED;

/**
 * 5DV135 - Application Development in Java
 * Department of Computing Science, Umeå University
 *
 * Tower that shoots the units.
 *
 * @version 1.0 18 December 2019
 * @author Joel Bystedt <id17jbt@cs.umu.se>
 * @author Axel Jakobsson <c18ajn@cs.umu.se>
 * @author Isak Larsson <id17ian@cs.umu.se>
 */

public class Tower {
    private int dmg = 1;
    private int range = 100;
    private Image image;
    private Unit target;
    //Position
    private int xCord;
    private int yCord;

    /**
     * Constructor used in tests
     */
    public Tower() {}

    /**
     * Constructor for the tower.
     * @param x Coordinate.
     * @param y Coordinate.
     * @param size Size of representation.
     */
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

    /**
     * Method that is used to shot at the unit.
     * @param graphics graphics to be set.
     * @param unit The unit to be shot.
     */
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

    /**
     * Draw graphics.
     * @param g graphics to be drawn.
     */
    public void draw(Graphics g) {
        g.drawImage(image, xCord, yCord, null);
    }

    public int getDmg() {
        return dmg;
    }

    public int getX() {
        return xCord;
    }

    public int getY() {
        return yCord;
    }

    public Image getImage() {
        return image;
    }
}
