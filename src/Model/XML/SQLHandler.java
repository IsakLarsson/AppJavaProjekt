package Model.XML;

import GUI.HighScore;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

//TODO: FIXA PREPARED STATMENTS
//TODO: Score i 00:00(tid), högre desto bättre score

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
     * Update an element in table G2Game
     * @param updateName Name of the element to be updated
     * @param update The update for "updateName"
     * @param conditionName Name of the condition variable. Determines the condition to be met.
     * @param condition The condition itself.
     * Note: update and condition can only be integers
     */
    /*public void updateTable(String updateName, int update, String conditionName, int condition) {
        try {
            PreparedStatement preparedStatement = c.prepareStatement("UPDATE G2Game set Score=? WHERE Score=?");
            //preparedStatement.setString(1, updateName);
            preparedStatement.setInt(1, update);
            //preparedStatement.setString(3, conditionName);
            preparedStatement.setInt(2, condition);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " +
                    "Failed to update table");
        }


        try {
            s.execute("UPDATE G2Game SET " + updateName + "=" + update +
                    " WHERE " + conditionName + "=" + condition);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " +
                    "Failed to update table");
        }
    }*/

    /**
     * Insert a value into table G2Game
     */
    public void insertTable(String name, int level, int score) {
        try {
            PreparedStatement preparedStatement = c.prepareStatement("INSERT into G2Game values(?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, level);
            preparedStatement.setInt(3, score);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " +
                    "Failed to insert element into table");
        }
    }

    /**
     * Delete a value from table G2Game
     * @param conditionName Name of the condition variable. Determines the condition to be met.
     * @param condition The condition itself.
     */
    public void deleteTable(String conditionName, int condition) {
        try {
            PreparedStatement preparedStatement = c.prepareStatement("DELETE from G2Game where Level=?");
            //preparedStatement.setString(1, conditionName);
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
                score.get(i).setLevel(res.getInt(2));
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
        Collections.sort(score, HighScore.scoreComparator);
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
