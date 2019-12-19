package Model.XML;

import Model.Logic.HighScore;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * 5DV135 - Application Development in Java
 * Department of Computing Science, Umeå University
 *
 *  Provides an interface to simplify SQL interaction.
 *
 * @version 1.0 18 December 2019
 * @author Albin Jönsson <c18ajs@cs.umu.se>
 */

public class SQLHandler {
    /** Database URL**/
    private final String databaseURL = "jdbc:mysql://mysql.cs.umu.se:3306/v135h19g2";
    /** Username **/
    private final String user = "v135h19g2";
    /** Password **/
    private final String password = "2riz6jDSDmhRlqMA";
    /** Allows prepared statement **/
    private Statement s;
    /** Connection reachable from all methods **/
    private Connection c;

    /**
     * Constructor for SQLHandler
     */
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
        try {
            PreparedStatement preparedStatement = c.prepareStatement("CREATE TABLE IF NOT EXISTS G2Game" +
                    "( Name varchar(255),"+ "Level varchar(255), " +  "Score int)");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " +
                    "Failed to create table");
        }
    }

    /**
     * Insert a value into table G2Game
     * @param name Entry name
     * @param level Current level
     * @param score Score, number of seconds left.
     */
    public void insertTable(String name, String level, int score) {
        try {
            PreparedStatement preparedStatement = c.prepareStatement("INSERT into G2Game values(?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, level);
            preparedStatement.setInt(3, score);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " +
                    "Failed to insert element into table");
        }
    }

    /**
     * Delete a value from table G2Game
     * @param condition The condition itself.
     */
    public void deleteTable(int condition) {
        try {
            PreparedStatement preparedStatement = c.prepareStatement("DELETE from G2Game where Score=?");
            preparedStatement.setInt(1, condition);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " +
                    "Failed to delete table");
        }
    }

    /**
     * Selects table
     */
    public ArrayList<HighScore> selectTable() {
        int tableSize = getTableSize();
        ArrayList<HighScore> score = createHighScoreList();
        try {
            //Select all rows and columns from G2Game
            PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM G2Game");
            ResultSet res = preparedStatement.executeQuery();

            for (int i = 0; i < tableSize; i++) {
                res.next();
                score.get(i).setName(res.getString(1));
                score.get(i).setLevel(res.getString(2));
                score.get(i).setScore(res.getInt(3));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " +
                    "Failed to select from table");
        }
        return sortTable(score);
    }

    /**
     * Sorts the table with highest score at the top and
     * lowest score at the bottom.
     * @return A sorted Highscore-array
     */
    private ArrayList<HighScore> sortTable(ArrayList<HighScore> score) {
        score.sort(HighScore.scoreComparator);
        return score;
    }

    /**
     * Creates and initiates an ArrayList with tableSize numbers
     * of elements.
     * @return An initiated ArrayList
     */
    private ArrayList<HighScore> createHighScoreList() {
        int tableSize = getTableSize();
        ArrayList<HighScore> score = new ArrayList<>();
        for (int i = 0; i < tableSize; i++) {
            score.add(new HighScore());
        }
        return score;
    }

    /**
     * Returns the table size of G2Game
     * @return Table size of G2Game
     */
    public int getTableSize() {
        int size = 0;
        try {
            PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM G2Game");
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                size++;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " +
                    "Failed to get size of table");
        }
        return size;
    }
}
