package Speckle;

import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Astralsphere Arts
 */
public class Invoicing extends javax.swing.JPanel {

    /**
     * Creates new form Invoicing
     */
    public Invoicing() {
        initComponents();
        Invoice_Deck = (java.awt.CardLayout)Invoice_Container.getLayout();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Invoice_Container = new javax.swing.JPanel();
        Invoice_Main = new javax.swing.JPanel();
        Main_Heading = new javax.swing.JLabel();
        New_Invoice = new javax.swing.JButton();
        Invoice_Table_Container = new javax.swing.JScrollPane();
        Invoice_Table = new javax.swing.JTable();
        View = new javax.swing.JButton();
        Remove = new javax.swing.JButton();
        Export = new javax.swing.JButton();
        Invoice_New = new javax.swing.JPanel();
        New_Heading = new javax.swing.JLabel();
        Customer_Name_Label = new javax.swing.JLabel();
        Customer_Name = new javax.swing.JTextField();
        Contact_Number_Label = new javax.swing.JLabel();
        Contact_Number = new javax.swing.JTextField();
        Customer_Address_Label = new javax.swing.JLabel();
        Customer_Address = new javax.swing.JTextField();
        New_Invoice_Table_Container = new javax.swing.JScrollPane();
        New_Invoice_Table = new javax.swing.JTable();
        Cancel = new javax.swing.JButton();
        Create_Invoice = new javax.swing.JButton();

        Invoice_Container.setLayout(new java.awt.CardLayout());

        Main_Heading.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Main_Heading.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Main_Heading.setText("Invoice");
        Main_Heading.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        New_Invoice.setFont(New_Invoice.getFont().deriveFont((float)14));
        New_Invoice.setText("New Invoice");
        New_Invoice.setToolTipText("Generate a New Invoice");
        New_Invoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                New_InvoiceActionPerformed(evt);
            }
        });

        Invoice_Table.setModel(Internal.SQLite.invoData());
        Invoice_Table.setShowGrid(true);
        Invoice_Table.getTableHeader().setReorderingAllowed(false);
        Invoice_Table_Container.setViewportView(Invoice_Table);
        final TableColumnModel columnModel = Invoice_Table.getColumnModel();
        for (int column = 0; column < Invoice_Table.getColumnCount(); column++) {
            int width = 15;
            for (int row = 0; row < Invoice_Table.getRowCount(); row++) {
                TableCellRenderer renderer = Invoice_Table.getCellRenderer(row, column);
                Component comp = Invoice_Table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1 , width);
            }
            if (width > 300) width=300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }

        View.setText("View Invoice");
        View.setToolTipText("View Selected Invoice from Invoice History");

        Remove.setText("Remove");
        Remove.setToolTipText("Remove Selected Invoices from Invoice History");
        Remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveActionPerformed(evt);
            }
        });

        Export.setText("Export");
        Export.setToolTipText("Export Invoice History to a CSV File");

        javax.swing.GroupLayout Invoice_MainLayout = new javax.swing.GroupLayout(Invoice_Main);
        Invoice_Main.setLayout(Invoice_MainLayout);
        Invoice_MainLayout.setHorizontalGroup(
            Invoice_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Invoice_MainLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(Invoice_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(Invoice_MainLayout.createSequentialGroup()
                        .addComponent(View, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Remove, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Export, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Invoice_MainLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(Main_Heading, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(New_Invoice, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Invoice_Table_Container, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        Invoice_MainLayout.setVerticalGroup(
            Invoice_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Invoice_MainLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(Invoice_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(New_Invoice, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Main_Heading, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Invoice_Table_Container, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(Invoice_MainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Remove, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(View, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Export, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        Invoice_Container.add(Invoice_Main, "invMain");

        New_Heading.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        New_Heading.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        New_Heading.setText("New Invoice");
        New_Heading.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        Customer_Name_Label.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        Customer_Name_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Customer_Name_Label.setText("Customer's Name");
        Customer_Name_Label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Customer_Name.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        Contact_Number_Label.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        Contact_Number_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Contact_Number_Label.setText("Contact Number");
        Contact_Number_Label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Contact_Number.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        Customer_Address_Label.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        Customer_Address_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Customer_Address_Label.setText("Customer's Address");
        Customer_Address_Label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Customer_Address.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        New_Invoice_Table.setModel(Internal.SQLite.newInvoData());
        New_Invoice_Table.setShowGrid(true);
        New_Invoice_Table.getTableHeader().setReorderingAllowed(false);
        New_Invoice_Table_Container.setViewportView(New_Invoice_Table);
        final TableColumnModel NewColumnModel = New_Invoice_Table.getColumnModel();
        for (int column = 0; column < New_Invoice_Table.getColumnCount(); column++) {
            int width = 15;
            for (int row = 0; row < New_Invoice_Table.getRowCount(); row++) {
                TableCellRenderer renderer = New_Invoice_Table.getCellRenderer(row, column);
                Component comp = New_Invoice_Table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1 , width);
            }
            if (width > 300) width=300;
            NewColumnModel.getColumn(column).setPreferredWidth(width);
        }

        Cancel.setText("Cancel");
        Cancel.setToolTipText("Return to Invoice History");
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });

        Create_Invoice.setText("Create Invoice");
        Create_Invoice.setToolTipText("Create a New Invoice using given Data");
        Create_Invoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Create_InvoiceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Invoice_NewLayout = new javax.swing.GroupLayout(Invoice_New);
        Invoice_New.setLayout(Invoice_NewLayout);
        Invoice_NewLayout.setHorizontalGroup(
            Invoice_NewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Invoice_NewLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(Invoice_NewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(Invoice_NewLayout.createSequentialGroup()
                        .addComponent(Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Create_Invoice, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(New_Invoice_Table_Container, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Invoice_NewLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(Invoice_NewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Invoice_NewLayout.createSequentialGroup()
                                .addComponent(New_Heading, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(Invoice_NewLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(Invoice_NewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Customer_Name_Label)
                                    .addComponent(Customer_Address_Label))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(Invoice_NewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Invoice_NewLayout.createSequentialGroup()
                                        .addComponent(Customer_Name)
                                        .addGap(18, 18, 18)
                                        .addComponent(Contact_Number_Label)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(Contact_Number, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(Customer_Address))))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        Invoice_NewLayout.setVerticalGroup(
            Invoice_NewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Invoice_NewLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(New_Heading, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Invoice_NewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Customer_Name_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Customer_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Contact_Number_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Contact_Number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Invoice_NewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Customer_Address_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Customer_Address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(New_Invoice_Table_Container, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(Invoice_NewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Create_Invoice, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70))
        );

        Invoice_Container.add(Invoice_New, "invNew");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Invoice_Container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Invoice_Container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void New_InvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_New_InvoiceActionPerformed
        Invoice_Deck.show(Invoice_Container, "invNew");
    }//GEN-LAST:event_New_InvoiceActionPerformed

    private void RemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveActionPerformed
        DefaultTableModel Invoice_Model = (DefaultTableModel) this.Invoice_Table.getModel();
        int[] rows = Invoice_Table.getSelectedRows();
        for (int i=0; i<rows.length; i++) {
            String invID = Invoice_Table.getValueAt(rows[i]-i, 0).toString();
            Internal.SQLite.remInvoice(invID);
            Invoice_Model.removeRow(rows[i]-i);
        }
    }//GEN-LAST:event_RemoveActionPerformed

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        Invoice_Deck.show(Invoice_Container, "invMain");
    }//GEN-LAST:event_CancelActionPerformed

    private void Create_InvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Create_InvoiceActionPerformed
        int prodSelected = 0;
        boolean emptyQuan = false;
        boolean excessQuan = false;
        String invID = "INV-" + Internal.Random.ID(4) + "-" + Internal.Random.ID(4);
        String custName = Customer_Name.getText();
        String custContact = Contact_Number.getText();
        String custAddress = Customer_Address.getText();
        String saleDate = new java.text.SimpleDateFormat("dd MMMM yyyy").format(new java.util.Date());
        String saleAmount = "0";
        for (int row = 0; row < New_Invoice_Table.getRowCount(); row++) {
            Boolean isChecked = Boolean.valueOf(New_Invoice_Table.getValueAt(row, 0).toString());
            if (isChecked) {
                prodSelected++;
                String availQuan = Integer.toString((Integer) New_Invoice_Table.getValueAt(row, 4));
                String purchQuan = "";
                if (New_Invoice_Table.getValueAt(row, 5) != null)
                    purchQuan = Integer.toString((Integer) New_Invoice_Table.getValueAt(row, 5));
                if (purchQuan.equals("")) {
                    emptyQuan = true;
                    purchQuan = "0";
                }
                int remaingQuan = Integer.parseInt(availQuan) - Integer.parseInt(purchQuan);
                if (remaingQuan < 0)
                    excessQuan = true;
            }
        }
        if (custName.equals("") || custContact.equals("") || custAddress.equals(""))
            JOptionPane.showMessageDialog(null, "Customer Details can't be Empty. Please"
                + " Try Again!", "Customer Details Empty", JOptionPane.ERROR_MESSAGE);
        else if (prodSelected == 0)
            JOptionPane.showMessageDialog(null, "At least one Product needs to be Selected. Please"
                + " Try Again!", "No Products Selected", JOptionPane.ERROR_MESSAGE);
        else if (emptyQuan)
            JOptionPane.showMessageDialog(null, "Purchased Quantity for one or more Products is Empty."
                + " Please Try Again!", "Purchased Quantity Empty", JOptionPane.ERROR_MESSAGE);
        else if (excessQuan)
            JOptionPane.showMessageDialog(null, "Purchased Quantity exceeds the Available Quantity"
                + " for one or more Products. Please Try Again!", "Purchased Quantity Exceeded",
                JOptionPane.ERROR_MESSAGE);
        else {
            Internal.SQLite.newInvoice(invID);
            for (int row = 0; row < New_Invoice_Table.getRowCount(); row++) {
                Boolean isChecked = Boolean.valueOf(New_Invoice_Table.getValueAt(row, 0).toString());
                if (isChecked) {
                    String prodID = (String) New_Invoice_Table.getValueAt(row, 1);
                    String prodName = (String) New_Invoice_Table.getValueAt(row, 2);
                    String Price = Double.toString((Double) New_Invoice_Table.getValueAt(row, 3));
                    int availQuan = (Integer) New_Invoice_Table.getValueAt(row, 4);
                    String purchQuan = Integer.toString((Integer) New_Invoice_Table.getValueAt(row, 5));
                    String remaingQuan = Integer.toString(availQuan - Integer.parseInt(purchQuan));
                    String netAmount = Double.toString(Double.parseDouble(Price) *
                        Double.parseDouble(purchQuan));
                    saleAmount = Double.toString(Double.parseDouble(saleAmount) +
                        Double.parseDouble(netAmount));
                    Internal.SQLite.updateStock(prodID, remaingQuan);
                    Internal.SQLite.newInvoice(invID, prodName, Price, purchQuan, netAmount);
                }
            }
            Internal.SQLite.newInvoice(invID, custName, custContact, custAddress, saleDate, saleAmount);
            Speckle.Main.Content.removeAll();
            Speckle.Invoicing scene = new Invoicing();
            scene.setBounds(0, 0, 955, 574);
            Speckle.Main.Content.add(scene).setVisible(true);
        }
    }//GEN-LAST:event_Create_InvoiceActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancel;
    private javax.swing.JTextField Contact_Number;
    private javax.swing.JLabel Contact_Number_Label;
    private javax.swing.JButton Create_Invoice;
    private javax.swing.JTextField Customer_Address;
    private javax.swing.JLabel Customer_Address_Label;
    private javax.swing.JTextField Customer_Name;
    private javax.swing.JLabel Customer_Name_Label;
    private javax.swing.JButton Export;
    private javax.swing.JPanel Invoice_Container;
    private javax.swing.JPanel Invoice_Main;
    private javax.swing.JPanel Invoice_New;
    private javax.swing.JTable Invoice_Table;
    private javax.swing.JScrollPane Invoice_Table_Container;
    private javax.swing.JLabel Main_Heading;
    private javax.swing.JLabel New_Heading;
    private javax.swing.JButton New_Invoice;
    private javax.swing.JTable New_Invoice_Table;
    private javax.swing.JScrollPane New_Invoice_Table_Container;
    private javax.swing.JButton Remove;
    private javax.swing.JButton View;
    // End of variables declaration//GEN-END:variables
    private final java.awt.CardLayout Invoice_Deck;
}
