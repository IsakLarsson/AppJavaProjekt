package Test;

import Model.HighScore;
import Model.XML.SQLHandler;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

/**
 * 5DV135 - Application Development in Java
 * Department of Computing Science, Umeå University
 *
 *  Tester for the class SQLHandler
 *
 * @version 1.0 18 December 2019
 * @author Albin Jönsson <c18ajs@cs.umu.se>
 */

public class SQLHandlerTester {
    /** SQL handler **/
    private SQLHandler sql;

    /**
     * Creates a new SQLHandler before each test.
     * Asserts that sql != null
     */
    @BeforeEach
    public void initTest() {
        sql = new SQLHandler();
        Assertions.assertNotNull(sql);
    }

    /**
     * Tests if it is possible to create a table named G2Game.
     */
    @Test
    public void createTableTest(){
        sql.createTable();
    }

    /**
     * Inserts TheNoob as a test entry.
     * TheNoob played level 1 and scored 0 points
     */
    @Test
    public void insertTableTest(){
        sql.insertTable("TheNoob", "Level 1", 0);
    }

    /**
     * Tests if the highest entry of the table is
     * the player Zezima
     */
    @Test
    public void selectTableTest(){
        ArrayList<HighScore> arr = sql.selectTable();
        Assertions.assertEquals("Zezima", arr.get(0).getName());
    }

    /**
     * Tries to delete TheNoob entry with 0 points
     */
    @Test
    public void deleteTableTest(){
        sql.deleteTable(112);
    }
}
