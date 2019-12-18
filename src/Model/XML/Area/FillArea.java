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

