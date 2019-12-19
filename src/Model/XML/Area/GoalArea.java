package Model.XML.Area;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class GoalArea extends Tile {
    private Image texture;

    public GoalArea(int xCoordinate, int yCoordinate, int size) {
        super(xCoordinate, yCoordinate, size);
        setColor(Color.pink);
        try {
            texture = ImageIO.read(
                    this.getClass().getResourceAsStream("/View/images" +
                            "/FinishTile.png"));
            texture = texture.getScaledInstance(size,size,Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Image getTexture() {
        return texture;
    }
}