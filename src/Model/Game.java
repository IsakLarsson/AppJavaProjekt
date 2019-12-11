package Model;

import Model.Unit.Farmer;
import Model.Unit.Tower;
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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Game extends Thread {
    private List<Unit> unitList;
    private List<Tower> towerList;
    private ModelAdapter modelAdapter;
    private Level level;
    private BufferedImage backgroundImage;
    private BufferedImage updatedImage;
    private int updateInterval;
    private Animator animator;
    Destination destination = new Destination();
    java.util.Queue<Integer> q;

    public Game(ModelAdapter adapter, int updateInterval) {
        XMLParser parser = new XMLParser();
        unitList = new ArrayList<>();
        towerList = new ArrayList<>();
        level = parser.parseXML();
        modelAdapter = adapter;
        this.updateInterval = updateInterval;
    }

    @Override
    public void run() {
        final int[] index = {20};

        //TODO Spawn tower on tower area
        Tower tower = new Tower(100,40);
        towerList.add(tower);

        /*Farmer farmer = new Farmer();
        Farmer farmer2 = new Farmer();
        animator.addUnit(farmer, deepCopyList());
        */

        animator = new Animator(unitList);

        drawMap();


        //TODO When a unit arrives at goal area, the program crash
        Timer t = new Timer(updateInterval, (e) -> {
            

            // If the position queue is empty, create a new one for the next tile
            for (Unit unit : unitList) {
                if (unit.getQueue().size() < 2) {
                    destination = new Destination();
                    animator.calculatePostitionQueue(destination, unit);
                }
            }

            //update image
            drawUnits();

            // Shoot towers
            Iterator<Unit> iterator = unitList.iterator();
            while(iterator.hasNext()) {
                Unit unit = iterator.next();
                shootTowers(updatedImage.getGraphics(),unit);
                if (unit.getHp() <= 0) {
                    iterator.remove();
                }
            }

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

        for(Tower tower : towerList){
            tower.draw(g);
        }

    }


    public static BufferedImage deepCopyImage(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(bi.getRaster().createCompatibleWritableRaster());
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public void drawUnits(){
        updatedImage = deepCopyImage(backgroundImage);
        Graphics newGraphics = updatedImage.getGraphics();

        for(Unit unit : unitList){
            unit.draw(newGraphics);

        }
    }

    public void shootTowers(Graphics g, Unit u) {

        for (Tower tower : towerList) {
            tower.shoot(g,u);
        }
    }

    private LinkedList<Tile> deepCopyList() {
        LinkedList<Tile> tilesCopy = new LinkedList<>();
        for (Tile t : level.getPath()) {
            tilesCopy.add(new Path(t));
        }
        return tilesCopy;
    }

    public void spawn (Unit unit) {
        animator.addUnit(unit,deepCopyList());
        Destination destination = new Destination();
        animator.calculatePostitionQueue(destination, unit);
    }

}
