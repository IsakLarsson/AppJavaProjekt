/**
 *
 * Level
 * Version 1.0
 *
 */

package Model;

import Model.XML.Area.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class Level {

    private Tile[][] tileMap;
    private LinkedList<Tile> path;
    private LinkedList<Tile> path2;
    private ArrayList<TowerArea> towerAreas;
    private int money;
    private int winCondition;


    /**
     * The constructor creates a tilemap of fill tiles and
     * creates the path lists.
     * @param mapSize The number of tiles vertically and horizontally.
     */
    public Level(int mapSize){
        tileMap = new Tile[mapSize][mapSize];
        for(int i = 0; i < mapSize; i++){
            for(int j = 0; j < mapSize; j++){
                tileMap[i][j] = new FillArea(i,j,40);
            }
        }
        path = new LinkedList<>();
        path2 = new LinkedList<>();

        towerAreas = new ArrayList<>();
    }


    /**
     * Add generic tile to tilemap.
     * @param tile Tile to be added to tilemap.
     */
    public void addTile(Tile tile){
        tileMap[tile.getxCoordinate()][tile.getyCoordinate()] = tile;
    }

    /**
     * Adds path tiles to both path lists and adds path tile to tilemap.
     * @param tile Tile to be added.
     */
    public void addPath(Tile tile){
        path.add(tile);
        path2.add(tile);
        tileMap[tile.getxCoordinate()][tile.getyCoordinate()] = tile;
    }

    /**
     * Add the alternative path tile to first path list and tilemap.
     * @param tile TIle to be added.
     */
    public void addAltPath1(Tile tile){
        path.add(tile);
        tileMap[tile.getxCoordinate()][tile.getyCoordinate()] = tile;
    }

    /**
     * Add the alternative path tile to second path list and tilemap.
     * @param tile TIle to be added.
     */
    public void addAltPath2(Tile tile){
        path2.add(tile);
        tileMap[tile.getxCoordinate()][tile.getyCoordinate()] = tile;
    }

    /**
     * Add the tower tile to towerArea list.
     * @param tile Tile to be added.
     */
    public void addTowerArea(TowerArea tile){
        towerAreas.add(tile);
        tileMap[tile.getxCoordinate()][tile.getyCoordinate()] = tile;
    }

    /**
     * Changes what path is returned with getPath().
     * This is if there is an alternative path to take.
     */
    public void changePath(){

        LinkedList<Tile> temp;
        temp = path;
        path = path2;
        path2 = temp;
    }

    public int buyUnit(int unitCost){
        money = money - unitCost;
        return money;
    }

    public void dmgBase(int dmg){
        winCondition = winCondition - dmg;
    }

    public ArrayList<TowerArea> getTowerAreas(){
        return towerAreas;
    }

    public LinkedList<Tile> getPath() {
        return path;
    }

    public Tile getTile(int x, int y){
        return tileMap[x][y];
    }

    public int getMapSize(){
        return tileMap.length;
    }

    public void addMoney(int add){
        money = money + add;
    }

    public int getMoney(){
        return money;
    }

    public void setWinCondition(int winCondition){
        this.winCondition = winCondition;
    }

    public int getWinCondition(){
        return winCondition;
    }
}
