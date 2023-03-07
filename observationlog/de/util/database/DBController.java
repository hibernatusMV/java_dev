package de.util.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 

public class DBController {
    private static final DBController dbcontroller = new DBController();
    private static Connection connection;
    private static final String DB_PATH = System.getProperty("user.dir") + "/database/" + "observationlog.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Fehler beim Laden des JDBC-Treibers");
            e.printStackTrace();
        }
    } 

    public DBController(){
    }
    
    public static DBController getInstance(){
        return dbcontroller;
    }
    
    private void initDBConnection() {
        try {
            if (connection != null)
                return;
            System.out.println("Creating Connection to Database...");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
            if (!connection.isClosed())
                System.out.println("...Connection established");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    if (!connection.isClosed() && connection != null) {
                        connection.close();
                        if (connection.isClosed())
                            System.out.println("Connection to Database closed");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    } 

    private void handleDB() {
        try { 
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM observations;");
            while (rs.next()) {
                System.out.println("Beobachtungsort = " + rs.getString("loc_name"));
                System.out.println("Objekt = " + rs.getString("obs_object"));
                System.out.println("Rektaszension = " + rs.getDate("obs_ra"));
                System.out.println("Deklination = " + rs.getInt("obs_dec"));
                System.out.println("Sternbild = " + rs.getDouble("con_abbrevation"));
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println("Couldn't handle DB-Query");
            e.printStackTrace();
        } 
    }

    public DBController dbConnection() {
        DBController dbc = DBController.getInstance();
        dbc.initDBConnection();
        dbc.handleDB();
        return dbc;
    }
}
