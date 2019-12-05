package Model;

import Model.XML.Area.Tile;
import Model.XML.XMLParser;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Worker extends SwingWorker {

    // Buffered image
    private BufferedImage image;

    // Adapter to transfer data from model to gui
    private ModelAdapter adapter;

    // Boolean to determine when the worker stops working
    private boolean gameOver = false;

    public Worker(ModelAdapter ma) {
        image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
        adapter = ma;
    }

    @Override
    protected Object doInBackground() {

        // Draws the level
        drawLevel();

        // Update every units position on the map
        updatePositions();

        //TODO add animation

        Graphics testunit = image.getGraphics();
        testunit.setColor(Color.MAGENTA);
        testunit.fillOval(10,10,10,10);
        // Publish the image to process()
        publish(image);


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
        Graphics g = image.getGraphics();

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


    private void updatePositions() {
    }

}
