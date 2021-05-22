package Internal;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

public class SQLite {
    public static boolean firstUse;
    static File dbFolder = new File("data");
    static Path currentDirectory = Paths.get(System.getProperty("user.dir"));
    static Connection mainDB = null;
    static Connection configDB = null;
    
    public static void initDB() {
        if (!Files.isWritable(currentDirectory)) {
            File appData = new File(System.getenv("localappdata") + "\\Speckle");
            appData.mkdir();
            dbFolder = new File(System.getenv("localappdata") + "\\Speckle\\data");
        }
        firstUse = dbFolder.mkdir();
        dbConnect();
        if (firstUse)
            dbTables();
    }
    
    static void dbConnect() {
        try {
            mainDB = DriverManager.getConnection("jdbc:sqlite:" + dbFolder + "/main.sqlite");
            configDB = DriverManager.getConnection("jdbc:sqlite:" + dbFolder + "/config.sqlite");
        } catch (SQLException ex) { 
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    static void dbTables() {
        if (mainDB == null || configDB == null)
            dbConnect();
        String invoice = "CREATE TABLE IF NOT EXISTS Invoice (\"Invoice ID\""
            + " TEXT NOT NULL UNIQUE, \"Customer Name\" TEXT, \"Contact Number\""
            + " TEXT, \"Date of Sale\" TEXT, \"Sale Amount\" TEXT, PRIMARY"
            + " KEY(\"Invoice ID\"));";
        String inventory = "CREATE TABLE IF NOT EXISTS Inventory (\"Product ID\""
            + " TEXT NOT NULL UNIQUE, \"Product Name\" TEXT, \"Price\" TEXT,"
            + " \"Available Quantity\" TEXT, PRIMARY KEY(\"Product ID\"));";
        String configuration = "CREATE TABLE IF NOT EXISTS Configuration (Parameter"
            + " TEXT NOT NULL UNIQUE, Value TEXT, PRIMARY KEY(Parameter));";
        String user = "INSERT INTO Configuration (Parameter) VALUES(\"Username\");";
        String pass = "INSERT INTO Configuration (Parameter) VALUES(\"Password\");";
        String compName = "INSERT INTO Configuration (Parameter) VALUES(\"Company Name\");";
        String compNum = "INSERT INTO Configuration (Parameter) VALUES(\"Company Number\");";
        String compEmail = "INSERT INTO Configuration (Parameter) VALUES(\"Company Email\");";
        String compAdd = "INSERT INTO Configuration (Parameter) VALUES(\"Company Address\");";
        try {
            Statement mainDBquery = mainDB.createStatement();
            Statement configDBquery = configDB.createStatement();
            mainDBquery.execute(invoice);
            mainDBquery.execute(inventory);
            configDBquery.execute(configuration);
            configDBquery.execute(user);
            configDBquery.execute(pass);
            configDBquery.execute(compName);
            configDBquery.execute(compNum);
            configDBquery.execute(compEmail);
            configDBquery.execute(compAdd);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static boolean login(String uname, String passwd) {
        if (configDB == null)
            dbConnect();
        String username = null;
        String password = null;
        String user = "SELECT Value FROM Configuration WHERE Parameter = \"Username\";";
        String pass = "SELECT Value FROM Configuration WHERE Parameter = \"Password\";";
        try {
            Statement configDBquery = configDB.createStatement();
            ResultSet userResult = configDBquery.executeQuery(user);
            username = userResult.getString("Value");
            ResultSet passResult = configDBquery.executeQuery(pass);
            password = passResult.getString("Value");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return uname.equals(username) && passwd.equals(password);
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
    
    public static void updateInven(String id, String name, String price, String quan) {
        if (mainDB == null)
            dbConnect();
        String inventory = "REPLACE INTO Inventory (\"Product ID\", \"Product Name\","
            + " \"Price\", \"Available Quantity\") VALUES(?, ?, ?, ?);";
        try {
            PreparedStatement mainDBquery = mainDB.prepareStatement(inventory);
            mainDBquery.setString(1, id);
            mainDBquery.setString(2, name);
            mainDBquery.setString(3, price);
            mainDBquery.setString(4, quan);
            mainDBquery.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
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
