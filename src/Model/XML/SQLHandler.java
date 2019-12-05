package Model.XML;

import java.sql.*;



public class SQLHandler {

    public static void main(String[] args) {

        //"v135h19g2" is both username and name of database
        final String databaseURL = "jdbc:mysql://mysql.cs.umu.se:3306/v135h19g2";
        final String user = "v135h19g2";
        final String password = "2riz6jDSDmhRlqMA";

        //Try to load drivers
        try {
            Class.forName("com.mysql.jdbc.Driver" );
        } catch (ClassNotFoundException e1) {
            System.err.println("ERROR: failed to load SQL JDBC driver.");
            return;
        }

        //Try to establish connection
        Connection c = null;
        try {
            c = DriverManager.getConnection(databaseURL, user,
                    password);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Statement s= null;
        try {
            if (c != null) {
                s = c.createStatement();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (s != null) {
            try {
                //Create table G2Game if it does not exist, with P_ID, LastName,..., City
                s.execute("CREATE TABLE IF NOT EXISTS G2Game " +
                        "( P_Id int,"+
                        "LastName varchar(255), " +
                        "FirstName varchar(255), " +
                        "Address varchar(255)," +
                        "City varchar(255))");

                //För att update:a:
                //s.execute("UPDATE G2Game SET P_Id=420 WHERE P_Id=2");
                //För att ta delete:a:  s.execute("DELETE FROM G2Game where P_Id=2");
                //För att inserta:
                /*s.execute("INSERT into G2Game values " +
                        "(2,'Jönsson','Albin','BinkV','Umeå')");*/

                ResultSet res = s.executeQuery("SELECT P_Id FROM G2Game");



            } catch (SQLException e) {
                e.printStackTrace(); //Ta bort senare
            }
        }
    }
}
