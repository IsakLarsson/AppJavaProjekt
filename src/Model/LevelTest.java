package Model;

import Model.XML.Area.Path;
import Model.XML.Area.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LevelTest {

    private Level level;

    @BeforeEach
    public void beforeEach(){
        level = new Level(20);
    }

    @Test
    public void createTest(){
        Assertions.assertNotNull(level);
    }

    @Test
    public void getTileFromCordinates(){
        Tile tile = level.getTile(0,0);
        int x = tile.getxCoordinate();
        int y = tile.getyCoordinate();
        Assertions.assertEquals(0, x);
        Assertions.assertEquals(0, y);
    }

    @Test
    public void addTileTest(){
        Tile path = new Path(1,0,20);
        level.addTile(path);
        Assertions.assertEquals(path, level.getTile(1,0));
    }

    @Test
    public void ifCordIsPathTest(){
        boolean test = level.isPath(1,0);
        Assertions.assertTrue(test);
    }

    @Test
    public void ifCordIsTowerTest(){
        boolean test = level.isTower(5,5);
        Assertions.assertTrue(test);
    }

    @Test
    public void ifCordIsSpawnTest(){
        boolean test = level.isSpawn(0,0);
        Assertions.assertTrue(test);
    }

    @Test
    public void ifCordIsGoalTest(){
        boolean test = level.isGoal(10,0);
        Assertions.assertTrue(test);
    }

}
