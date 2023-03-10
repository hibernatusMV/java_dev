package de.util.database;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;

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

    private void handleDB(String statement) {
        try { 
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(statement);
            ResultSetMetaData rsmd = rs.getMetaData();
            
            while (rs.next()) {
                for (int i=1; i<=rsmd.getColumnCount();i++) {
                    System.out.println(getData(rs, rsmd, i));
                }
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println("Couldn't handle DB-Query");
            e.printStackTrace();
        } 
    }

    /**
     * Returns value for specified column and datatype
     * Method gets the actual datatype for the indexed column
     * and gets the value for the indexed column with the matching get method 
     * @param rs                The resultset for the given SQL statement
     * @param rsmd              The resultset meta data
     * @param i                 Index for the active column for the data row
     * @return                  returned data as string
     * @throws SQLException
     */
    private String getData(ResultSet rs, ResultSetMetaData rsmd, int i) throws SQLException {
        String data = "";
        String dataType = "";

        String pattern = "dd. MMMMM yyyy HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
               
        dataType = rsmd.getColumnTypeName(i);

        switch(dataType) {
            case "INT":
                data = String.valueOf(rs.getInt(i));
                break;
            case "VARCHAR":
                data = rs.getString(i);
                break;
            case "DECIMAL":
                data = String.valueOf(rs.getDouble(i));
                break;
            case "DATETIME":
                data = simpleDateFormat.format(rs.getDate(i));
                break;
            case "TEXT":
                data = rs.getString(i);
                break;
            case "BLOB":
                data = "BLOB";
                break;
            default:
                data = "";
        }
        
        return data;
    }

    public DBController dbGetData(String selectStmt) {
        DBController dbc = DBController.getInstance();
        dbc.initDBConnection();
        dbc.handleDB(selectStmt);
        return dbc;
    }
}
