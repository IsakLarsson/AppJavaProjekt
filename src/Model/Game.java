package Model;

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

/**
 * The game logic thread that handles all calculations and
 * sends them to the view via a adapter
 */
public class Game extends Thread {
    private List<Unit> unitList;
    private List<Tower> towerList;
    private ModelAdapter modelAdapter;
    private Level level;
    private BufferedImage backgroundImage;
    private BufferedImage updatedImage;
    private int updateInterval;
    private Animator animator;
    private Destination destination;
    private int timeCounter;
    private Boolean gameState = false;
    private Boolean mapIsDrawn = false;
    private String pathFile;
    private String levelName;
    private int timeLimit = 30;

    public Game(ModelAdapter adapter, int updateInterval, String pathFile) {

        this.pathFile = pathFile;

        // Array lists
        unitList = new ArrayList<>();
        towerList = new ArrayList<>();

        // Adapter
        this.modelAdapter = adapter;

        // Update interval
        this.updateInterval = updateInterval;
    }

    @Override
    public void run() {

        animator = new Animator(unitList);

        Timer t = new Timer(updateInterval, (e) -> {

            if (gameState) {

                // Draw map once
                drawMap(levelName);

                //update image
                drawUnits();

                // Shoot towers
                shootTowers();

                //
                eachSecond();

                //Send image to adapter
                modelAdapter.setBufferedImage(updatedImage);

                //
                if (level.getWinCondition() <= 0) {
                    modelAdapter.levelWon(this);
                }
            }
        });
        t.setRepeats(true);
        t.start();
    }

    public void setGameState(Boolean gameState) {
        this.gameState = gameState;
    }

    private void shootTowers() {
        Iterator<Unit> iterator = unitList.iterator();
        while(iterator.hasNext()) {
            Unit unit = iterator.next();
            for (Tower tower : towerList) {
                tower.shoot(updatedImage.getGraphics(),unit);
            }
            if (unit.getHp() <= 0) {
                iterator.remove();
            }
        }
    }

    public void drawMap(String levelName) {
        if (mapIsDrawn) {
            return;
        }
        // XML level
        XMLParser parser = new XMLParser(pathFile, levelName);
        this.level = parser.parseXML();

        backgroundImage = new BufferedImage(400, 400,
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = backgroundImage.getGraphics();
        modelAdapter.setMoney(level.getMoney());

        for (int i = 0; i < level.getMapSize(); i++) {
            for (int j = 0; j < level.getMapSize(); j++) {

                Tile tile = level.getTile(i, j);

                g.drawImage(tile.getTexture(), tile.getxCoordinate() + i * (tile.getSize() - 1),
                        tile.getyCoordinate() + j * (tile.getSize() - 1),null);
            }
        }

        ArrayList<TowerArea> towerAreas = level.getTowerAreas();
        for(int i = 0; i < towerAreas.size(); i++) {
            Tower tower = new Tower(towerAreas.get(i).getxCoordinate() * 20,
                    towerAreas.get(i).getyCoordinate() * 20);
            towerList.add(tower);
        }

        for(Tower tower : towerList){
            tower.draw(g);
        }

        mapIsDrawn = true;

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
            }
        }
    }

    /**
     * Increases money with 1 every 20 updates and decreases time with 1
     * and updates sends updates to gui.
     */
    private void eachSecond(){
        timeCounter++;
        if(timeCounter == 20) {
            level.addMoney(1);
            timeLimit--;
            if(timeLimit <= 0){
                modelAdapter.timeIsOut();
            }

            timeCounter = 0;
        }
        if(timeLimit >= 0) {
            modelAdapter.setTime(timeLimit);
        }
        modelAdapter.setMoney(level.getMoney());
    }

    /**
     * Changes the path to the alternetiv  path if there is one.
     */
    public void changePath(){
        level.changePath();
    }

    /**
     * Creates a deepcopy of the list of path tiles
     * that then are used by the units.
     * @return The copied list of tiles.
     */
    private LinkedList<Tile> deepCopyList() {
        LinkedList<Tile> tilesCopy = new LinkedList<>();
        for (Tile t : level.getPath()) {
            tilesCopy.add(new Path(t));
        }
        return tilesCopy;
    }

    /**
     * Spawn unit if you have enough money and set new money value.
     * @param unit The type of unit to be spawned.
     * @return New money value.
     */
    public int spawn (Unit unit) {
        if (level.getMoney() >= unit.getCost()) {
            int money = level.buyUnit(unit.getCost());
            animator.addUnit(unit, deepCopyList());
            Destination destination = new Destination();
            animator.calculatePositionQueue(destination, unit);
            return money;
        } else {
            return level.getMoney();
        }
    }

    public void restart() {
        unitList = new ArrayList<>();
        towerList = new ArrayList<>();
        animator = new Animator(unitList);
        mapIsDrawn = false;
    }

    public void nextLevel(String levelName) {
        System.out.println("Next level: " + levelName);
        setLevelName(levelName);
        unitList = new ArrayList<>();
        towerList = new ArrayList<>();
        animator = new Animator(unitList);
        mapIsDrawn = false;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
}
