package Model.XML;

public class SQLHandlerTester {

    public static void main(String[] args) {
        SQLHandler sql = new SQLHandler();
        sql.createTable();
        //sql.insertTable("Albin1", 1, 0);
        //sql.insertTable("Albin2", 0, 1);
        sql.deleteTable("Level", 1);


    }
}
