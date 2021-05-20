package Speckle;

/**
 *
 * @author Astralsphere Arts
 */
public class Settings extends javax.swing.JPanel {

    /**
     * Creates new form Settings
     */
    public Settings() {
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

        Settings_Container = new javax.swing.JTabbedPane();
        User_Tab = new javax.swing.JPanel();
        User_Heading = new javax.swing.JLabel();
        User_SubHeading = new javax.swing.JLabel();
        Old_Pass_Label = new javax.swing.JLabel();
        Old_Pass = new javax.swing.JPasswordField();
        New_Pass_Label = new javax.swing.JLabel();
        New_Pass = new javax.swing.JPasswordField();
        Reset = new javax.swing.JButton();
        Details_Tab = new javax.swing.JPanel();
        Details_Heading = new javax.swing.JLabel();
        Details_SubHeading = new javax.swing.JLabel();
        Comp_Name_Label = new javax.swing.JLabel();
        Comp_Name = new javax.swing.JTextField();
        Comp_Contact_Label = new javax.swing.JLabel();
        Comp_Contact = new javax.swing.JTextField();
        Save = new javax.swing.JButton();

        Settings_Container.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        Settings_Container.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        User_Heading.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        User_Heading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        User_Heading.setText("Settings");
        User_Heading.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        User_SubHeading.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        User_SubHeading.setText("Recover Credentials");

        Old_Pass_Label.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        Old_Pass_Label.setText("Old Password");

        Old_Pass.setPreferredSize(new java.awt.Dimension(64, 28));

        New_Pass_Label.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        New_Pass_Label.setText("New Password");

        New_Pass.setPreferredSize(new java.awt.Dimension(64, 28));
        New_Pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                New_PassActionPerformed(evt);
            }
        });

        Reset.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        Reset.setText("Reset");

        javax.swing.GroupLayout User_TabLayout = new javax.swing.GroupLayout(User_Tab);
        User_Tab.setLayout(User_TabLayout);
        User_TabLayout.setHorizontalGroup(
            User_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(User_TabLayout.createSequentialGroup()
                .addGroup(User_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(User_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(User_TabLayout.createSequentialGroup()
                            .addGap(251, 251, 251)
                            .addGroup(User_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(User_TabLayout.createSequentialGroup()
                                    .addComponent(Old_Pass_Label)
                                    .addGap(18, 18, 18)
                                    .addComponent(Old_Pass, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(User_SubHeading)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, User_TabLayout.createSequentialGroup()
                            .addGap(248, 248, 248)
                            .addComponent(New_Pass_Label)
                            .addGap(10, 10, 10)
                            .addComponent(New_Pass, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(User_TabLayout.createSequentialGroup()
                        .addGap(395, 395, 395)
                        .addComponent(Reset))
                    .addGroup(User_TabLayout.createSequentialGroup()
                        .addGap(353, 353, 353)
                        .addComponent(User_Heading, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(375, Short.MAX_VALUE))
        );
        User_TabLayout.setVerticalGroup(
            User_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(User_TabLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(User_Heading, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(User_SubHeading)
                .addGap(99, 99, 99)
                .addGroup(User_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Old_Pass_Label)
                    .addComponent(Old_Pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(User_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(New_Pass_Label)
                    .addComponent(New_Pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(Reset)
                .addGap(0, 171, Short.MAX_VALUE))
        );

        Settings_Container.addTab("Login", User_Tab);

        Details_Heading.setFont(new java.awt.Font("Segoe UI Semibold", 0, 36)); // NOI18N
        Details_Heading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Details_Heading.setText("Settings");
        Details_Heading.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Details_SubHeading.setFont(new java.awt.Font("Segoe UI Semibold", 0, 20)); // NOI18N
        Details_SubHeading.setText("Change Credentials");

        Comp_Name_Label.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        Comp_Name_Label.setText("Company name");

        Comp_Name.setPreferredSize(new java.awt.Dimension(64, 28));

        Comp_Contact_Label.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        Comp_Contact_Label.setText("Contact Number");

        Comp_Contact.setPreferredSize(new java.awt.Dimension(64, 28));

        Save.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        Save.setText("Save Changes");

        javax.swing.GroupLayout Details_TabLayout = new javax.swing.GroupLayout(Details_Tab);
        Details_Tab.setLayout(Details_TabLayout);
        Details_TabLayout.setHorizontalGroup(
            Details_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Details_TabLayout.createSequentialGroup()
                .addGroup(Details_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Details_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(Details_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Details_TabLayout.createSequentialGroup()
                                .addGap(360, 360, 360)
                                .addComponent(Details_Heading, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Details_TabLayout.createSequentialGroup()
                                .addGap(351, 351, 351)
                                .addComponent(Details_SubHeading)))
                        .addGroup(Details_TabLayout.createSequentialGroup()
                            .addGap(198, 198, 198)
                            .addGroup(Details_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Comp_Name_Label)
                                .addComponent(Comp_Contact_Label))
                            .addGap(23, 23, 23)
                            .addGroup(Details_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Comp_Name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Comp_Contact, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))))
                    .addGroup(Details_TabLayout.createSequentialGroup()
                        .addGap(365, 365, 365)
                        .addComponent(Save)))
                .addContainerGap(368, Short.MAX_VALUE))
        );
        Details_TabLayout.setVerticalGroup(
            Details_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Details_TabLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(Details_Heading, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(Details_SubHeading)
                .addGap(65, 65, 65)
                .addGroup(Details_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Comp_Name_Label)
                    .addComponent(Comp_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(Details_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Comp_Contact_Label)
                    .addComponent(Comp_Contact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(Save)
                .addContainerGap(184, Short.MAX_VALUE))
        );

        Settings_Container.addTab("Details", Details_Tab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Settings_Container, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Settings_Container, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void New_PassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_New_PassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_New_PassActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Comp_Contact;
    private javax.swing.JLabel Comp_Contact_Label;
    private javax.swing.JTextField Comp_Name;
    private javax.swing.JLabel Comp_Name_Label;
    private javax.swing.JLabel Details_Heading;
    private javax.swing.JLabel Details_SubHeading;
    private javax.swing.JPanel Details_Tab;
    private javax.swing.JPasswordField New_Pass;
    private javax.swing.JLabel New_Pass_Label;
    private javax.swing.JPasswordField Old_Pass;
    private javax.swing.JLabel Old_Pass_Label;
    private javax.swing.JButton Reset;
    private javax.swing.JButton Save;
    private javax.swing.JTabbedPane Settings_Container;
    private javax.swing.JLabel User_Heading;
    private javax.swing.JLabel User_SubHeading;
    private javax.swing.JPanel User_Tab;
    // End of variables declaration//GEN-END:variables
}
