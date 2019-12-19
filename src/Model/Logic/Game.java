package Model.Logic;

import Adapter.ModelAdapter;
import Model.Unit.Teleporter;
import Model.Unit.Tower;
import Model.Unit.Unit;
import Model.XML.Area.*;
import Model.XML.XMLParser;

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
 * 5DV135 - Application Development in Java
 * Department of Computing Science, Umeå University
 *
 * The game logic thread that handles all calculations and
 * sends them to the view via an adapter
 *
 * @version 1.0 18 December 2019
 * @author Axel Jakobsson <c18ajn@cs.umu.se>
 * @author Isak Larsson <@cs.umu.se>
 * @author Joel Bystedt <@cs.umu.se>
 * @author Albin Jönsson <@cs.umu.se>
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
    private int timeLimit = 200;
    private boolean teleported = true;
    /** Name entered by user **/
    private String userName;

    /**
     * Constructor for the game object
     * @param adapter adapter that handles coms between the model and view
     * @param updateInterval the interval of which the window should update
     * @param pathFile path to the xml file
     */
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

    /**
     * Overrides the thread function to do some tasks for each timer tick.
     */
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

    /**
     * Sets the state of the game to false or true
     * @param gameState a boolean
     */
    public void setGameState(Boolean gameState) {
        this.gameState = gameState;
    }

    /**
     * Shoots the all the towers in the tower list at every unit in range.
     */
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

    /**
     * Draws the map, and spawns the towers.
     * @param levelName name of the level to be drawn.
     */
    public void drawMap(String levelName) {
        if (mapIsDrawn) {
            return;
        }
        // XML level
        if(teleported) {
            XMLParser parser = new XMLParser(pathFile, levelName);
            this.level = parser.parseXML();

            ArrayList<TowerArea> towerAreas = level.getTowerAreas();
            for(int i = 0; i < towerAreas.size(); i++) {
                Tower tower = new Tower(towerAreas.get(i).getxCoordinate() * 30,
                        towerAreas.get(i).getyCoordinate() * 30, 30);
                towerList.add(tower);
            }
        }

        backgroundImage = new BufferedImage(600, 600,
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

        for(Tower tower : towerList){
            tower.draw(g);
        }

        mapIsDrawn = true;
        teleported = true;

    }

    /**
     * Deep copies a bufferedimage.
     * @param bi the image.
     * @return a deep copy of the image.
     */
    public static BufferedImage deepCopyImage(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(bi.getRaster().createCompatibleWritableRaster());
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    /**
     * Draws each unit on the map if the unit is alive.
     */
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
     * and sends updates to gui.
     */
    private void eachSecond(){
        timeCounter++;
        if(timeCounter == 20) {
            level.addMoney(1);
            timeLimit--;
            if(timeLimit <= 0){
                modelAdapter.timeIsOut(this);
            }

            timeCounter = 0;
        }
        if(timeLimit >= 0) {
            modelAdapter.setTime(timeLimit);
        }
        modelAdapter.setMoney(level.getMoney());
    }

    /**
     * Changes the path to the alternative  path if there is one.
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
            if (t instanceof TeleportInArea){
                tilesCopy.add(new TeleportInArea(t.getxCoordinate(),
                        t.getyCoordinate(), t.getSize()));

            } else if (t instanceof TeleportOutArea) {
                tilesCopy.add(new TeleportOutArea(t.getxCoordinate(),
                        t.getyCoordinate(), t.getSize()));
            }
            else{
                tilesCopy.add(new Path(t));
            }


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

    /**
     * Sets a new level.
     * @param levelName the name of the level.
     */
    public void nextLevel(String levelName) {
        setGameState(true);
        setLevelName(levelName);
        modelAdapter.setTeleportAbility(true);
        timeLimit = 200;
        unitList = new ArrayList<>();
        towerList = new ArrayList<>();
        animator = new Animator(unitList);
        mapIsDrawn = false;
    }

    /**
     *  The first teleportUnit in the list spawns a teleportInArea and teleportOutArea
     *  if there is an teleportUnit out on the map.
     */
    public void teleport(){
        for (Unit unit : unitList) {
            if(unit instanceof Teleporter){

                Tile inTile = unit.getPath().getFirst();

                Tile outTile = unit.getPath().get(5);

                level.addInTeleport(((Teleporter) unit).getSteps(), inTile);
                level.addOutTeleport(((Teleporter) unit).getSteps() + 5,
                        outTile);

                mapIsDrawn = false;
                teleported = false;
                return;
            }
        }
    }

    /**
     * Refreshes the adapter.
     */
    public void refreshAdapter(ModelAdapter modelAdapter) {
        this.modelAdapter = modelAdapter;
    }

    /**
     * Display the highscore dialog.
     */
    public void displayHighscore() {
        SQLHandler sql = new SQLHandler();
        sql.insertTable(getUserName(), getLevelName(), getTimeLimit());
        modelAdapter.printHighScore();
    }

    /**
     * Set the level name
     * @param levelName the name
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     *  Set teleported bool so that drawmap() dose'nt reads in map from xml again
     *  or spawns additional towers.
     * @param bool
     */
    public void setTeleported(boolean bool){
        teleported = bool;
    }

    /**
     * Gets the level's name
     * @return the name
     */
    public String getLevelName(){return this.levelName;}

    /**
     * Getter for timeLimit
     * @return timeLimit
     */
    public int getTimeLimit() {
        return timeLimit;
    }

    /**
     * Getter for username
     * @return username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter for username
     * @param userName username to be set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
