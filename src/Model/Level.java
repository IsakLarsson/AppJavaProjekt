package Model;

import Model.XML.Area.Tile;
import Model.XML.XMLParser;

public class Level {

    private Tile[][] tileMap;

    public Level(){
        XMLParser parser = new XMLParser();
        int size = parser.getMapSize();

        tileMap = new Tile[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                tileMap[i][j] = new Tile(i, j, 20);
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



}
