package Model.XML.Area;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * 5DV135 - Application Development in Java
 * Department of Computing Science, Umeå University
 *
 * The teleportOut tile.
 *
 * @version 1.0 18 December 2019
 * @author Isak Larsson <id17iln@cs.umu.se>
 * @author Axel Jakobsson <c18ajn@cs.umu.se>
 */
public class TeleportOutArea extends Tile {
    private Image texture;

    /**
     * Constructor for the TeleportOut tile.
     * @param xCoordinate x coordinate where tile should spawn.
     * @param yCoordinate y coordinate where tile should spawn.
     * @param size Size of the tile representation.
     */
    public TeleportOutArea(int xCoordinate, int yCoordinate, int size) {
        super(xCoordinate, yCoordinate, size);
        setColor(Color.orange);
        try {
            texture = ImageIO.read(
                    this.getClass().getResourceAsStream("/View/images" +
                            "/TeleportOut.png"));
            texture = texture.getScaledInstance(size,size,Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Image getTexture() {
        return texture;
    }

}
