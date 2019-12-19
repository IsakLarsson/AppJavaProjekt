package Model.XML.Area;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * 5DV135 - Application Development in Java
 * Department of Computing Science, Umeå University
 *
 * Path tile for units to walk along.
 *
 * @version 1.0 18 December 2019
 * @author Isak Larsson <id17ian@cs.umu.se>
 * @author Axel Jakobsson <c18ajn@cs.umu.se>
 */

public class Path extends Tile{
    private Image texture;

    /**
     * Constructor for the Path.
     * @param xCoordinate x coordinate where tile should spawn.
     * @param yCoordinate y coordinate where tile should spawn.
     * @param size Size of the tile representation.
     */
    public Path(int xCoordinate, int yCoordinate, int size) {
        super(xCoordinate, yCoordinate, size);
        setColor(Color.orange);
        try {
            texture = ImageIO.read(
                    this.getClass().getResourceAsStream("/View/images/PathTile" +
                            ".png"));
            texture = texture.getScaledInstance(size,size,Image.SCALE_REPLICATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * An alternative constructor for the tile.
     * @param tile The tile that is used to get the information to create the tile.
     */
    public Path(Tile tile) {
        super(tile.getxCoordinate(),tile.getyCoordinate(),tile.getSize());
    }

    public Image getTexture() {
        return texture;
    }

}
