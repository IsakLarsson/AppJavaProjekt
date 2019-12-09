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
import java.util.Queue;

public class Worker extends SwingWorker {

    int x = 10, y = 10;

    // XML File
    private XMLParser xmlParser;

    // Queue of tiles
    private Queue<Tile> queue;

    // Level image
    private BufferedImage level;

    // Buffered image
    private BufferedImage image;

    // Adapter to transfer data from model to gui
    private ModelAdapter adapter;

    // Boolean to determine when the worker stops working
    private boolean gameOver = false;

    public Worker(ModelAdapter ma) {

        // Draws the level
        drawLevel();
        // Get thw path queue

        // Save the adapter
        adapter = ma;
    }

    @Override
    protected Object doInBackground() {



        // Update every units position on the map

        //TODO add animation
        int queuesize = queue.size();
        for (int i = 0; i < queuesize; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Get a tile from the queue
            Tile tile = queue.poll();

            // Get x and y positions
            x = tile.getxCoordinate();
            y = tile.getyCoordinate();

            // Print positions to console
            System.out.println(x + ", " + y);

            // Publish the image to process()
            publish(updatePositions(x, y));
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

    public Queue<Tile> getQueue() {
        return this.queue;
    }
}
