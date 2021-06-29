package Internal;

import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Astralsphere Arts
 */

public class Function {
    static String Seed = "0123456789";
    static SecureRandom random = new SecureRandom();
    
    public static String randomID(int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int i=0; i<length; i++)
            builder.append(Seed.charAt(random.nextInt(Seed.length())));
        return builder.toString();
    }
    
    public static TableModel invoTableModel() {
        ResultSet invResult = Internal.SQLite.invoData();
        DefaultTableModel invTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Double.class
            };
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        };
        try {
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
    
    public static TableModel newInvoTableModel() {
        ResultSet invResult = Internal.SQLite.newInvoData();
        DefaultTableModel invTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex == 0 || columnIndex == 5;
            }
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Double.class, java.lang.Integer.class, java.lang.Integer.class
            };
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        };
        try {
            ResultSetMetaData invMeta = invResult.getMetaData();
            int numberOfColumns = invMeta.getColumnCount();
            invTableModel.addColumn("Products Purchased");
            for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++)
                invTableModel.addColumn(invMeta.getColumnLabel(columnIndex + 1));
            invTableModel.addColumn("Purchased Quantity");
            Object[] row = new Object[numberOfColumns + 2];
            while (invResult.next()) {
                row[0] = false;
                for (int i = 1; i < numberOfColumns + 1; i++)
                    row[i] = invResult.getObject(i);
                row[5] = 1;
                invTableModel.addRow(row);
            }
            return invTableModel;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public static TableModel invenTableModel() {
        ResultSet invResult = Internal.SQLite.invenData();
        DefaultTableModel invTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex != 0;
            }
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class,
                java.lang.Double.class, java.lang.Integer.class
            };
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        };
        try {
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
}
