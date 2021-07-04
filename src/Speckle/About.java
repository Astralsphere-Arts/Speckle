package Speckle;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Astralsphere Arts
 */
public class About extends javax.swing.JPanel {

    /**
     * Creates new form About
     */
    public About() {
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

        Speckle_Logo = new javax.swing.JLabel();
        Heading = new javax.swing.JLabel();
        Version = new javax.swing.JLabel();
        About_Para = new javax.swing.JLabel();
        About_Container = new javax.swing.JTabbedPane();
        Authors_Tab = new javax.swing.JPanel();
        Authors_Container = new javax.swing.JScrollPane();
        Authors_Text = new javax.swing.JTextArea();
        License_Tab = new javax.swing.JPanel();
        License_Container = new javax.swing.JScrollPane();
        License_Text = new javax.swing.JTextArea();
        Credits_Tab = new javax.swing.JPanel();
        Credits_Container = new javax.swing.JScrollPane();
        Credits_Text = new javax.swing.JTextArea();

        Speckle_Logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Speckle_Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/speckle-logo.png"))); // NOI18N
        Speckle_Logo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Heading.setFont(new java.awt.Font("Segoe UI Semibold", 0, 30)); // NOI18N
        Heading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Heading.setText("About Speckle");
        Heading.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Version.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        Version.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Version.setText("Version 0.7-beta");
        Version.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        About_Para.setFont(new java.awt.Font("Segoe UI Semibold", 0, 11)); // NOI18N
        About_Para.setText("Speckle is Software Used For Invoicing(Billing) and Inventory Management");

        Authors_Tab.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 180, 180)));

        Authors_Container.setBorder(null);

        Authors_Text.setEditable(false);
        Authors_Text.setBackground(new java.awt.Color(247, 247, 247));
        Authors_Text.setColumns(20);
        Authors_Text.setRows(5);
        Authors_Caret = (javax.swing.text.DefaultCaret)License_Text.getCaret();
        Authors_Caret.setUpdatePolicy(javax.swing.text.DefaultCaret.NEVER_UPDATE);
        try {
            Authors_Reader = new java.io.BufferedReader(new java.io.InputStreamReader(getClass().getResourceAsStream("/Resources/authors.txt")));
            Authors_Line = Authors_Reader.readLine();
            while (Authors_Line != null) {
                Authors_Text.append(Authors_Line + "\n");
                Authors_Line = Authors_Reader.readLine();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
        Authors_Container.setViewportView(Authors_Text);

        javax.swing.GroupLayout Authors_TabLayout = new javax.swing.GroupLayout(Authors_Tab);
        Authors_Tab.setLayout(Authors_TabLayout);
        Authors_TabLayout.setHorizontalGroup(
            Authors_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Authors_Container, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
        );
        Authors_TabLayout.setVerticalGroup(
            Authors_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Authors_Container, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );

        About_Container.addTab("Authors", Authors_Tab);

        License_Tab.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 180, 180)));

        License_Container.setBorder(null);

        License_Text.setEditable(false);
        License_Text.setBackground(new java.awt.Color(247, 247, 247));
        License_Text.setColumns(20);
        License_Text.setRows(5);
        License_Caret = (javax.swing.text.DefaultCaret)License_Text.getCaret();
        License_Caret.setUpdatePolicy(javax.swing.text.DefaultCaret.NEVER_UPDATE);
        try {
            License_Reader = new java.io.BufferedReader(new java.io.InputStreamReader(getClass().getResourceAsStream("/Resources/license.txt")));
            License_Line = License_Reader.readLine();
            while (License_Line != null) {
                License_Text.append(License_Line + "\n");
                License_Line = License_Reader.readLine();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
        License_Container.setViewportView(License_Text);

        javax.swing.GroupLayout License_TabLayout = new javax.swing.GroupLayout(License_Tab);
        License_Tab.setLayout(License_TabLayout);
        License_TabLayout.setHorizontalGroup(
            License_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(License_Container, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
        );
        License_TabLayout.setVerticalGroup(
            License_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(License_Container, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );

        About_Container.addTab("License", License_Tab);

        Credits_Tab.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 180, 180)));

        Credits_Container.setBorder(null);

        Credits_Text.setEditable(false);
        Credits_Text.setBackground(new java.awt.Color(247, 247, 247));
        Credits_Text.setColumns(20);
        Credits_Text.setRows(5);
        Credits_Caret = (javax.swing.text.DefaultCaret)License_Text.getCaret();
        Credits_Caret.setUpdatePolicy(javax.swing.text.DefaultCaret.NEVER_UPDATE);
        try {
            Credits_Reader = new java.io.BufferedReader(new java.io.InputStreamReader(getClass().getResourceAsStream("/Resources/credits.txt")));
            Credits_Line = Credits_Reader.readLine();
            while (Credits_Line != null) {
                Credits_Text.append(Credits_Line + "\n");
                Credits_Line = Credits_Reader.readLine();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
        Credits_Container.setViewportView(Credits_Text);

        javax.swing.GroupLayout Credits_TabLayout = new javax.swing.GroupLayout(Credits_Tab);
        Credits_Tab.setLayout(Credits_TabLayout);
        Credits_TabLayout.setHorizontalGroup(
            Credits_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Credits_Container, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
        );
        Credits_TabLayout.setVerticalGroup(
            Credits_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Credits_Container, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );

        About_Container.addTab("Credits", Credits_Tab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(Speckle_Logo, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Version, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Heading, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(About_Para, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(About_Container, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Heading)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Version)
                        .addGap(18, 18, 18)
                        .addComponent(About_Para)
                        .addGap(26, 26, 26)
                        .addComponent(About_Container, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Speckle_Logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane About_Container;
    private javax.swing.JLabel About_Para;
    private javax.swing.JScrollPane Authors_Container;
    private javax.swing.JPanel Authors_Tab;
    private javax.swing.JTextArea Authors_Text;
    private javax.swing.JScrollPane Credits_Container;
    private javax.swing.JPanel Credits_Tab;
    private javax.swing.JTextArea Credits_Text;
    private javax.swing.JLabel Heading;
    private javax.swing.JScrollPane License_Container;
    private javax.swing.JPanel License_Tab;
    private javax.swing.JTextArea License_Text;
    private javax.swing.JLabel Speckle_Logo;
    private javax.swing.JLabel Version;
    // End of variables declaration//GEN-END:variables
    private javax.swing.text.DefaultCaret Authors_Caret;
    private javax.swing.text.DefaultCaret Credits_Caret;
    private javax.swing.text.DefaultCaret License_Caret;
    private java.io.BufferedReader Authors_Reader;
    private java.io.BufferedReader Credits_Reader;
    private java.io.BufferedReader License_Reader;
    private String Authors_Line;
    private String Credits_Line;
    private String License_Line;
}
