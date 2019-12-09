package Model;

import GUI.GameWindow;
import Model.XML.Area.Tile;
import Model.XML.XMLParser;
import org.junit.jupiter.params.provider.EnumSource;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Game extends Thread{
    ModelAdapter modelAdapter;
    Level level;
    private BufferedImage gameImage;

    public Game(GameWindow gui){
        XMLParser parser = new XMLParser();
        level = parser.parseXML();
        modelAdapter = new ModelAdapter(gui);

    }

    @Override
    public void run() {
        drawMap();

        //move everything

        //update image

        //Send image to adapter
        modelAdapter.setBufferedImage(gameImage);

    }

    public void drawMap(){
        gameImage = new BufferedImage(400, 400,
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = gameImage.getGraphics();

        for (int i = 0; i < level.getMapSize(); i++) {
            for (int j = 0; j < level.getMapSize(); j++){

                Tile tile = level.getTile(i,j);
                g.setColor(tile.getColor());
                g.fillRect(tile.getxCoordinate(), tile.getyCoordinate(),
                        tile.getSize(),
                        tile.getSize());
            }
        }
    }

}
