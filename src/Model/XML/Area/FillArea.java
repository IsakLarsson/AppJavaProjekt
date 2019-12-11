package Model.XML.Area;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;


public class FillArea extends Tile {
    private Image texture;

    public FillArea(int xCoordinate, int yCoordinate, int size) {
            super(xCoordinate, yCoordinate, size);
        try {
            texture = ImageIO.read(
                    this.getClass().getResourceAsStream("/GUI/images" +
                            "/GrassTile.png"));
            texture = texture.getScaledInstance(20,20,Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Image getTexture() {
        return texture;
    }
}

