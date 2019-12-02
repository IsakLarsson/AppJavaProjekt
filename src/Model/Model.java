package Model;

import GUI.Tile;
import GUI.TileMap;
import XML.ParseXML;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Model extends SwingWorker {

    // Arraylist of frames
    private BufferedImage image;

    // Adapter to transfer data from model to gui
    private ModelAdapter adapter;

    public Model(ArrayList<BufferedImage> al, ModelAdapter ma) {
        this.image = new BufferedImage(800,600,BufferedImage.TYPE_INT_ARGB);
        this.adapter = ma;
        doInBackground();
    }

    @Override
    protected Object doInBackground() {

        // Draws the level
        drawLevel();

        // Update every units position on the map
        updatePositions();

        adapter.setBufferedImage(image);
        //publish();
        return null;
    }

    @Override
    protected void process(List chunks) {
        for (Object o : chunks) {

        }
    }

    private void drawLevel () {
        ParseXML xmlParser = new ParseXML();
        TileMap tileMap = xmlParser.parser();
        Graphics g = image.getGraphics();

        for(int i = 0; i < tileMap.getMapData().length; i++){
            for(int j = 0; j < tileMap.getMapData().length; j++){
                Tile tile = tileMap.getTile(i,j);
                g.fillRect(tile.getxCoordinate()+(19*i),
                        tile.getyCoordinate()+(19*j),
                        tile.getxSize(),
                        tile.getySize());
                g.setColor(tile.getColor());
            }
        }
    }

    public void updatePositions () {

    }
}
