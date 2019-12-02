package GUI;

public class TileMap {
    private Tile[][] mapData;

    public TileMap(int xSize, int ySize){
        mapData = new Tile[xSize][ySize];

    }

    public void addTile(Tile tile){
        mapData[tile.getxCoordinate()][tile.getyCoordinate()] = tile;
    }

    public Tile getTile(int x, int y){
        return mapData[x][y];
    }

    public Tile[][] getMapData() {
        return mapData;
    }
}
