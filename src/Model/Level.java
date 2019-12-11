package Model;

import Model.XML.Area.*;
import Model.XML.XMLParser;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Level {

    private Tile[][] tileMap;
    private LinkedList<Tile> path;
    private SpawnArea spawnArea;
    private GoalArea goalArea;
    private ArrayList<TowerArea> towerAreas;

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

    public LinkedList<Tile> getPath() {
        return path;
    }

    public void addTile(Tile tile){
        tileMap[tile.getxCoordinate()][tile.getyCoordinate()] = tile;
    }

    public void addPath(Path tile){
        path.add(tile);
        tileMap[tile.getxCoordinate()][tile.getyCoordinate()] = tile;
    }

    public void addSpawn(SpawnArea tile){
        spawnArea = tile;
        tileMap[tile.getxCoordinate()][tile.getyCoordinate()] = tile;
    }

    public void addTowerArea(TowerArea tile){
        towerAreas.add(tile);
        tileMap[tile.getxCoordinate()][tile.getyCoordinate()] = tile;
    }

    public void addGoalArea(GoalArea tile){
        goalArea = tile;
        tileMap[tile.getxCoordinate()][tile.getyCoordinate()] = tile;
    }

    public ArrayList<TowerArea> getTowerAreas(){
        return towerAreas;
    }

    public GoalArea getGoalArea(){
        System.out.println("this is goal");
        return goalArea;
    }

    public Tile getTile(int x, int y){
        return tileMap[x][y];
    }

    public int getMapSize(){
        return tileMap.length;
    }

    public boolean isPath(int x, int y){
        return tileMap[x][y].getColor().equals(Color.orange);
    }

    public boolean isTower(int x, int y){
        return tileMap[x][y].getColor().equals(Color.red);
    }

    public boolean isSpawn(int x, int y){
        return tileMap[x][y].getColor().equals(Color.green);
    }

    public boolean isGoal(int x, int y){
        return tileMap[x][y].getColor().equals(Color.pink);
    }

    public Tile getSpawn(){
        return spawnArea;
    }
}
