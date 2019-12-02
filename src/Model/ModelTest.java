package Model;

import GUI.GameWindow;
import GUI.TileMap;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    private GameWindow gameWindow = new GameWindow();
    private ModelAdapter adapter = new ModelAdapter(gameWindow);
    private Model model = new Model(adapter);

    /**
     * Tests that the size of the tile map is correct
     */
    @Test
    public void testSizeOfTileMap() {
        TileMap map = model.getTileMap();
        Assert.assertEquals(40, map.getMapData().length);
    }

}