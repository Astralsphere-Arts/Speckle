package Speckle;

import java.awt.Component;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Astralsphere Arts
 */
public class Billing extends javax.swing.JPanel {

    /**
     * Creates new form Billing
     */
    public Billing() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Heading = new javax.swing.JLabel();
        New_Invoice = new javax.swing.JButton();
        Table_Container = new javax.swing.JScrollPane();
        Invoice = new javax.swing.JTable();
        Remove = new javax.swing.JButton();
        Export = new javax.swing.JButton();
        View = new javax.swing.JButton();

        Heading.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Heading.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Heading.setText("Invoice");
        Heading.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        New_Invoice.setFont(New_Invoice.getFont().deriveFont((float)14));
        New_Invoice.setText("New Invoice");
        New_Invoice.setToolTipText("Generate a New Invoice");

        Invoice.setAutoCreateRowSorter(true);
        Invoice.setModel(DbUtils.resultSetToTableModel(SQLite.Main.invoData()));
        Invoice.setShowGrid(true);
        Table_Container.setViewportView(Invoice);
        final TableColumnModel columnModel = Invoice.getColumnModel();
        for (int column = 0; column < Invoice.getColumnCount(); column++) {
            int width = 15;
            for (int row = 0; row < Invoice.getRowCount(); row++) {
                TableCellRenderer renderer = Invoice.getCellRenderer(row, column);
                Component comp = Invoice.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +1 , width);
            }
            if(width > 300) width=300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }

        Remove.setText("Remove");
        Remove.setToolTipText("Remove Selected Invoice from Invoice History");

        Export.setText("Export");
        Export.setToolTipText("Export Invoice History to a CSV File");

        View.setText("View Invoice");
        View.setToolTipText("View Selected Invoice from Invoice History");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(View, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Remove, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Export, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(Heading, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(New_Invoice, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Table_Container, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(New_Invoice, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Heading, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Table_Container, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Remove, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(View, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Export, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(70, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Export;
    private javax.swing.JLabel Heading;
    private javax.swing.JTable Invoice;
    private javax.swing.JButton New_Invoice;
    private javax.swing.JButton Remove;
    private javax.swing.JScrollPane Table_Container;
    private javax.swing.JButton View;
    // End of variables declaration//GEN-END:variables
}
