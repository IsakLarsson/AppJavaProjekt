package Model.XML;

import org.junit.jupiter.api.*;

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
        sql.insertTable("Albin1", 1, 0);
        sql.insertTable("Albin2", 1, 32);
        sql.insertTable("Albin1", 2, 5);


    }

    /*@Test
    public void selectTableTest(){
        String str = sql.selectTable("name");
        Assertions.assertEquals("Albin1", str);
    }*/

    @Test
    public void deleteTableTest(){
        sql.deleteTable("Level", 1);
    }
}
