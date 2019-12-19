package Model.XML.Area;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 5DV135 - Application Development in Java
 * Department of Computing Science, Umeå University
 *
 * Teleport tile that teleports units to teleportOutArea.
 *
 * @version 1.0 18 December 2019
 * @author Isak Larsson <id17ian@cs.umu.se>
 * @author Axel Jakobsson <c18ajn@cs.umu.se>
 */
public class TeleportInArea extends Tile implements AreaEffect {
    private Image texture;
    private Tile tele;

    /**
     * Constructor for the teleportIn tile.
     * @param xCoordinate x coordinate where tile should spawn.
     * @param yCoordinate y coordinate where tile should spawn.
     * @param size Size of the tile representation.
     */
    public TeleportInArea(int xCoordinate, int yCoordinate, int size) {
        super(xCoordinate, yCoordinate, size);
        setColor(Color.orange);
        try {
            texture = ImageIO.read(
                    this.getClass().getResourceAsStream("/View/images" +
                            "/TeleportIn.png"));
            texture = texture.getScaledInstance(size,size,Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Image getTexture() {
        return texture;
    }

    /**
     * Teleports units that steps on the tile to the next teleportOutArea.
     * @param tileList The path.
     * @return The coordinates in a queue that the unit shall be teleported to.
     */
    @Override
    public Queue<Integer> landOn(LinkedList<Tile> tileList) {
        for (Tile tile: tileList) {
            if(tile instanceof TeleportOutArea){
                tele = tile;
                break;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(tele.getxCoordinate() * 30);
        queue.add(tele.getyCoordinate() * 30);

        return queue;
    }

}
