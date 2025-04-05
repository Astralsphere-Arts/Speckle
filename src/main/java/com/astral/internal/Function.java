package com.astral.internal;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
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
    static File invFolder = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + File.separator + "Speckle");
    static java.awt.Color TableHeader = new java.awt.Color(224, 224, 224);
    
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
    
    public static boolean checkPass(String password) {
        int i; char c;
        boolean hasNum = false, hasCap = false, hasLow = false;
        for (i = 0; i < password.length(); i++) {
            c = password.charAt(i);
            if (Character.isDigit(c))
                hasNum = true;
            if (Character.isUpperCase(c))
                hasCap = true;
            if (Character.isLowerCase(c))
                hasLow = true;
        }
        return hasNum && hasCap && hasLow;
    }
    
    public static TableModel invoTableModel() {
        ResultSet invResult = com.astral.internal.SQLite.invoData();
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
            invTableModel.addColumn(invMeta.getColumnLabel(7));
            Object[] row = new Object[5];
            while (invResult.next()) {
                row[0] = invResult.getObject(1);
                row[1] = invResult.getObject(2);
                row[2] = invResult.getObject(3);
                row[3] = invResult.getObject(5);
                row[4] = invResult.getObject(7);
                invTableModel.addRow(row);
            }
            return invTableModel;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public static TableModel newInvoTableModel() {
        ResultSet invResult = com.astral.internal.SQLite.invenData();
        DefaultTableModel invTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex == 0 || columnIndex == 6;
            }
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Double.class, java.lang.Integer.class, java.lang.Integer.class, 
                java.lang.Integer.class
            };
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        };
        try {
            ResultSetMetaData invMeta = invResult.getMetaData();
            int numberOfColumns = invMeta.getColumnCount();
            invTableModel.addColumn("Purchased");
            for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++)
                invTableModel.addColumn(invMeta.getColumnLabel(columnIndex + 1));
            invTableModel.addColumn("Purchased Quantity");
            Object[] row = new Object[numberOfColumns + 2];
            while (invResult.next()) {
                row[0] = false;
                for (int i = 1; i < numberOfColumns + 1; i++)
                    row[i] = invResult.getObject(i);
                row[6] = 1;
                invTableModel.addRow(row);
            }
            return invTableModel;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public static TableModel invenTableModel() {
        ResultSet invResult = com.astral.internal.SQLite.invenData();
        DefaultTableModel invTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class,
                java.lang.Double.class, java.lang.Integer.class,
                java.lang.Integer.class
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
        File invSubFolder = new File(invFolder + File.separator + new java.text.SimpleDateFormat("yyyy - MMMM").format(new java.util.Date()));
        invSubFolder.mkdir();
        invPath = new File(invSubFolder + File.separator + invID + ".pdf");
        ResultSet invoiceData = com.astral.internal.SQLite.invoData(invID);
        try (Document document = new Document()) {
            PdfWriter.getInstance(document, new FileOutputStream(invPath));
            Font IBMPlex = new Font(BaseFont.createFont("/fonts/IBMPlexSansDevanagari-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 10);
            document.open();
            Paragraph para = new Paragraph(com.astral.internal.SQLite.getConfigValue("Business Name"), FontFactory.getFont(FontFactory.TIMES_BOLD, 20));
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            para = new Paragraph(com.astral.internal.SQLite.getConfigValue("Business Location"));
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            para = new Paragraph("Contact Number : " + com.astral.internal.SQLite.getConfigValue("Contact Number") + "    Email : " + com.astral.internal.SQLite.getConfigValue("Email Address"));
            para.setAlignment(Element.ALIGN_CENTER);
            para.setSpacingAfter(30f);
            document.add(para);
            document.add(new LineSeparator());
            PdfPTable table = new PdfPTable(2);
            para = new Paragraph();
            Chunk chunk = new Chunk("Invoice Number : ", FontFactory.getFont(FontFactory.HELVETICA_BOLD));
            para.add(chunk);
            chunk = new Chunk(invoiceData.getString("Invoice ID"), FontFactory.getFont(FontFactory.HELVETICA));
            para.add(chunk);
            PdfPCell cell = new PdfPCell(para);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPadding(0);
            table.addCell(cell);
            para = new Paragraph();
            chunk = new Chunk("Invoice Date : ", FontFactory.getFont(FontFactory.HELVETICA_BOLD));
            para.add(chunk);
            chunk = new Chunk(invoiceData.getString("Date of Sale"), FontFactory.getFont(FontFactory.HELVETICA));
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
            chunk = new Chunk(invoiceData.getString("Customer Name"), FontFactory.getFont(FontFactory.HELVETICA));
            para.add(chunk);
            document.add(para);
            para = new Paragraph();
            chunk = new Chunk("Contact Number : ", FontFactory.getFont(FontFactory.HELVETICA_BOLD));
            para.add(chunk);
            chunk = new Chunk(invoiceData.getString("Contact Number"), FontFactory.getFont(FontFactory.HELVETICA));
            para.add(chunk);
            document.add(para);
            para = new Paragraph();
            chunk = new Chunk("Billing Address : ", FontFactory.getFont(FontFactory.HELVETICA_BOLD));
            para.add(chunk);
            chunk = new Chunk(invoiceData.getString("Address"), FontFactory.getFont(FontFactory.HELVETICA));
            para.add(chunk);
            document.add(para);
            float[] widths = {10f, 36f, 13f, 16f, 8f, 17f};
            table = new PdfPTable(widths);
            cell = new PdfPCell(new Paragraph("S.No.", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(TableHeader);
            cell.setPadding(10f);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("Product Name", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
            cell.setBackgroundColor(TableHeader);
            cell.setPadding(10f);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("Quantity", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(TableHeader);
            cell.setPadding(10f);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("Price", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(TableHeader);
            cell.setPadding(10f);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("GST", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(TableHeader);
            cell.setPadding(10f);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("Net Amount", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(TableHeader);
            cell.setPadding(10f);
            table.addCell(cell);
            org.json.JSONArray productData = new org.json.JSONArray(invoiceData.getString("Products Purchased"));
            for (int i = 0, size = productData.length(); i < size; i++) {
                org.json.JSONObject product = productData.getJSONObject(i);
                cell = new PdfPCell(new Paragraph(String.format("%02d", i + 1), IBMPlex));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPadding(10f);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph(product.getString("Product Name"), IBMPlex));
                cell.setPadding(10f);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph(String.format("%02d", product.getInt("Purchased Quantity")), IBMPlex));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPadding(10f);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph(com.ibm.icu.text.NumberFormat.getCurrencyInstance(Locale.of("en", "in")).format(product.getBigDecimal("Price")), IBMPlex));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPadding(10f);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph(String.format("%02d", product.getInt("GST Rate")) + "%", IBMPlex));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPadding(10f);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph(com.ibm.icu.text.NumberFormat.getCurrencyInstance(Locale.of("en", "in")).format(product.getBigDecimal("Net Amount")), IBMPlex));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPadding(10f);
                table.addCell(cell);
            }
            cell = new PdfPCell(new Paragraph("Total Amount", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setPadding(10f);
            cell.setColspan(5);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph(com.ibm.icu.text.NumberFormat.getCurrencyInstance(Locale.of("en", "in")).format(new BigDecimal(invoiceData.getString("Sale Amount"))), IBMPlex));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setPadding(10f);
            table.addCell(cell);
            para = new Paragraph();
            chunk = new Chunk("Total Amount in Words : ", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10));
            para.add(chunk);
            chunk = new Chunk("Rupees " + com.ibm.icu.lang.UCharacter.toTitleCase(new com.ibm.icu.text.RuleBasedNumberFormat
                (Locale.of("en", "in"), com.ibm.icu.text.RuleBasedNumberFormat.SPELLOUT).format(Double.parseDouble(invoiceData.getString("Sale Amount")), "%spellout-numbering"),
                com.ibm.icu.text.BreakIterator.getWordInstance()) + " Only", IBMPlex);
            para.add(chunk);
            cell = new PdfPCell(para);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setPadding(10f);
            cell.setColspan(6);
            table.addCell(cell);
            table.setSpacingBefore(40f);
            table.setWidthPercentage(100);
            document.add(table);
            table = new PdfPTable(2);
            cell = new PdfPCell(new Paragraph("THANK YOU FOR YOUR BUSINESS!", new Font(BaseFont.createFont("/fonts/IBMPlexSansDevanagari-Regular.ttf",
                BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 12, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("AUTHORIZED SIGNATURE", new Font(BaseFont.createFont("/fonts/IBMPlexSansDevanagari-Regular.ttf",
                BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 12)));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            table.setSpacingBefore(80f);
            table.setWidthPercentage(100);
            document.add(table);
            document.close();
        } catch (DocumentException | IOException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void ExportActionPerformed(String TableName) {
        ResultSet resultSet;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Export to CSV");
        fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooser.setSelectedFile(new File("Speckle-" + TableName + "-" + new java.text.SimpleDateFormat("dd-MM-yyyy").format(new java.util.Date()) + ".csv"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV File", "csv"));
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            if (TableName.equals("Inventory"))
                resultSet = com.astral.internal.SQLite.invenData();
            else
                resultSet = com.astral.internal.SQLite.invoData();
            try (FileWriter writer = new FileWriter(fileChooser.getSelectedFile())) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    writer.append("'" + metaData.getColumnName(i));
                    if (i < columnCount)
                        writer.append("',");
                }
                writer.append("'\r\n");
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        writer.append("'" + resultSet.getString(i));
                        if (i < columnCount)
                            writer.append("',");
                    }
                    writer.append("'\r\n");
                }
                JOptionPane.showMessageDialog(null, TableName + " Data Exported Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException | SQLException ex) {
                JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public static boolean InventoryImportActionPerformed() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Import from CSV");
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV File", "csv"));
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                String CSV_Line;
                java.io.BufferedReader CSV_Reader = new java.io.BufferedReader(new java.io.FileReader(fileChooser.getSelectedFile()));
                while ((CSV_Line = CSV_Reader.readLine()) != null) {
                    String[] CSV_Parts = CSV_Line.replace("\"", "").replace("'", "").split(",");
                    String PID = CSV_Parts[0];
                    String productName = CSV_Parts[1];
                    String price = CSV_Parts[2];
                    String gstRate = CSV_Parts[3];
                    String availableQuantity = CSV_Parts[4];
                    if (!PID.equals("Product ID"))
                        com.astral.internal.SQLite.updateInven(PID, productName, price, gstRate, availableQuantity);
                }
                JOptionPane.showMessageDialog(null, "Inventory Data Imported Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return false;
    }
}
