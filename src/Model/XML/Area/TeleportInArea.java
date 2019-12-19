package Model.XML.Area;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class TeleportInArea extends Tile implements AreaEffect {
    private Image texture;
    private Tile tele;

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
