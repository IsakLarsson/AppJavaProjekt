package Model;

import XML.Area.Tile;
import XML.XMLParser;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Model extends SwingWorker {

    // Buffered image
    private BufferedImage image;

    // Adapter to transfer data from model to gui
    private ModelAdapter adapter;

    // Boolean to determine when the worker stops working
    private Boolean gameOver = false;

    public Model(ModelAdapter ma) {
        this.image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        this.adapter = ma;
        doInBackground();
    }

    @Override
    protected Object doInBackground() {

        // Draws the level
        drawLevel();


        // Update every units position on the map
        updatePositions();

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


    public void setGameOver(Boolean gameOver) {
        this.gameOver = gameOver;
    }


    private void updatePositions() {
    }

}
