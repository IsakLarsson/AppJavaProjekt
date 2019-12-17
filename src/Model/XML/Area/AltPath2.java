package Model.XML.Area;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class AltPath2 extends Tile{
    private Image texture;

    public AltPath2(int xCoordinate, int yCoordinate, int size) {
        super(xCoordinate, yCoordinate, size);
        setColor(Color.orange);
        try {
            texture = ImageIO.read(
                    this.getClass().getResourceAsStream("/GUI/images/PathTile" +
                            ".png"));
            texture = texture.getScaledInstance(20,20,Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AltPath2(Tile tile) {
        super(tile.getxCoordinate(),tile.getyCoordinate(),tile.getSize());
    }

    public Image getTexture() {
        return texture;
    }

}
