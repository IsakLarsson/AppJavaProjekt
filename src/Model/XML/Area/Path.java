package Model.XML.Area;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Path extends Tile{
    private Image texture;

    public Path(int xCoordinate, int yCoordinate, int size) {
        super(xCoordinate, yCoordinate, size);
        setColor(Color.orange);
        try {
            texture = ImageIO.read(
                    this.getClass().getResourceAsStream("/GUI/images/PathTile" +
                            ".png"));
            texture = texture.getScaledInstance(size,size,Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Path(Tile tile) {
        super(tile.getxCoordinate(),tile.getyCoordinate(),tile.getSize());
    }

    public Image getTexture() {
        return texture;
    }

}
