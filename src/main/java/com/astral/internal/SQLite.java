package com.astral.internal;

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
import java.util.Base64;
import javax.swing.JOptionPane;
import com.password4j.Password;

/**
 *
 * @author Astralsphere Arts
 */

public class SQLite {
    static Connection mainDB = null;
    static Connection configDB = null;
    static File dbFolder = new File("data");
    static Path currentDirectory = Paths.get(System.getProperty("user.dir"));
    
    public static void initDB() {
        if (!Files.isWritable(currentDirectory))
            dbFolder = new File(System.getenv("appdata") + File.separator + "Speckle");
        dbFolder.mkdir();
        boolean configExists = new File(dbFolder + File.separator + "config.sqlite").isFile();
        try {
            String configPath = "jdbc:sqlite:" + dbFolder + File.separator + "config.sqlite";
            String configSchema = "CREATE TABLE IF NOT EXISTS Configuration (Parameter TEXT NOT NULL UNIQUE, Value TEXT, PRIMARY KEY(Parameter));";
            String configData = "REPLACE INTO Configuration (Parameter, Value) VALUES ('Signed Up', 'False');";
            configDB = DriverManager.getConnection(configPath, org.sqlite.mc.SQLiteMCChacha20Config.getDefault().withKey("7&NFV#&LuhDm7Zk#!ZYN").build().toProperties());
            Statement configDBquery = configDB.createStatement();
            configDBquery.execute(configSchema);
            if (!configExists)
                configDBquery.execute(configData);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void closeDB() {
        try {
            mainDB.close();
            configDB.close();
        } catch (SQLException ex) { 
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void dbConnect() {
        String mainDBPath = "jdbc:sqlite:" + dbFolder + File.separator + "main.sqlite";
        String invoiceSchema = "CREATE TABLE IF NOT EXISTS Invoice (\"Invoice ID\" TEXT NOT NULL UNIQUE, \"Customer Name\" TEXT, \"Contact Number\" TEXT,"
            + " \"Address\" TEXT, \"Date of Sale\" TEXT, \"GST Amount\" REAL, \"Sale Amount\" REAL, \"Products Purchased\" TEXT, PRIMARY KEY(\"Invoice ID\"));";
        String inventorySchema = "CREATE TABLE IF NOT EXISTS Inventory (\"Product ID\" TEXT NOT NULL UNIQUE, \"Product Name\" TEXT, \"Price\" REAL,"
            + " \"GST Rate\" INTEGER, \"Available Quantity\" INTEGER, PRIMARY KEY(\"Product ID\"));";
        try {
            mainDB = DriverManager.getConnection(mainDBPath, org.sqlite.mc.SQLiteMCChacha20Config.getDefault().withKey("7&NFV#&LuhDm7Zk#!ZYN").build().toProperties());
            Statement mainDBquery = mainDB.createStatement();
            mainDBquery.execute(invoiceSchema);
            mainDBquery.execute(inventorySchema);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static boolean logIn(String usrname, String passwd) {
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
        return Base64.getEncoder().encodeToString(usrname.getBytes()).equals(username) && Password.check(passwd, password).withArgon2();
    }
    
    public static String getConfigValue(String param) {
        String value = null;
        String configuration = "SELECT Value FROM Configuration WHERE Parameter = ?;";
        try {
            PreparedStatement configDBquery = configDB.prepareStatement(configuration);
            configDBquery.setString(1, param);
            ResultSet configResult = configDBquery.executeQuery();
            value = configResult.getString("Value");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return value;
    }
    
    public static void setConfigValue(String param, String value) {
        String configuration = "REPLACE INTO Configuration (Parameter, Value) VALUES (?, ?);";
        try {
            PreparedStatement configDBquery = configDB.prepareStatement(configuration);
            configDBquery.setString(1, param);
            configDBquery.setString(2, value);
            configDBquery.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void userConfig(String username, String password, String recoveryKey) {
        String configuration = "REPLACE INTO Configuration (Parameter, Value) VALUES ('Username', ?), ('Password', ?), ('Recovery Key', ?);";
        try {
            PreparedStatement configDBquery = configDB.prepareStatement(configuration);
            configDBquery.setString(1, username);
            configDBquery.setString(2, password);
            configDBquery.setString(3, recoveryKey);
            configDBquery.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void compConfig(String businessName, String contactNumber, String emailAddress, String businessLocation) {
        String configuration = "REPLACE INTO Configuration (Parameter, Value) VALUES ('Business Name', ?), ('Contact Number', ?), ('Email Address', ?), ('Business Location', ?);";
        try {
            PreparedStatement configDBquery = configDB.prepareStatement(configuration);
            configDBquery.setString(1, businessName);
            configDBquery.setString(2, contactNumber);
            configDBquery.setString(3, emailAddress);
            configDBquery.setString(4, businessLocation);
            configDBquery.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static String[][] dashData() {
        String data[][] = new String[5][2];
        String inventory = "SELECT \"Product Name\", \"Available Quantity\" FROM Inventory ORDER BY \"Available Quantity\" LIMIT 5;";
        try {
            Statement mainDBquery = mainDB.createStatement();
            ResultSet invResult = mainDBquery.executeQuery(inventory);
            int i = 0;
            while (invResult.next()) {
                data[i][0] = invResult.getString("Product Name");
                data[i][1] = invResult.getString("Available Quantity");
                i++;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return data;
    }
    
    public static ResultSet invoData() {
        ResultSet invResult = null;
        String invoice = "SELECT * FROM Invoice;";
        try {
            Statement mainDBquery = mainDB.createStatement();
            invResult = mainDBquery.executeQuery(invoice);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return invResult;
    }
    
    public static ResultSet invoData(String invID) {
        ResultSet invResult = null;
        String invoice = "SELECT * FROM Invoice WHERE \"Invoice ID\" = ?;";
        try {
            PreparedStatement mainDBquery = mainDB.prepareStatement(invoice);
            mainDBquery.setString(1, invID);
            invResult = mainDBquery.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return invResult;
    }
    
    public static ResultSet invenData() {
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
    
    public static void newInvoice(String invID, String name, String contact, String address, String date, String gst, String amount, String products) {
        String invoice = "REPLACE INTO Invoice (\"Invoice ID\", \"Customer Name\", \"Contact Number\", \"Address\", \"Date of Sale\", \"GST Amount\","
            + " \"Sale Amount\", \"Products Purchased\") VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement mainDBquery = mainDB.prepareStatement(invoice);
            mainDBquery.setString(1, invID);
            mainDBquery.setString(2, name);
            mainDBquery.setString(3, contact);
            mainDBquery.setString(4, address);
            mainDBquery.setString(5, date);
            mainDBquery.setString(6, gst);
            mainDBquery.setString(7, amount);
            mainDBquery.setString(8, products);
            mainDBquery.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void updateInven(String PID, String productName, String price, String gstRate, String availableQuantity) {
        String inventory = "REPLACE INTO Inventory (\"Product ID\", \"Product Name\", \"Price\", \"GST Rate\", \"Available Quantity\") VALUES(?, ?, ?, ?, ?);";
        try {
            PreparedStatement mainDBquery = mainDB.prepareStatement(inventory);
            mainDBquery.setString(1, PID);
            mainDBquery.setString(2, productName);
            mainDBquery.setString(3, price);
            mainDBquery.setString(4, gstRate);
            mainDBquery.setString(5, availableQuantity);
            mainDBquery.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void remInvoice(String invID) {
        String invoice = "DELETE FROM Invoice WHERE \"Invoice ID\" = ?;";
        try {
            PreparedStatement mainDBquery = mainDB.prepareStatement(invoice);
            mainDBquery.setString(1, invID);
            mainDBquery.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void remRowInven(String PID) {
        String inventory = "DELETE FROM Inventory WHERE \"Product ID\" = ?;";
        try {
            PreparedStatement mainDBquery = mainDB.prepareStatement(inventory);
            mainDBquery.setString(1, PID);
            mainDBquery.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void updateStock(String PID, String quan) {
        String inventory = "UPDATE Inventory SET \"Available Quantity\" = ? WHERE \"Product ID\" = ?;";
        try {
            PreparedStatement mainDBquery = mainDB.prepareStatement(inventory);
            mainDBquery.setString(1, quan);
            mainDBquery.setString(2, PID);
            mainDBquery.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
