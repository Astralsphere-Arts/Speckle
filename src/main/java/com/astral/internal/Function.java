package Internal;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
    static String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    static SecureRandom random = new SecureRandom();
    static File invFolder = new File(FileSystemView.getFileSystemView()
        .getDefaultDirectory().getPath() + File.separator + "Speckle");
    static java.awt.Color TableHeader = new java.awt.Color(240, 240, 240);
    
    public static String randomID(int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int i=0; i<length; i++)
            builder.append(Seed.charAt(random.nextInt(Seed.length())));
        return builder.toString();
    }
    
    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
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
            invTableModel.addColumn(invMeta.getColumnLabel(1));
            invTableModel.addColumn(invMeta.getColumnLabel(2));
            invTableModel.addColumn(invMeta.getColumnLabel(3));
            invTableModel.addColumn(invMeta.getColumnLabel(5));
            invTableModel.addColumn(invMeta.getColumnLabel(6));
            Object[] row = new Object[5];
            while (invResult.next()) {
                row[0] = invResult.getObject(1);
                row[1] = invResult.getObject(2);
                row[2] = invResult.getObject(3);
                row[3] = invResult.getObject(5);
                row[4] = invResult.getObject(6);
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
    
    public static void invoicePDF(String invID) {
        invFolder.mkdir();
        invPath = new File(invFolder + File.separator + invID + ".pdf");
        ResultSet invoData = Internal.SQLite.invoData(invID);
        ResultSet invoTableData = Internal.SQLite.invoTableData(invID);
        try (Document document = new Document()) {
            PdfWriter.getInstance(document, new FileOutputStream(invPath));
            document.open();
            Paragraph para = new Paragraph(Internal.SQLite.getConfigValue("Business Name"),
                FontFactory.getFont(FontFactory.TIMES_BOLD, 20));
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            para = new Paragraph(Internal.SQLite.getConfigValue("Business Location"));
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            para = new Paragraph("Contact Number : " + Internal.SQLite.getConfigValue("Contact Number")
                + "    Email : " + Internal.SQLite.getConfigValue("Email Address"));
            para.setAlignment(Element.ALIGN_CENTER);
            para.setSpacingAfter(30f);
            document.add(para);
            document.add(new LineSeparator());
            PdfPTable table = new PdfPTable(2);
            para = new Paragraph();
            Chunk chunk = new Chunk("Invoice Number : ", FontFactory.getFont(FontFactory.HELVETICA_BOLD));
            para.add(chunk);
            chunk = new Chunk(invoData.getString("Invoice ID"), FontFactory.getFont(FontFactory.HELVETICA));
            para.add(chunk);
            PdfPCell cell = new PdfPCell(para);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPadding(0);
            table.addCell(cell);
            para = new Paragraph();
            chunk = new Chunk("Invoice Date : ", FontFactory.getFont(FontFactory.HELVETICA_BOLD));
            para.add(chunk);
            chunk = new Chunk(invoData.getString("Date of Sale"), FontFactory.getFont(FontFactory.HELVETICA));
            para.add(chunk);
            cell = new PdfPCell(para);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPadding(0);
            table.addCell(cell);
            table.setSpacingAfter(20f);
            table.setSpacingBefore(20f);
            table.setWidthPercentage(100);
            document.add(table);
            para = new Paragraph();
            chunk = new Chunk("Billed To : ", FontFactory.getFont(FontFactory.HELVETICA_BOLD));
            para.add(chunk);
            chunk = new Chunk(invoData.getString("Customer Name"), FontFactory.getFont(FontFactory.HELVETICA));
            para.add(chunk);
            document.add(para);
            para = new Paragraph();
            chunk = new Chunk("Contact Number : ", FontFactory.getFont(FontFactory.HELVETICA_BOLD));
            para.add(chunk);
            chunk = new Chunk(invoData.getString("Contact Number"), FontFactory.getFont(FontFactory.HELVETICA));
            para.add(chunk);
            document.add(para);
            para = new Paragraph();
            chunk = new Chunk("Billing Address : ", FontFactory.getFont(FontFactory.HELVETICA_BOLD));
            para.add(chunk);
            chunk = new Chunk(invoData.getString("Address"), FontFactory.getFont(FontFactory.HELVETICA));
            para.add(chunk);
            document.add(para);
            float[] widths = {10f, 44f, 15f, 14f, 17f};
            table = new PdfPTable(widths);
            cell = new PdfPCell(new Paragraph("S.No.", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            cell.setBackgroundColor(TableHeader);
            cell.setPadding(10f);
            cell.setPaddingLeft(11f);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("Product Name", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            cell.setBackgroundColor(TableHeader);
            cell.setPadding(10f);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("Unit Price", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            cell.setBackgroundColor(TableHeader);
            cell.setPadding(10f);
            cell.setPaddingLeft(11f);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("Quantity", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            cell.setBackgroundColor(TableHeader);
            cell.setPadding(10f);
            cell.setPaddingLeft(12f);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("Net Amount", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            cell.setBackgroundColor(TableHeader);
            cell.setPadding(10f);
            table.addCell(cell);
            int i = 1;
            while (invoTableData.next()) {
                cell = new PdfPCell(new Paragraph(Integer.toString(i)));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPadding(10f);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph(invoTableData.getString("Product Name")));
                cell.setPadding(10f);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph(invoTableData.getString("Price")));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPadding(10f);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph(invoTableData.getString("Purchased Quantity")));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPadding(10f);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph(invoTableData.getString("Net Amount")));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPadding(10f);
                table.addCell(cell);
                i++;
            }
            cell = new PdfPCell(new Paragraph("Total Amount", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setPadding(10f);
            cell.setColspan(4);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph(invoData.getString("Sale Amount")));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setPadding(10f);
            table.addCell(cell);
            table.setSpacingBefore(40f);
            table.setWidthPercentage(100);
            document.add(table);
            document.close();
        } catch (DocumentException | IOException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void invoiceCSVex(File CSV) {
        ResultSet invoData = Internal.SQLite.invoData();
        try (FileWriter invCSV = new FileWriter(CSV)) {
            ResultSetMetaData invMeta = invoData.getMetaData();
            invCSV.write("\"" + invMeta.getColumnLabel(1) + "\",");
            invCSV.write("\"" + invMeta.getColumnLabel(2) + "\",");
            invCSV.write("\"" + invMeta.getColumnLabel(3) + "\",");
            invCSV.write("\"" + invMeta.getColumnLabel(4) + "\",");
            invCSV.write("\"" + invMeta.getColumnLabel(5) + "\",");
            invCSV.write("\"" + invMeta.getColumnLabel(6) + "\"\r\n");
            while (invoData.next()) {
                invCSV.write("\"" + invoData.getString("Invoice ID") + "\",");
                invCSV.write("\"" + invoData.getString("Customer Name") + "\",");
                invCSV.write("\"" + invoData.getString("Contact Number") + "\",");
                invCSV.write("\"" + invoData.getString("Address") + "\",");
                invCSV.write("\"" + invoData.getString("Date of Sale") + "\",");
                invCSV.write("\"" + invoData.getString("Sale Amount") + "\"\r\n");
            }
            invCSV.close();
        } catch (IOException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void invenCSVex(File CSV) {
        ResultSet invenData = Internal.SQLite.invenData();
        try (FileWriter invCSV = new FileWriter(CSV)) {
            ResultSetMetaData invMeta = invenData.getMetaData();
            invCSV.write("\"" + invMeta.getColumnLabel(1) + "\",");
            invCSV.write("\"" + invMeta.getColumnLabel(2) + "\",");
            invCSV.write("\"" + invMeta.getColumnLabel(3) + "\",");
            invCSV.write("\"" + invMeta.getColumnLabel(4) + "\"\r\n");
            while (invenData.next()) {
                invCSV.write("\"" + invenData.getString("Product ID") + "\",");
                invCSV.write("\"" + invenData.getString("Product Name") + "\",");
                invCSV.write("\"" + invenData.getString("Price") + "\",");
                invCSV.write("\"" + invenData.getString("Available Quantity") + "\"\r\n");
            }
            invCSV.close();
        } catch (IOException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void invenCSVim(File CSV) {
        try {
            String CSV_Line;
            java.io.BufferedReader CSV_Reader = new java.io.BufferedReader(new java.io.FileReader(CSV));
            while ((CSV_Line = CSV_Reader.readLine()) != null) {
                String[] CSV_Parts = CSV_Line.replace("\"", "").split(",");
                String PID = CSV_Parts[0];
                String name = CSV_Parts[1];
                String price = CSV_Parts[2];
                String quan = CSV_Parts[3];
                if (!PID.equals("Product ID"))
                    Internal.SQLite.updateInven(PID, name, price, quan);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
