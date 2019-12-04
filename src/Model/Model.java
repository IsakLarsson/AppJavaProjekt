package Model;
import GUI.Tile;
import GUI.TileMap;
import XML.ParseXML;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Model extends SwingWorker {

    // Buffered image
    private BufferedImage image;

    // Adapter to transfer data from model to gui
    private ModelAdapter adapter;

    // Map of tiles in a 2d array
    private TileMap tileMap;

    // Boolean to determine when the worker stops working
    private Boolean gameOver = false;

    public Model(ModelAdapter ma) {
        this.image = new BufferedImage(800,600,BufferedImage.TYPE_INT_ARGB);
        this.adapter = ma;
        doInBackground();
    }

    @Override
    protected Object doInBackground() {

        // Draws the level
        drawLevel();

        while(!gameOver) {

            // Update every units position on the map
            updatePositions();

            // Publish the image to process()
            publish(image);
        }



        return null;
    }

    @Override
    protected void process(List chunks) {
        for (Object o : chunks) {
            adapter.setBufferedImage((BufferedImage) o);
        }
    }

    private void drawLevel () {
        ParseXML xmlParser = new ParseXML();
        tileMap = xmlParser.parser();
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


    public void setGameOver(Boolean gameOver) {
        this.gameOver = gameOver;
    }

    public TileMap getTileMap() {
        return tileMap;
    }

    private void updatePositions() {
    }

}
