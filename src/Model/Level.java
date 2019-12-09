package Model;

import Model.XML.Area.*;
import Model.XML.XMLParser;

import java.awt.*;

public class Level {

    private Tile[][] tileMap;
    private int towerRange = 100;

    public Level(int mapSize){
        tileMap = new Tile[mapSize][mapSize];
        for(int i = 0; i < mapSize; i++){
            for(int j = 0; j < mapSize; j++){
                tileMap[i][j] = new FillArea(i,j,20);
            }
        }
    }

    public void addTile(Tile tile){
        int x = tile.getxCoordinate();
        int y = tile.getyCoordinate();
        tileMap[x][y] = tile;
    }

    public Tile getTile(int x, int y){
        return tileMap[x][y];
    }

    public int getTowerRange(){
        return towerRange;
    }

    public int getMapSize(){
        return tileMap.length;
    }

    public boolean isPath(int x, int y){

        Tile tile = getTile(x,y);
        return tile.equals(new Path(x,y,20));
        //return tileMap[x][y].getColor().equals(Color.orange);
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

}
