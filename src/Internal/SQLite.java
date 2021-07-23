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
    static Connection invoiceDB = null;
    
    public static void initDB() {
        if (!Files.isWritable(currentDirectory)) {
            File appData = new File(System.getenv("localappdata") + File.separator + "Speckle");
            appData.mkdir();
            dbFolder = new File(appData + File.separator + "data");
        }
        firstUse = dbFolder.mkdir();
        dbConnect();
        if (firstUse)
            dbTables();
    }
    
    public static void closeDB() {
        try {
            mainDB.close();
            configDB.close();
            invoiceDB.close();
        } catch (SQLException ex) { 
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    static void dbConnect() {
        try {
            mainDB = DriverManager.getConnection("jdbc:sqlite:" + dbFolder + File.separator
                + "main.sqlite");
            configDB = DriverManager.getConnection("jdbc:sqlite:" + dbFolder + File.separator
                + "config.sqlite");
            invoiceDB = DriverManager.getConnection("jdbc:sqlite:" + dbFolder + File.separator
                + "invoice.sqlite");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    static void dbTables() {
        if (mainDB == null || configDB == null)
            dbConnect();
        String invoice = "CREATE TABLE IF NOT EXISTS Invoice (\"Invoice ID\" TEXT NOT"
            + " NULL UNIQUE, \"Customer Name\" TEXT, \"Contact Number\" TEXT, \"Address\""
            + " TEXT, \"Date of Sale\" TEXT, \"Sale Amount\" REAL, PRIMARY KEY(\"Invoice"
            + " ID\"));";
        String inventory = "CREATE TABLE IF NOT EXISTS Inventory (\"Product ID\" TEXT"
            + " NOT NULL UNIQUE, \"Product Name\" TEXT, \"Price\" REAL, \"Available"
            + " Quantity\" INTEGER, PRIMARY KEY(\"Product ID\"));";
        String configuration = "CREATE TABLE IF NOT EXISTS Configuration (Parameter"
            + " TEXT NOT NULL UNIQUE, Value TEXT, PRIMARY KEY(Parameter));";
        String configData = "INSERT INTO Configuration (Parameter) VALUES ('Username'),"
            + " ('Password'), ('Recovery Key'), ('Business Name'), ('Contact Number'),"
            + " ('Email Address'), ('Business Location');";
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
    
    public static boolean logIn(String usrname, String passwd) {
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
        return Internal.Security.getEncodedString(usrname).equals(username) &&
            Internal.Security.validateHash(passwd, password);
    }
    
    public static String getConfigValue(String param) {
        if (configDB == null)
            dbConnect();
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
        if (configDB == null)
            dbConnect();
        String configuration = "UPDATE Configuration SET Value = ? WHERE Parameter = ?;";
        try {
            PreparedStatement configDBquery = configDB.prepareStatement(configuration);
            configDBquery.setString(1, value);
            configDBquery.setString(2, param);
            configDBquery.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void userConfig(String usrname, String passwd) {
        if (configDB == null)
            dbConnect();
        String username = "UPDATE Configuration SET Value = ? WHERE Parameter = 'Username';";
        String password = "UPDATE Configuration SET Value = ? WHERE Parameter = 'Password';";
        try {
            PreparedStatement configDBquery = configDB.prepareStatement(username);
            configDBquery.setString(1, usrname);
            configDBquery.executeUpdate();
            configDBquery = configDB.prepareStatement(password);
            configDBquery.setString(1, passwd);
            configDBquery.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void compConfig(String cname, String cnumber, String cmail, String caddress) {
        if (configDB == null)
            dbConnect();
        String name = "UPDATE Configuration SET Value = ? WHERE Parameter = 'Business Name';";
        String number = "UPDATE Configuration SET Value = ? WHERE Parameter = 'Contact Number';";
        String email = "UPDATE Configuration SET Value = ? WHERE Parameter = 'Email Address';";
        String address = "UPDATE Configuration SET Value = ? WHERE Parameter = 'Business Location';";
        try {
            PreparedStatement configDBquery = configDB.prepareStatement(name);
            configDBquery.setString(1, cname);
            configDBquery.executeUpdate();
            configDBquery = configDB.prepareStatement(number);
            configDBquery.setString(1, cnumber);
            configDBquery.executeUpdate();
            configDBquery = configDB.prepareStatement(email);
            configDBquery.setString(1, cmail);
            configDBquery.executeUpdate();
            configDBquery = configDB.prepareStatement(address);
            configDBquery.setString(1, caddress);
            configDBquery.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static String[][] dashData() {
        if (mainDB == null)
            dbConnect();
        String data[][] = new String[5][2];
        String inventory = "SELECT \"Product Name\", \"Available Quantity\" FROM Inventory"
            + " ORDER BY \"Available Quantity\" LIMIT 5;";
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
        if (mainDB == null)
            dbConnect();
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
        if (mainDB == null)
            dbConnect();
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
    
    public static ResultSet invoTableData(String invID) {
        if (invoiceDB == null)
            dbConnect();
        ResultSet invResult = null;
        String inventory = "SELECT * FROM \"" + invID + "\";";
        try {
            Statement invoiceDBquery = invoiceDB.createStatement();
            invResult = invoiceDBquery.executeQuery(inventory);
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
    
    public static void newInvoice(String invID, String name, String contact, String address,
        String date, String amount) {
        if (mainDB == null)
            dbConnect();
        String invoice = "REPLACE INTO Invoice (\"Invoice ID\", \"Customer Name\","
            + " \"Contact Number\", \"Address\", \"Date of Sale\", \"Sale Amount\")"
            + " VALUES(?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement mainDBquery = mainDB.prepareStatement(invoice);
            mainDBquery.setString(1, invID);
            mainDBquery.setString(2, name);
            mainDBquery.setString(3, contact);
            mainDBquery.setString(4, address);
            mainDBquery.setString(5, date);
            mainDBquery.setString(6, amount);
            mainDBquery.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void newInvoice(String invID) {
        if (invoiceDB == null)
            dbConnect();
        String invoice = "CREATE TABLE IF NOT EXISTS \"" + invID + "\" (\"Product Name\""
            + " TEXT NOT NULL UNIQUE, \"Price\" REAL, \"Purchased Quantity\" INTEGER,"
            + " \"Net Amount\" REAL, PRIMARY KEY(\"Product Name\"));";
        try {
            Statement invoiceDBquery = invoiceDB.createStatement();
            invoiceDBquery.execute(invoice);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void newInvoice(String invID, String name, String price, String quan, String amount) {
        if (invoiceDB == null)
            dbConnect();
        String invoice = "REPLACE INTO \"" + invID + "\" (\"Product Name\", \"Price\","
            + " \"Purchased Quantity\", \"Net Amount\") VALUES(?, ?, ?, ?);";
        try {
            PreparedStatement invoiceDBquery = invoiceDB.prepareStatement(invoice);
            invoiceDBquery.setString(1, name);
            invoiceDBquery.setString(2, price);
            invoiceDBquery.setString(3, quan);
            invoiceDBquery.setString(4, amount);
            invoiceDBquery.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void updateInven(String PID, String name, String price, String quan) {
        if (mainDB == null)
            dbConnect();
        String inventory = "REPLACE INTO Inventory (\"Product ID\", \"Product Name\","
            + " \"Price\", \"Available Quantity\") VALUES(?, ?, ?, ?);";
        try {
            PreparedStatement mainDBquery = mainDB.prepareStatement(inventory);
            mainDBquery.setString(1, PID);
            mainDBquery.setString(2, name);
            mainDBquery.setString(3, price);
            mainDBquery.setString(4, quan);
            mainDBquery.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void remInvoice(String invID) {
        if (mainDB == null || invoiceDB == null)
            dbConnect();
        String invoice = "DELETE FROM Invoice WHERE \"Invoice ID\" = ?;";
        String invTable = "DROP TABLE \"" + invID + "\";";
        try {
            PreparedStatement mainDBquery = mainDB.prepareStatement(invoice);
            Statement invoiceDBquery = invoiceDB.createStatement();
            mainDBquery.setString(1, invID);
            mainDBquery.executeUpdate();
            invoiceDBquery.execute(invTable);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void remRowInven(String PID) {
        if (mainDB == null)
            dbConnect();
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
        if (mainDB == null)
            dbConnect();
        String inventory = "UPDATE Inventory SET \"Available Quantity\" = ? WHERE"
            + " \"Product ID\" = ?;";
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
