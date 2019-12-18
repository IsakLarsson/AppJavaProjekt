package Model.XML;

import GUI.HighScore;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

//Runs tests in normal order
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SQLHandlerTester {
    SQLHandler sql;

    @BeforeEach
    public void initTest() {
        sql = new SQLHandler();
        Assertions.assertNotNull(sql);
    }

    @Test
    public void createTableTest(){
        sql.createTable();
    }

    @Test
    public void insertTableTest(){
        sql.insertTable("TheNoob", "Level 1", 0);
    }

    @Test
    public void selectTableTest(){
        ArrayList<HighScore> arr = sql.selectTable();
        Assertions.assertEquals("Zezima", arr.get(0).getName());
    }

    @Test
    public void deleteTableTest(){
        sql.deleteTable( 0);
    }
}
