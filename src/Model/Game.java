package Model;

import GUI.GameWindow;
import Model.Unit.Farmer;
import Model.Unit.Unit;
import Model.XML.Area.Tile;
import Model.XML.XMLParser;

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

        Farmer farmer = new Farmer();
        Farmer farmer2 = new Farmer();
        Animator animator = new Animator(unitList);
        animator.addUnit(farmer);

        Timer t = new Timer(updateInterval, (e) -> {
            //move everything

            Graphics g = backgroundImage.getGraphics();
            animator.run();

            //update image
            drawUnits();


            //Send image to adapter
            modelAdapter.setBufferedImage(updatedImage);
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


    public static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(bi.getRaster().createCompatibleWritableRaster());
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public void drawUnits(){
        updatedImage = deepCopy(backgroundImage);
        Graphics newGraphics = updatedImage.getGraphics();
        for(Unit unit : unitList){
            unit.draw(newGraphics, unit.getX(), unit.getY());
        }
    }

}
