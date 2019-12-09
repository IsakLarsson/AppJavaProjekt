package Model.XML;

public class SQLHandlerTester {

    public static void main(String[] args) {
        SQLHandler sql = new SQLHandler();
        sql.createTable();
        sql.insertTable("Albin1", 1, 0);
        sql.insertTable("Albin2", 0, 1);
        sql.updateTable("Score", 100, "Score", 0);
        sql.deleteTable("Level", 0);
        sql.printTable();
        sql.deleteTable("Level", 1);

        //Print should result in:
        /*
        * Albin1
        * 1
        * 100
        */
    }
}
