package Model;

import Model.XML.Area.Tile;
import Model.XML.XMLParser;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;

public class Worker extends SwingWorker {

    int x = 10, y = 10;

    // Frame class that creates images
    Frame frame = new Frame();

    // Level image
    private BufferedImage level;

    // Buffered image
    private BufferedImage image;

    // Adapter to transfer data from model to gui
    private ModelAdapter adapter;

    // Boolean to determine when the worker stops working
    private boolean gameOver = false;

    public Worker(ModelAdapter ma) {
        level = frame.createImage();
        adapter = ma;
    }

    @Override
    protected Object doInBackground() {

        // Draws the level
        drawLevel();

        // Update every units position on the map


        //TODO add animation

        for (int i = 1; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Publish the image to process()
            publish(updatePositions(x*i,y*i));
        }

        return null;
    }

    @Override
    protected void process(List chunks) {
        for (Object o : chunks) {
            adapter.setBufferedImage((BufferedImage) o);
        }
    }

    @Override
    protected void done() {
        super.done();
    }

    private void drawLevel() {
        XMLParser xmlParser = new XMLParser();
        ArrayList<Tile> tileMap = xmlParser.parseXML();
        Graphics g = level.getGraphics();

        for (int i = 0; i < tileMap.size(); i++) {
            Tile tile = tileMap.get(i);
            g.setColor(tile.getColor());
            g.fillRect(tile.getxCoordinate(), tile.getyCoordinate(),
                    tile.getSize(),
                    tile.getSize());
        }
    }


    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }


    private BufferedImage updatePositions(int x, int y) {
        BufferedImage newImage = deepCopy(level);
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
