package Model.XML;

import javax.swing.*;
import java.sql.*;

//TODO: FIXA PREPARED STATMENTS

public class SQLHandler {
    //"v135h19g2" is both username and name of database
    final String databaseURL = "jdbc:mysql://mysql.cs.umu.se:3306/v135h19g2";
    final String user = "v135h19g2";
    final String password = "2riz6jDSDmhRlqMA";
    Statement s;
    Connection c;

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
     * Creates table G2GameTest if it not already exists
     */
    public void createTable() {
        if (s != null) {
            try {
                s.execute("CREATE TABLE IF NOT EXISTS G2GameTest" +
                        "( Name varchar(255),"+ "Level varchar(255), " +  "Score int)");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Database error: " +
                        "Failed to create a table.");
            }
        }
    }

    /**
     * Update an element in table G2GameTest
     * @param updateName Name of the element to be updated
     * @param update The update for "updateName"
     * @param conditionName Name of the condition variable. Determines the condition to be met.
     * @param condition The condition itself.
     * Note: update and condition can only be integers
     */
    public void updateTable(String updateName, int update, String conditionName, int condition) {
        try {
            s.execute("UPDATE G2GameTest SET " + updateName + "=" + update +
                    " WHERE " + conditionName + "=" + condition);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " +
                    "Failed to update table");
        }
    }

    /**
     * Insert a value into table G2GameTest
     */
    public void insertTable(String name, int level, int score) {
        try {
            s.execute("INSERT into G2GameTest values " +
                            "(" + "'" + name + "'" + "," + "'" + level + "'" + ","  + "'" + score + "'" + ")");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " +
                    "Failed to insert element into table");

        }
    }

    /**
     * Delete a value from table G2GameTest
     * @param conditionName Name of the condition variable. Determines the condition to be met.
     * @param condition The condition itself.
     */
    public void deleteTable(String conditionName, int condition) {
        try {
            s.execute("DELETE FROM G2GameTest where " + "" + conditionName + "" + "=" + condition);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " +
                    "Failed to delete table");
        }
    }

    /**
     * Prints table
     */
    public void printTable() {
        try{
            ResultSet pID = s.executeQuery("SELECT Name FROM G2GameTest");
            pID.next();
            String str = pID.getString(1);
            System.out.println(str);

            ResultSet lastN = s.executeQuery("SELECT Level FROM G2GameTest");
            lastN.next();
            str = lastN.getString(1);
            System.out.println(str);

            ResultSet firstN = s.executeQuery("SELECT Score FROM G2GameTest");
            firstN.next();
            str = firstN.getString(1);
            System.out.println(str);
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error:" +
                    " Failed to print table");
        }

    }
}
