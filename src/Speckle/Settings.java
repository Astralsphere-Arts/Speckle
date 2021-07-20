package Speckle;

import static Speckle.Main.Content;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

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
        Speckle_Logo = new javax.swing.JLabel();
        Change_Username = new javax.swing.JLabel();
        Current_Username_Label = new javax.swing.JLabel();
        Current_Username = new javax.swing.JTextField();
        New_Username_Label = new javax.swing.JLabel();
        New_Username = new javax.swing.JTextField();
        Change_Username_Button = new javax.swing.JButton();
        Change_Password = new javax.swing.JLabel();
        Current_Password_Label = new javax.swing.JLabel();
        Current_Password = new javax.swing.JPasswordField();
        New_Password_Label = new javax.swing.JLabel();
        New_Password = new javax.swing.JPasswordField();
        Confirm_Password_Label = new javax.swing.JLabel();
        Confirm_Password = new javax.swing.JPasswordField();
        Change_Password_Button = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Reset = new javax.swing.JButton();
        Details_Tab = new javax.swing.JPanel();
        Update_Business_Details = new javax.swing.JLabel();
        Current_Value_Label = new javax.swing.JLabel();
        New_Value_Label = new javax.swing.JLabel();
        Business_Name_Label = new javax.swing.JLabel();
        Current_Business_Name = new javax.swing.JTextField();
        New_Business_Name = new javax.swing.JTextField();
        Contact_Number_Label = new javax.swing.JLabel();
        Current_Contact_Number = new javax.swing.JTextField();
        New_Contact_Number = new javax.swing.JTextField();
        Email_Address_Label = new javax.swing.JLabel();
        Current_Email_Address = new javax.swing.JTextField();
        New_Email_Address = new javax.swing.JTextField();
        Business_Location_Label = new javax.swing.JLabel();
        Current_Business_Location_Label = new javax.swing.JLabel();
        Current_Business_Location = new javax.swing.JTextField();
        New_Business_Location_Label = new javax.swing.JLabel();
        New_Business_Location = new javax.swing.JTextField();
        Save_Changes_Button = new javax.swing.JButton();

        Settings_Container.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        Settings_Container.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        User_Tab.setPreferredSize(new java.awt.Dimension(948, 574));

        Speckle_Logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Speckle_Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/speckle-logo.png"))); // NOI18N
        Speckle_Logo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Change_Username.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        Change_Username.setText("Change Username");

        Current_Username_Label.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        Current_Username_Label.setText("Current Username");

        Current_Username.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        Current_Username.setText(Internal.Security.getDecodedString(Internal.SQLite.getConfigValue("Username")));
        Current_Username.setEnabled(false);

        New_Username_Label.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        New_Username_Label.setText("New Username");

        New_Username.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        Change_Username_Button.setText("Change Username");
        Change_Username_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Change_Username_ButtonActionPerformed(evt);
            }
        });

        Change_Password.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        Change_Password.setText("Change Password");

        Current_Password_Label.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        Current_Password_Label.setText("Current Password");

        Current_Password.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        New_Password_Label.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        New_Password_Label.setText("New Password");

        New_Password.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        Confirm_Password_Label.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        Confirm_Password_Label.setText("Confirm Password");

        Confirm_Password.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        Change_Password_Button.setText("Change Password");
        Change_Password_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Change_Password_ButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel1.setText("Reset Recovery Code");

        Reset.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        Reset.setText("Reset");
        Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout User_TabLayout = new javax.swing.GroupLayout(User_Tab);
        User_Tab.setLayout(User_TabLayout);
        User_TabLayout.setHorizontalGroup(
            User_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(User_TabLayout.createSequentialGroup()
                .addGroup(User_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(User_TabLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(Change_Username))
                    .addGroup(User_TabLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(Current_Username_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(Current_Username, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(User_TabLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(New_Password_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(New_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(User_TabLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(New_Username_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(User_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Change_Username_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(New_Username, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(User_TabLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(Confirm_Password_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(User_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Change_Password_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Confirm_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(User_TabLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(User_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(User_TabLayout.createSequentialGroup()
                                .addGroup(User_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Change_Password)
                                    .addComponent(Current_Password_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addComponent(Current_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(User_TabLayout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                .addComponent(Speckle_Logo, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );
        User_TabLayout.setVerticalGroup(
            User_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(User_TabLayout.createSequentialGroup()
                .addGroup(User_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(User_TabLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(Change_Username)
                        .addGap(18, 18, 18)
                        .addGroup(User_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(User_TabLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(Current_Username_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Current_Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(User_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(User_TabLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(New_Username_Label))
                            .addComponent(New_Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addComponent(Change_Username_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(Change_Password)
                        .addGap(28, 28, 28)
                        .addGroup(User_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Current_Password_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Current_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(User_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(New_Password_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(New_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(User_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Confirm_Password_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Confirm_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(Change_Password_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(14, 14, 14)
                        .addComponent(Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(User_TabLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(Speckle_Logo, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );

        Settings_Container.addTab("User Details", User_Tab);

        Update_Business_Details.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        Update_Business_Details.setText("Update Business Details");

        Current_Value_Label.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        Current_Value_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Current_Value_Label.setText("Current");
        Current_Value_Label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        New_Value_Label.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        New_Value_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        New_Value_Label.setText("New");
        New_Value_Label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Business_Name_Label.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        Business_Name_Label.setText("Business Name");

        Current_Business_Name.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        Current_Business_Name.setText(Internal.SQLite.getConfigValue("Business Name"));
        Current_Business_Name.setEnabled(false);

        New_Business_Name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        Contact_Number_Label.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        Contact_Number_Label.setText("Contact Number");

        Current_Contact_Number.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        Current_Contact_Number.setText(Internal.SQLite.getConfigValue("Contact Number"));
        Current_Contact_Number.setEnabled(false);

        New_Contact_Number.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        New_Contact_Number.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                New_Contact_NumberActionPerformed(evt);
            }
        });
        New_Contact_Number.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                New_Contact_NumberKeyPressed(evt);
            }
        });

        Email_Address_Label.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        Email_Address_Label.setText("Email Address");

        Current_Email_Address.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        Current_Email_Address.setText(Internal.SQLite.getConfigValue("Email Address"));
        Current_Email_Address.setEnabled(false);

        New_Email_Address.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        Business_Location_Label.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        Business_Location_Label.setText("<html><p>Business Location (Address) :</p></html>");

        Current_Business_Location_Label.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        Current_Business_Location_Label.setText("Current");

        Current_Business_Location.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        Current_Business_Location.setText(Internal.SQLite.getConfigValue("Business Location"));
        Current_Business_Location.setEnabled(false);

        New_Business_Location_Label.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        New_Business_Location_Label.setText("New");

        New_Business_Location.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        Save_Changes_Button.setText("Save Changes");
        Save_Changes_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Save_Changes_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Details_TabLayout = new javax.swing.GroupLayout(Details_Tab);
        Details_Tab.setLayout(Details_TabLayout);
        Details_TabLayout.setHorizontalGroup(
            Details_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Details_TabLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(Update_Business_Details)
                .addGap(531, 682, Short.MAX_VALUE))
            .addGroup(Details_TabLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(Details_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Business_Location_Label)
                    .addGroup(Details_TabLayout.createSequentialGroup()
                        .addGroup(Details_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Details_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Email_Address_Label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Contact_Number_Label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addComponent(Business_Name_Label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(Current_Business_Location_Label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(New_Business_Location_Label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Details_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(Details_TabLayout.createSequentialGroup()
                                .addComponent(Current_Value_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(New_Value_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Current_Business_Location)
                            .addGroup(Details_TabLayout.createSequentialGroup()
                                .addGroup(Details_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(Current_Email_Address, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Current_Contact_Number, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Current_Business_Name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(Details_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(New_Business_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(New_Contact_Number)
                                    .addComponent(New_Email_Address)))
                            .addComponent(New_Business_Location))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Details_TabLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Save_Changes_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Details_TabLayout.setVerticalGroup(
            Details_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Details_TabLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(Update_Business_Details)
                .addGap(26, 26, 26)
                .addGroup(Details_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(New_Value_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Current_Value_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Details_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Business_Name_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Current_Business_Name)
                    .addComponent(New_Business_Name))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Details_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Contact_Number_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Current_Contact_Number)
                    .addComponent(New_Contact_Number))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Details_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Email_Address_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Current_Email_Address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(New_Email_Address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Business_Location_Label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(Details_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Current_Business_Location, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Current_Business_Location_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Details_TabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(New_Business_Location, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(New_Business_Location_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(Save_Changes_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(189, Short.MAX_VALUE))
        );

        Settings_Container.addTab("Business Details", Details_Tab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Settings_Container, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1019, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Settings_Container)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void Save_Changes_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save_Changes_ButtonActionPerformed
        String cname = New_Business_Name.getText();
        String cnum = New_Contact_Number.getText();
        String cmail = New_Email_Address.getText();
        String caddress = New_Business_Location.getText();
        if (cname.equals("") && cnum.equals("") && cmail.equals("") && caddress.equals(""))
            JOptionPane.showMessageDialog(null, "You haven't made any Changes. Please"
                + " Try Again!", "No Changes Found", JOptionPane.ERROR_MESSAGE);
        else {
            if (cname.equals(""))
                cname = Current_Business_Name.getText();
            if (cnum.equals(""))
                cnum = Current_Contact_Number.getText();
            if (cmail.equals(""))
                cmail = Current_Email_Address.getText();
            if (caddress.equals(""))
                caddress = Current_Business_Location.getText();
            
            if(cnum.length() < 10){
            JOptionPane.showMessageDialog(null, "You Have to Enter 10 Digit Phone"
            + "Number", "Please Try Again!", JOptionPane.ERROR_MESSAGE);
            }
         else{
         Internal.SQLite.compConfig(cname, cnum, cmail, caddress);
            JOptionPane.showMessageDialog(null, "Your Changes Have Been Saved Sucessfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
            Speckle.Main.Content.removeAll();
            Settings scene = new Settings();
            scene.setBounds(0, 0, 948, 574);
            Speckle.Main.Content.add(scene).setVisible(true);
            scene.Settings_Container.setSelectedIndex(1);
        }
      }
    }//GEN-LAST:event_Save_Changes_ButtonActionPerformed

    private void New_Contact_NumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_New_Contact_NumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_New_Contact_NumberActionPerformed

    private void New_Contact_NumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_New_Contact_NumberKeyPressed
        
        String phonenumber = New_Contact_Number.getText();
        int length = phonenumber.length();
        char c = evt.getKeyChar();
          if(evt.getKeyChar()>='0' && evt.getKeyChar()<='9'){
            
        if(length < 10){
            New_Contact_Number.setEditable(true);
        }
        else {
            New_Contact_Number.setEditable(false);
        }
       }
        else{
            if(evt.getExtendedKeyCode()==KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode()==KeyEvent.VK_DELETE){
                New_Contact_Number.setEditable(true);
            }
            else
                New_Contact_Number.setEditable(false);
        }
    }//GEN-LAST:event_New_Contact_NumberKeyPressed

    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetActionPerformed
       Content.removeAll();
       RecoveryKey sc = new RecoveryKey();
       sc.setVisible(true);
    }//GEN-LAST:event_ResetActionPerformed

    private void Change_Password_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Change_Password_ButtonActionPerformed
        String curpasswd = new String(Current_Password.getPassword());
        String passwd = new String(New_Password.getPassword());
        String confpasswd = new String(Confirm_Password.getPassword());
        if (curpasswd.equals("") || passwd.equals(""))
        JOptionPane.showMessageDialog(null, "Both Current and New Passwords are needed"
            + " for changing Password. Please Try Again!", "Password Fields Empty",
            JOptionPane.ERROR_MESSAGE);
        else if (!Internal.Security.validateHash(curpasswd, Internal.SQLite.getConfigValue("Password")))
        JOptionPane.showMessageDialog(null, "The Current Password is Incorrect. Please"
            + " Try Again!", "Incorrect Password", JOptionPane.ERROR_MESSAGE);
        else if (!passwd.equals(confpasswd))
        JOptionPane.showMessageDialog(null, "The Password in Confirm Password Field does not Match."
            + " Please Try Again!", "Password Mismatch", JOptionPane.ERROR_MESSAGE);
        else if (!passwd.equals(confpasswd))
        JOptionPane.showMessageDialog(null, "The Passwords in Password Fields do not Match."
            + " Please Try Again!", "Password Mismatch", JOptionPane.ERROR_MESSAGE);
        else if(passwd.length() < 7){
            JOptionPane.showMessageDialog(null, "Your Password Must Contain"
                +" at Least 7 Characters"," Please Try Again!", JOptionPane.ERROR_MESSAGE);
        }
        else if(!Internal.Security.checkPass(passwd)){
            JOptionPane.showMessageDialog(null, "Your Password Must Contain at Least One Numeric "
                + "One Uppercase and One Lowercase Character", " Please Try Again!", JOptionPane.ERROR_MESSAGE);
        }
        else {
            Internal.SQLite.setConfigValue("Password", Internal.Security.generateHash(passwd));
            JOptionPane.showMessageDialog(null, "Your Password changed Sucessfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
            Speckle.Main.Content.removeAll();
            Settings scene = new Settings();
            scene.setBounds(0, 0, 948, 574);
            Speckle.Main.Content.add(scene).setVisible(true);
        }
    }//GEN-LAST:event_Change_Password_ButtonActionPerformed

    private void Change_Username_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Change_Username_ButtonActionPerformed
        String usrname = Internal.Security.getEncodedString(New_Username.getText());
        if (usrname.equals(""))
        JOptionPane.showMessageDialog(null, "Username cannot be Empty. Please Try Again!",
            "Username Field Empty", JOptionPane.ERROR_MESSAGE);
        else {
            Internal.SQLite.setConfigValue("Username", usrname);
            JOptionPane.showMessageDialog(null, "Your Username changed Sucessfully!",
                "Sucess", JOptionPane.INFORMATION_MESSAGE);
            Speckle.Main.Content.removeAll();
            Settings scene = new Settings();
            scene.setBounds(0, 0, 948, 574);
            Speckle.Main.Content.add(scene).setVisible(true);
        }
    }//GEN-LAST:event_Change_Username_ButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Business_Location_Label;
    private javax.swing.JLabel Business_Name_Label;
    private javax.swing.JLabel Change_Password;
    private javax.swing.JButton Change_Password_Button;
    private javax.swing.JLabel Change_Username;
    private javax.swing.JButton Change_Username_Button;
    private javax.swing.JPasswordField Confirm_Password;
    private javax.swing.JLabel Confirm_Password_Label;
    private javax.swing.JLabel Contact_Number_Label;
    private javax.swing.JTextField Current_Business_Location;
    private javax.swing.JLabel Current_Business_Location_Label;
    private javax.swing.JTextField Current_Business_Name;
    private javax.swing.JTextField Current_Contact_Number;
    private javax.swing.JTextField Current_Email_Address;
    private javax.swing.JPasswordField Current_Password;
    private javax.swing.JLabel Current_Password_Label;
    private javax.swing.JTextField Current_Username;
    private javax.swing.JLabel Current_Username_Label;
    private javax.swing.JLabel Current_Value_Label;
    private javax.swing.JPanel Details_Tab;
    private javax.swing.JLabel Email_Address_Label;
    private javax.swing.JTextField New_Business_Location;
    private javax.swing.JLabel New_Business_Location_Label;
    private javax.swing.JTextField New_Business_Name;
    private javax.swing.JTextField New_Contact_Number;
    private javax.swing.JTextField New_Email_Address;
    private javax.swing.JPasswordField New_Password;
    private javax.swing.JLabel New_Password_Label;
    private javax.swing.JTextField New_Username;
    private javax.swing.JLabel New_Username_Label;
    private javax.swing.JLabel New_Value_Label;
    private javax.swing.JButton Reset;
    private javax.swing.JButton Save_Changes_Button;
    private javax.swing.JTabbedPane Settings_Container;
    private javax.swing.JLabel Speckle_Logo;
    private javax.swing.JLabel Update_Business_Details;
    private javax.swing.JPanel User_Tab;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
    String Recoverykey;
}
