package Model.XML;

import java.sql.*;

public class SQLHandler {
    //"v135h19g2" is both username and name of database
    final String databaseURL = "jdbc:mysql://mysql.cs.umu.se:3306/v135h19g2";
    final String user = "v135h19g2";
    final String password = "2riz6jDSDmhRlqMA";
    Statement s = null;
    Connection c = null;

    public SQLHandler() {
        //Try to load drivers
        try {
            Class.forName("com.mysql.jdbc.Driver" );
        } catch (ClassNotFoundException e1) {
            System.err.println("ERROR: failed to load SQL JDBC driver.");
            return;
        }

        //Try to establish connection
        c = null;
        s = null;
        try {
            c = DriverManager.getConnection(databaseURL, user,
                    password);
            if (c != null) {
                s = c.createStatement();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Creates table G2Game if it not already exists
     */
    public void createTable() {
        if (s != null) {
            try {
                //Create table G2Game if it does not exist
                s.execute("CREATE TABLE IF NOT EXISTS G2Game " +
                        "( Name varchar(255),"+ "Level varchar(255), " +  "Score int)");
            } catch (SQLException e) {
                e.printStackTrace(); //Ta bort senare
            }
        }
    }

    /**
     * Update an element in table G2Game
     * @param updateName Name of the element to be updated
     * @param update The update for "updateName"
     * @param conditionName Name of the condition variable. Determines the condition to be met.
     * @param condition The condition itself.
     */
    public void updateTable(String updateName, String update, String conditionName, String condition) {
        try {
            s.execute("UPDATE G2Game SET " + updateName + "=" + update +
                    " WHERE " + conditionName + "=" + condition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert a value into table G2Game
     */
    public void insertTable(String name, int level, int score) {
        try {
            s.execute("INSERT into G2Game values " +
                            "(" + "'" + name + "'" + "," + "'" + level + "'" + ","  + "'" + score + "'" + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a value from table G2Game
     * @param conditionName Name of the condition variable. Determines the condition to be met.
     * @param condition The condition itself.
     */
    public void deleteTable(String conditionName, int condition) {
        try {
            s.execute("DELETE FROM G2Game where " + "" + conditionName + "" + "=" + condition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints table
     */
    public void printTable() {
        try{
            ResultSet pID = s.executeQuery("SELECT Name FROM G2Game");
            pID.next();
            String str = pID.getString(1);
            System.out.println(str);

            ResultSet lastN = s.executeQuery("SELECT Level FROM G2Game");
            lastN.next();
            str = lastN.getString(1);
            System.out.println(str);

            ResultSet firstN = s.executeQuery("SELECT Score FROM G2Game");
            firstN.next();
            str = firstN.getString(1);
            System.out.println(str);
        }catch (SQLException e) {
            e.printStackTrace(); //Ta bort sen
        }

    }
}
