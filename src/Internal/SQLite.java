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
import java.sql.ResultSetMetaData;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
        String invoice = "CREATE TABLE IF NOT EXISTS Invoice (\"Invoice ID\" TEXT NOT"
            + " NULL UNIQUE, \"Customer Name\" TEXT, \"Contact Number\" TEXT, \"Date of"
            + " Sale\" TEXT, \"Sale Amount\" TEXT, PRIMARY KEY(\"Invoice ID\"));";
        String inventory = "CREATE TABLE IF NOT EXISTS Inventory (\"Product ID\" TEXT"
            + " NOT NULL UNIQUE, \"Product Name\" TEXT, \"Price\" TEXT, \"Available"
            + " Quantity\" TEXT, PRIMARY KEY(\"Product ID\"));";
        String configuration = "CREATE TABLE IF NOT EXISTS Configuration (Parameter"
            + " TEXT NOT NULL UNIQUE, Value TEXT, PRIMARY KEY(Parameter));";
        String configData = "INSERT INTO Configuration (Parameter) VALUES('Username'),"
            + " ('Password'), ('Business Name'), ('Contact Number'), ('Email Address'),"
            + " ('Business Location');";
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
        return usrname.equals(username) && passwd.equals(password);
    }
    
    public static String configValue(String param) {
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
    
    public static void userConfig(String usrname, String passwd) {
        if(configDB == null)
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
        if(configDB==null)
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
    
    public static TableModel invoData() {
        if (mainDB == null)
            dbConnect();
        ResultSet invResult;
        DefaultTableModel invTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        String invoice = "SELECT \"Invoice ID\", \"Customer Name\", \"Contact Number\","
            + " \"Date of Sale\", \"Sale Amount\" FROM Invoice;";
        try {
            Statement mainDBquery = mainDB.createStatement();
            invResult = mainDBquery.executeQuery(invoice);
            ResultSetMetaData invMeta = invResult.getMetaData();
            int numberOfColumns = invMeta.getColumnCount();
            for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++)
                invTableModel.addColumn(invMeta.getColumnLabel(columnIndex + 1));
            Object[] row = new Object[numberOfColumns];
            while (invResult.next()) {
                for (int i = 0; i < numberOfColumns; i++)
                    row[i] = invResult.getObject(i+1);
                invTableModel.addRow(row);
            }
            return invTableModel;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public static TableModel newInvo() {
        if (mainDB == null)
            dbConnect();
        ResultSet invResult;
        DefaultTableModel invTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex == 0 || columnIndex == 4;
            }
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        };
        String inventory = "SELECT \"Product Name\", \"Price\", \"Available Quantity\""
            + " FROM Inventory;";
        try {
            Statement mainDBquery = mainDB.createStatement();
            invResult = mainDBquery.executeQuery(inventory);
            ResultSetMetaData invMeta = invResult.getMetaData();
            int numberOfColumns = invMeta.getColumnCount();
            invTableModel.addColumn("Products Purchased");
            for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++)
                invTableModel.addColumn(invMeta.getColumnLabel(columnIndex + 1));
            invTableModel.addColumn("Purchased Quantity");
            Object[] row = new Object[numberOfColumns + 2];
            while (invResult.next()) {
                for (int i = 1; i < numberOfColumns + 1; i++) {
                    row[0] = false;
                    row[i] = invResult.getObject(i);
                    row[4] = "";
                }
                invTableModel.addRow(row);
            }
            return invTableModel;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public static TableModel invenData() {
        if (mainDB == null)
            dbConnect();
        ResultSet invResult;
        DefaultTableModel invTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex != 0;
            }
        };
        String inventory = "SELECT * FROM Inventory;";
        try {
            Statement mainDBquery = mainDB.createStatement();
            invResult = mainDBquery.executeQuery(inventory);
            ResultSetMetaData invMeta = invResult.getMetaData();
            int numberOfColumns = invMeta.getColumnCount();
            for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++)
                invTableModel.addColumn(invMeta.getColumnLabel(columnIndex + 1));
            Object[] row = new Object[numberOfColumns];
            while (invResult.next()) {
                for (int i = 0; i < numberOfColumns; i++)
                    row[i] = invResult.getObject(i+1);
                invTableModel.addRow(row);
            }
            return invTableModel;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
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
    
    public static void remRowInvo(String id) {
        if (mainDB == null)
            dbConnect();
        String invoice = "DELETE FROM Invoice WHERE \"Invoice ID\" = ?;";
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
        String inventory = "DELETE FROM Inventory WHERE \"Product ID\" = ?;";
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
        String inventory = "UPDATE Inventory SET \"Available Quantity\" = ? WHERE"
            + " \"Product ID\" = ?;";
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
