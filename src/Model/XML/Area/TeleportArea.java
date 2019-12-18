package Model.XML.Area;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;

public class TeleportArea extends Tile implements AreaEffect {
    private Image texture;

    public TeleportArea(int xCoordinate, int yCoordinate, int size) {
        super(xCoordinate, yCoordinate, size);
        setColor(Color.orange);
        try {
            texture = ImageIO.read(
                    this.getClass().getResourceAsStream("/GUI/images/Portal" +
                            ".jpg"));
            texture = texture.getScaledInstance(size,size,Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Image getTexture() {
        return texture;
    }

    @Override
    public void landOn(LinkedList<Tile> tileList) {
        System.out.println("landed on");
    }

}
