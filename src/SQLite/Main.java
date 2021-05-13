package SQLite;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
        } catch (SQLException e) { 
            System.out.println(e.getMessage());
        }
    }
    public static void initDB() {
        if (mainDB == null || configDB == null) {
            firstUse = dbFolder.mkdir();
            dbConnect();
        }
        String invoice = "CREATE TABLE IF NOT EXISTS Invoice (ID INTEGER NOT NULL,"
            + " Name TEXT NOT NULL, Contact INTEGER NOT NULL, Date TEXT NOT NULL,"
            + " Amount REAL NOT NULL, PRIMARY KEY(ID));";
        String inventory = "CREATE TABLE IF NOT EXISTS Inventory (ID INTEGER NOT NULL,"
            + " Name TEXT NOT NULL, Price REAL NOT NULL, Quantity INTEGER NOT NULL,"
            + " PRIMARY KEY(ID));";
        try {
            Statement mainDBquery = mainDB.createStatement();
            mainDBquery.execute(invoice);
            mainDBquery.execute(inventory);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
