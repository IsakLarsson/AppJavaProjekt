package Model;

import Controller.Controller;
import Model.Unit.Tower;
import Model.Unit.Unit;
import Model.XML.Area.Path;
import Model.XML.Area.Tile;
import Model.XML.Area.TowerArea;
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
    private Controller controller;
    private Animator animator;
    private Destination destination;
    private int timeCounter;

    public Game(ModelAdapter adapter, int updateInterval, Controller controller) {

        // Controller
        this.controller = controller;

        // Array lists
        unitList = new ArrayList<>();
        towerList = new ArrayList<>();

        // XML level
        XMLParser parser = new XMLParser();
        level = parser.parseXML();

        // Adapter
        this.modelAdapter = adapter;

        // Update interval
        this.updateInterval = updateInterval;
    }

    @Override
    public void run() {
        //TODO Spawn tower on tower area
        ArrayList<TowerArea> towerAreas = level.getTowerAreas();
        Tower tower = new Tower(towerAreas.get(0).getxCoordinate()*20,towerAreas.get(0).getyCoordinate()*20);
        towerList.add(tower);

        animator = new Animator(unitList);

        drawMap();

        Timer t = new Timer(updateInterval, (e) -> {

            //update image
            drawUnits();

            // Shoot towers TODO Only shoot at one unit at a time
            Iterator<Unit> iterator = unitList.iterator();
            while(iterator.hasNext()) {
                Unit unit = iterator.next();
                shootTowers(updatedImage.getGraphics(),unit);
                if (unit.getHp() <= 0) {
                    iterator.remove();
                }
            }

            income();

            //Send image to adapter
            modelAdapter.setBufferedImage(updatedImage);

            if(level.getWinCondition() <= 0){
                System.out.println("YOU WON!!!");
            }

        });
        t.setRepeats(true);
        t.start();


    }

    public void drawMap() {
        backgroundImage = new BufferedImage(400, 400,
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = backgroundImage.getGraphics();
        controller.setMoney(level.getMoney());

        for (int i = 0; i < level.getMapSize(); i++) {
            for (int j = 0; j < level.getMapSize(); j++) {

                Tile tile = level.getTile(i, j);

                g.drawImage(tile.getTexture(), tile.getxCoordinate() + i * (tile.getSize() - 1),
                        tile.getyCoordinate() + j * (tile.getSize() - 1),null);
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

    //TODO needs iterator
    public void drawUnits(){
        updatedImage = deepCopyImage(backgroundImage);
        Graphics newGraphics = updatedImage.getGraphics();

        Iterator<Unit> iterator = unitList.iterator();
        while(iterator.hasNext()) {
            Unit unit = iterator.next();
            if (unit.getQueue().isEmpty()) {
                destination = new Destination();
                animator.calculatePositionQueue(destination, unit);
            }

            int dmg = unit.move(newGraphics);
            if(dmg > 0) {
                level.dmgBase(dmg);
                iterator.remove();
                /*if(unitList.isEmpty()){
                    break;
                }*/
            }
        }
    }

    //TODO: Pretty ugly solution but works
    private void income(){
        timeCounter++;
        if(timeCounter == 20) {
            level.addMoney(1);
            timeCounter = 0;
        }
        controller.setMoney(level.getMoney());
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

    public int spawn (Unit unit, int cost) {
        if (level.getMoney() >= cost) {
            level.buyUnit(cost);
            animator.addUnit(unit, deepCopyList());
            Destination destination = new Destination();
            animator.calculatePositionQueue(destination, unit);
            return level.getMoney();
        } else {
            return level.getMoney();
        }
    }

}
