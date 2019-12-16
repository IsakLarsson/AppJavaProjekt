package Model;

import GUI.GameFrame;
import GUI.GameWindow;
import Model.XML.Area.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    private GameWindow gameWindow = new GameWindow();
    private GameFrame gameFrame = new GameFrame("",gameWindow,null,null, 1);
    private ModelAdapter adapter = new ModelAdapter(gameFrame);
    private Worker worker = new Worker(adapter);

    /**
     * Tests that the size of the tile map is correct
     */
    /*@Test
    public void testSizeOfTileMap() {
        TileMap map = model.getTileMap();
        assertEquals(40, map.getMapData().length);
    }*/

    @Test
    public void testThatLevelImageIsNotNull() {
        assertNotNull(adapter.getImage());
    }

    @Test
    public void testQueueElementsIsNotNull () {
        for (Tile t : worker.getQueue()) {
            assertNotNull(t);
        }
    }
}