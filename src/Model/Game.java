package Model;

import Model.Unit.Farmer;
import Model.Unit.Unit;
import Model.XML.Area.Path;
import Model.XML.Area.Tile;
import Model.XML.XMLParser;
import Model.XML.Area.Destination;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game extends Thread {
    private List<Unit> unitList;
    private ModelAdapter modelAdapter;
    private Level level;
    private BufferedImage backgroundImage;
    private BufferedImage updatedImage;
    private int updateInterval;
    Destination destination = new Destination();
    java.util.Queue<Integer> q;

    public Game(ModelAdapter adapter, int updateInterval) {
        XMLParser parser = new XMLParser();
        unitList = new ArrayList<>();
        level = parser.parseXML();
        modelAdapter = adapter;
        this.updateInterval = updateInterval;

    }

    @Override
    public void run() {
        drawMap();
        final int[] index = {20};
        Farmer farmer = new Farmer();
        Farmer farmer2 = new Farmer();
        Animator animator = new Animator(unitList);
        animator.addUnit(farmer, deepCopyList());
        // Draw the map
        drawMap();
        System.out.println("Drew map yo");

        Timer t = new Timer(updateInterval, (e) -> {



            // Counts to 20 pixels for each tile.
            // When 20 is up, it gets the next tile
            if (index[0] == 20) {
                System.out.println("Reached its destination, Next tile.");
                destination = new Destination();
                animator.run(destination);
                //q = destination.calculateQueue(level.getPath());
                index[0] = 0;
            }

            //Path queue is empty
            if (animator.getQueue() == null) {
                return;
            }

            //move everything
            Graphics g = backgroundImage.getGraphics();

            //update image
            drawUnits();

            //Send image to adapter
            modelAdapter.setBufferedImage(updatedImage);

            // Count for each pixel
            index[0]++;
        });
        t.setRepeats(true);
        t.start();


    }

    public void drawMap() {
        backgroundImage = new BufferedImage(400, 400,
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = backgroundImage.getGraphics();

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


    public static BufferedImage deepCopyImage(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(bi.getRaster().createCompatibleWritableRaster());
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    //TODO alla units behöver typ en egen path-list
    public void drawUnits(){
        updatedImage = deepCopyImage(backgroundImage);
        Graphics newGraphics = updatedImage.getGraphics();

        for(Unit unit : unitList){
            unit.draw(newGraphics);

        }
    }

    private LinkedList<Tile> deepCopyList() {
        LinkedList<Tile> tilesCopy = new LinkedList<>();
        for (Tile t : level.getPath()) {
            tilesCopy.add(new Path(t));
        }
        return tilesCopy;
    }

}
