package Model.XML.Area;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * 5DV135 - Application Development in Java
 * Department of Computing Science, Umeå University
 *
 * Tile to fill unused areas.
 *
 * @version 1.0 18 December 2019
 * @author Isak Larsson <id17ian@cs.umu.se>
 */

public class FillArea extends Tile {
    private Image texture;

    /**
     * Constructor for the FillArea.
     * @param xCoordinate x coordinate where tile should spawn.
     * @param yCoordinate y coordinate where tile should spawn.
     * @param size Size of the tile representation.
     */
    public FillArea(int xCoordinate, int yCoordinate, int size) {
            super(xCoordinate, yCoordinate, size);
        try {
            texture = ImageIO.read(
                    this.getClass().getResourceAsStream("/View/images" +
                            "/GrassTile2.png"));
            texture = texture.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Image getTexture() {
        return texture;
    }
}

