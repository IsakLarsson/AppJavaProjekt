package Model;

import Model.XML.Area.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class Level {

    private Tile[][] tileMap;
    private LinkedList<Tile> path;
    private SpawnArea spawnArea;
    private GoalArea goalArea;
    private ArrayList<TowerArea> towerAreas;
    private int money;
    private int winCondition;

    public Level(int mapSize){
        tileMap = new Tile[mapSize][mapSize];
        for(int i = 0; i < mapSize; i++){
            for(int j = 0; j < mapSize; j++){
                tileMap[i][j] = new FillArea(i,j,20);
            }
        }
        path = new LinkedList<>();
        towerAreas = new ArrayList<>();
    }



    public void addTile(Tile tile){
        tileMap[tile.getxCoordinate()][tile.getyCoordinate()] = tile;
    }

    public void addPath(Tile tile){
        path.add(tile);
        tileMap[tile.getxCoordinate()][tile.getyCoordinate()] = tile;
    }


    //TODO kan vara onödig
    public void addSpawn(SpawnArea tile){
        spawnArea = tile;
        tileMap[tile.getxCoordinate()][tile.getyCoordinate()] = tile;
    }

    public void addTowerArea(TowerArea tile){
        towerAreas.add(tile);
        tileMap[tile.getxCoordinate()][tile.getyCoordinate()] = tile;
    }


    //TODO kan vara onödig
    public void addGoalArea(GoalArea tile){
        goalArea = tile;
        tileMap[tile.getxCoordinate()][tile.getyCoordinate()] = tile;
    }

    public LinkedList<Tile> getPath() {
        return path;
    }

    public ArrayList<TowerArea> getTowerAreas(){
        return towerAreas;
    }

    public GoalArea getGoalArea(){
        return goalArea;
    }

    public Tile getTile(int x, int y){
        return tileMap[x][y];
    }

    public int getMapSize(){
        return tileMap.length;
    }

    public Tile getSpawn(){
        return spawnArea;
    }

    public void addMoney(int add){
        money = money + add;
    }

    public int buyUnit(int unitCost){
        money = money - unitCost;
        return money;
    }

    public int getMoney(){
        return money;
    }

    public void dmgBase(int dmg){
        winCondition = winCondition - dmg;
    }
    public void setWinCondition(int winCondition){
        this.winCondition = winCondition;
    }

    public int getWinCondition(){
        return winCondition;
    }
}
