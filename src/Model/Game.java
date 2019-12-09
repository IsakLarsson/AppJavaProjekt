package Model;

import GUI.GameWindow;
import Model.Unit.Farmer;
import Model.XML.Area.Tile;
import Model.XML.XMLParser;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

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

        Farmer farmer = new Farmer();
        Animator animator = new Animator();
        animator.addUnit(farmer);

        Timer t = new Timer(updateInterval, (e) -> {
            //move everything

            Graphics g = gameImage.getGraphics();
            animator.run();

            //update image
            farmer.draw(g, farmer.getX(), farmer.getY());


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

    private BufferedImage updatePositions(int x, int y) {
        BufferedImage newImage = deepCopy(gameImage);
        Graphics testunit = newImage.getGraphics();
        testunit.setColor(Color.MAGENTA);
        testunit.fillOval(x,y,10,10);
        return newImage;
    }

    public static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(bi.getRaster().createCompatibleWritableRaster());
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

}
