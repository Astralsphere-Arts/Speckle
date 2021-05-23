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
        String invoice = "CREATE TABLE IF NOT EXISTS Invoice ('Invoice ID' TEXT NOT"
            + " NULL UNIQUE, 'Customer Name' TEXT, 'Contact Number' TEXT, 'Date of"
            + " Sale' TEXT, 'Sale Amount' TEXT, PRIMARY KEY('Invoice ID'));";
        String inventory = "CREATE TABLE IF NOT EXISTS Inventory ('Product ID' TEXT"
            + " NOT NULL UNIQUE, 'Product Name' TEXT, 'Price' TEXT, 'Available"
            + " Quantity' TEXT, PRIMARY KEY('Product ID'));";
        String configuration = "CREATE TABLE IF NOT EXISTS Configuration (Parameter"
            + " TEXT NOT NULL UNIQUE, Value TEXT, PRIMARY KEY(Parameter));";
        String configData = "INSERT INTO Configuration (Parameter) VALUES('Username'),"
            + " ('Password'), ('Company Name'), ('Company Number'), ('Company Email'),"
            + " ('Company Address');";
        try {
            Statement mainDBquery = mainDB.createStatement();
            Statement configDBquery = configDB.createStatement();
            mainDBquery.execute(invoice);
            mainDBquery.execute(inventory);
            configDBquery.execute(configuration);
            configDBquery.execute(configData);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static boolean logIn(String uname, String passwd) {
        if (configDB == null)
            dbConnect();
        String username = null;
        String password = null;
        String user = "SELECT Value FROM Configuration WHERE Parameter = 'Username';";
        String pass = "SELECT Value FROM Configuration WHERE Parameter = 'Password';";
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
    
    public static void signUp1(String usname, String pass) {
        if(configDB == null)
            dbConnect();
        String username = "UPDATE Configuration SET Value=? WHERE Parameter='Username';"; 
        String password = "UPDATE Configuration SET Value=? WHERE Parameter='Password';";
        try {
            PreparedStatement configDBquery = configDB.prepareStatement(username);
            configDBquery.setString(1, usname);
            configDBquery.executeUpdate();
            configDBquery = configDB.prepareStatement(password);
            configDBquery.setString(1, pass);
            configDBquery.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
     
    public static void signUp2(String cname,  String cnumber, String cmail, String caddress) {
        if(configDB==null)
            dbConnect();
        String name = "UPDATE Configuration SET Value=? WHERE Parameter='Company Name';";
        String number = "UPDATE Configuration SET Value=? WHERE Parameter='Company Number';";
        String mail = "UPDATE Configuration SET Value=? WHERE Parameter='Company Email';";
        String address = "UPDATE Configuration SET Value=? WHERE Parameter='Company Address';";
        try {
            PreparedStatement configDBquery = configDB.prepareStatement(name);
            configDBquery.setString(1, cname);
            configDBquery.executeUpdate();
            configDBquery = configDB.prepareStatement(number);
            configDBquery.setString(1, cnumber);
            configDBquery.executeUpdate();
            configDBquery = configDB.prepareStatement(mail);
            configDBquery.setString(1, cmail);
            configDBquery.executeUpdate();
            configDBquery = configDB.prepareStatement(address);
            configDBquery.setString(1, caddress);
            configDBquery.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static ResultSet invoData() {
        if (mainDB == null)
            dbConnect();
        ResultSet invResult = null;
        String invoice = "SELECT 'Invoice ID', 'Customer Name', 'Contact Number',"
            + " 'Date of Sale', 'Sale Amount' FROM Invoice;";
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
        String inventory = "REPLACE INTO Inventory ('Product ID', 'Product Name',"
            + " 'Price', 'Available Quantity') VALUES(?, ?, ?, ?);";
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
    
    public static void remRowInvo(String id) {
        if (mainDB == null)
            dbConnect();
        String invoice = "DELETE FROM Invoice WHERE 'Invoice ID'=?;";
        try {
            PreparedStatement mainDBquery = mainDB.prepareStatement(invoice);
            mainDBquery.setString(1,id);
            mainDBquery.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void remRowInven(String id) {
        if (mainDB == null)
            dbConnect();
        String inventory = "DELETE FROM Inventory WHERE 'Product ID'=?;";
        try {
            PreparedStatement mainDBquery = mainDB.prepareStatement(inventory);
            mainDBquery.setString(1,id);
            mainDBquery.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void updateStock(String id, String quan) {
        if (mainDB == null)
            dbConnect();
        String inventory = "UPDATE Inventory SET 'Available Quantity'=? WHERE"
            + " 'Product ID'=?;";
        try {
            PreparedStatement mainDBquery = mainDB.prepareStatement(inventory);
            mainDBquery.setString(1, quan);
            mainDBquery.setString(2, id);
            mainDBquery.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
