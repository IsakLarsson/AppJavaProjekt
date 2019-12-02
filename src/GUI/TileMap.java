package GUI;

public class TileMap {
    private Tile[][] mapData;

    public TileMap(){

    }

    public void addTile(Tile tile){
        mapData[tile.getxCoordinate()][tile.getyCoordinate()] = tile;
    }
}
