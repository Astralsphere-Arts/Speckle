package SQLite;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Astralsphere Arts
 */

public class Main {
    static boolean firstUse;
    static File dbFolder = new File("data");
    static Connection mainDB = null;
    static Connection configDB = null;
    
    public static void dbConnect() {
        try {
            mainDB = DriverManager.getConnection("jdbc:sqlite:data/main.sqlite");
            configDB = DriverManager.getConnection("jdbc:sqlite:data/config.sqlite");
        } catch (SQLException ex) { 
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void initDB() {
        if (mainDB == null || configDB == null) {
            firstUse = dbFolder.mkdir();
            dbConnect();
        }
        String invoice = "CREATE TABLE IF NOT EXISTS Invoice (\"Invoice ID\""
            + " TEXT NOT NULL, \"Customer Name\" TEXT NOT NULL, \"Contact Number\""
            + " INTEGER NOT NULL, \"Date of Sale\" TEXT NOT NULL, \"Sale Amount\""
            + " REAL NOT NULL, PRIMARY KEY(\"Customer ID\"));";
        String inventory = "CREATE TABLE IF NOT EXISTS Inventory (\"Product ID\""
            + " TEXT NOT NULL, \"Product Name\" TEXT NOT NULL, \"Price\" REAL"
            + " NOT NULL, \"Available Quantity\" INTEGER NOT NULL, PRIMARY KEY"
            + "(\"Product ID\"));";
        try {
            Statement mainDBquery = mainDB.createStatement();
            mainDBquery.execute(invoice);
            mainDBquery.execute(inventory);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static ResultSet invoData() {
        if (mainDB == null)
            dbConnect();
        ResultSet invResult = null;
        String invoice = "SELECT \"Invoice ID\", \"Customer Name\", \"Contact Number\","
            + " \"Date of Sale\", \"Sale Amount\" FROM Invoice;";
        try {
            Statement mainDBquery = mainDB.createStatement();
            invResult = mainDBquery.executeQuery(invoice);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return invResult;
    }
    
    public static ResultSet invenData() {
        if (mainDB == null)
            dbConnect();
        ResultSet invResult = null;
        String inventory = "SELECT * FROM Inventory;";
        try {
            Statement mainDBquery = mainDB.createStatement();
            invResult = mainDBquery.executeQuery(inventory);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return invResult;
    }
    
    public static void remRowInvo(String ID) {
        if (mainDB == null)
            dbConnect();
        String invoice = "DELETE FROM Invoice WHERE \"Invoice ID\"=?;";
        try {
            PreparedStatement mainDBquery = mainDB.prepareStatement(invoice);
            mainDBquery.setString(1,ID);
            mainDBquery.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void remRowInven(String ID) {
        if (mainDB == null)
            dbConnect();
        String inventory = "DELETE FROM Inventory WHERE \"Product ID\"=?;";
        try {
            PreparedStatement mainDBquery = mainDB.prepareStatement(inventory);
            mainDBquery.setString(1,ID);
            mainDBquery.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
