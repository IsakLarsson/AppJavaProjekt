package Model;

import Model.Unit.Farmer;
import Model.Unit.Tower;
import Model.Unit.Unit;
import Model.XML.Area.Path;
import Model.XML.Area.Tile;
import Model.XML.XMLParser;
import Model.XML.Area.Destination;

import javax.crypto.spec.DESedeKeySpec;
import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
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

        Farmer farmer = new Farmer();
        Farmer farmer2 = new Farmer();
        animator = new Animator(unitList);
        animator.addUnit(farmer, deepCopyList());

        drawMap();


        Timer t = new Timer(updateInterval, (e) -> {

            // Counts to 20 pixels for each tile.
            // When 20 is up, it gets the next tile
            if (index[0] == 20) {

                // Update the next tile destination for each unit
                for (Unit unit : unitList) {
                    destination = new Destination();
                    animator.run(destination, unit);
                }

                index[0] = 0;
            }

            //Path queue is empty
            if (animator.getQueue() == null) {
                return;
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
        animator.run(destination, unit);
    }

}
