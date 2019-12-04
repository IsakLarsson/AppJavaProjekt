package Model;

import GUI.GameWindow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    private GameWindow gameWindow = new GameWindow();
    private ModelAdapter adapter = new ModelAdapter(gameWindow);
    private Model model = new Model(adapter);

    /**
     * Tests that the size of the tile map is correct
     */
    /*@Test
    public void testSizeOfTileMap() {
        TileMap map = model.getTileMap();
        assertEquals(40, map.getMapData().length);
    }*/

    @Test
    public void testThatBufferedImageIsNotNull() {
        assertNotNull(adapter.getImage());
    }

}