package Model;

import Model.Unit.Tower;
import Model.XML.Area.*;
import Model.XML.XMLParser;
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
        Path path = new Path(1,0,20);
        level.addPath(path);
        Assertions.assertEquals(path, level.getTile(1,0));
    }


    @Test
    public void ifCastIsCorrectTest(){
        Path path = new Path(1,0,20);
        level.addTile(path);
        Assertions.assertEquals(path, level.getTile(1,0));
    }

    @Test
    public void setMoneyTest(){
        XMLParser parser = new XMLParser();
        level = parser.parseXML();
        Assertions.assertEquals(100, level.getMoney());
    }

    @Test
    public void setWinConditionTest(){
        XMLParser parser = new XMLParser();
        level = parser.parseXML();
        Assertions.assertEquals(10, level.getWinCodition());
    }

    @Test
    public void addMoneyTest(){
        level.addMoney(20);
        Assertions.assertEquals(20, level.getMoney());
    }

    @Test
    public void buyTest(){
        level.addMoney(50);
        level.buyUnit(10);
        Assertions.assertEquals(40,level.getMoney());
    }

}
