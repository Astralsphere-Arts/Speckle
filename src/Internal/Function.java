package Internal;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Astralsphere Arts
 */

public class Function {
    public static File invPath;
    static String Seed = "0123456789";
    static SecureRandom random = new SecureRandom();
    static File invFolder = new File(FileSystemView.getFileSystemView()
        .getDefaultDirectory().getPath() + File.separator + "Speckle");
    private static java.awt.Color TableHeader = new java.awt.Color(240, 240, 240);
    
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
        ResultSet invResult = Internal.SQLite.invenData();
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
    
    public static void invoicePDF(String id) {
        invFolder.mkdir();
        invPath = new File(invFolder + File.separator + id + ".pdf");
        ResultSet invoData = Internal.SQLite.invoData(id);
        ResultSet invoTableData = Internal.SQLite.invoTableData(id);
        try (Document document = new Document()) {
            PdfWriter.getInstance(document, new FileOutputStream(invPath));
            document.open();
            Paragraph para = new Paragraph(Internal.SQLite.configValue("Business Name"),
                FontFactory.getFont(FontFactory.TIMES_BOLD, 20));
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            para = new Paragraph(Internal.SQLite.configValue("Business Location"));
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            para = new Paragraph("Contact No. : " + Internal.SQLite.configValue("Contact Number")
                + "    Email : " + Internal.SQLite.configValue("Email Address"));
            para.setAlignment(Element.ALIGN_CENTER);
            para.setSpacingAfter(50f);
            document.add(para);
            document.add(new Paragraph("Invoice ID : " + invoData.getString("Invoice ID")));
            document.add(new Paragraph("Date : " + invoData.getString("Date of Sale")));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Billed To : " + invoData.getString("Customer Name")));
            document.add(new Paragraph("Contact No. : " + invoData.getString("Contact Number")));
            document.add(new Paragraph("Billing Address : " + invoData.getString("Address")));
            float[] widths = {10f, 47f, 14f, 13f, 16f};
            PdfPTable table = new PdfPTable(widths);
            PdfPCell cell = new PdfPCell(new Paragraph("S.No."));
            cell.setBackgroundColor(TableHeader);
            cell.setPadding(10f);
            cell.setPaddingLeft(12f);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("Product Name"));
            cell.setBackgroundColor(TableHeader);
            cell.setPadding(10f);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("Unit Price"));
            cell.setBackgroundColor(TableHeader);
            cell.setPadding(10f);
            cell.setPaddingLeft(11f);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("Quantity"));
            cell.setBackgroundColor(TableHeader);
            cell.setPadding(10f);
            cell.setPaddingLeft(12f);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("Net Amount"));
            cell.setBackgroundColor(TableHeader);
            cell.setPadding(10f);
            table.addCell(cell);
            int i = 1;
            while (invoTableData.next()) {
                cell = new PdfPCell(new Paragraph(Integer.toString(i)));
                cell.setPadding(10f);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph(invoTableData.getString("Product Name")));
                cell.setPadding(10f);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph(invoTableData.getString("Price")));
                cell.setPadding(10f);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph(invoTableData.getString("Purchased Quantity")));
                cell.setPadding(10f);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph(invoTableData.getString("Net Amount")));
                cell.setPadding(10f);
                table.addCell(cell);
                i++;
            }
            cell = new PdfPCell(new Paragraph("Total Amount"));
            cell.setPadding(10f);
            cell.setColspan(4);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph(invoData.getString("Sale Amount")));
            cell.setPadding(10f);
            table.addCell(cell);
            table.setSpacingBefore(50f);
            table.setWidthPercentage(100);
            document.add(table);
            document.close();
        } catch (DocumentException | IOException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
