package Model;

import GUI.GameWindow;
import Model.XML.Area.Tile;
import Model.XML.XMLParser;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Game extends Thread {
    private ModelAdapter modelAdapter;
    private Level level;
    private BufferedImage gameImage;
    private int updateInterval;

    public Game(ModelAdapter adapter, int updateInterval) {
        XMLParser parser = new XMLParser();
        level = parser.parseXML();
        modelAdapter = adapter;
        this.updateInterval = updateInterval;

    }

    @Override
    public void run() {
        drawMap();

        Timer t = new Timer(updateInterval, (e) -> {
            //move everything

            //update image

            //Send image to adapter


            modelAdapter.setBufferedImage(gameImage);
        });
        t.setRepeats(true);
        t.start();


    }

    public void drawMap() {
        gameImage = new BufferedImage(400, 400,
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = gameImage.getGraphics();

        for (int i = 0; i < level.getMapSize(); i++) {
            for (int j = 0; j < level.getMapSize(); j++) {

                Tile tile = level.getTile(i, j);
                g.setColor(tile.getColor());
                g.fillRect(tile.getxCoordinate() + i * (tile.getSize() - 1),
                        tile.getyCoordinate() + j * (tile.getSize() - 1),
                        tile.getSize(),
                        tile.getSize());
            }
        }
    }

}
