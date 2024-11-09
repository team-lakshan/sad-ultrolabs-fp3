package gui;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubDarkIJTheme;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.MySQL;

public class CustomerRegistration extends javax.swing.JFrame {

    private Invoice invoice;

    public void setinvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    private Return_and_Refund raf;

    public void setraf(Return_and_Refund raf) {
        this.raf = raf;
    }

    public CustomerRegistration() {
        initComponents();
        loadCustomer("first_name", "ASC", jTextField1.getText());

    }

    private void loadCustomer(String colum, String orderby, String mobile) {

        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `customer` ORDER BY '" + colum + "' " + orderby + "");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("mobile"));
                vector.add(resultSet.getString("first_name"));
                vector.add(resultSet.getString("last_name"));
                vector.add(resultSet.getString("email"));
                vector.add(resultSet.getString("point"));

                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Customer Registration");

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel1.setText("Customer Registration");

        jLabel2.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jLabel2.setText("Mobile");

        jLabel3.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jLabel3.setText("First Name");

        jLabel4.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jLabel4.setText("Last name");

        jLabel5.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jLabel5.setText("Email");

        jTextField1.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jTextField1.setToolTipText("enter your valid mobile");
        jTextField1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField1.setDisabledTextColor(new java.awt.Color(255, 0, 51));

        jTextField2.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N

        jTextField3.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N

        jTextField4.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jTextField4.setToolTipText("@ and gmail.com required");

        jButton1.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jButton1.setText("Create Account");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jButton2.setText("Update Account");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jButton3.setText("Clerar All");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(139, 139, 139)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(229, 229, 229)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(93, 93, 93)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 10, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(188, 188, 188))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jTable1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mobile", "First Name", "Last Name", "Email", "Points"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(51, 0, 255));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel6.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jLabel6.setText("Customer Sort By");

        jComboBox1.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name ACS", "Name DESC", "Points ACS", "Points DESC" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jLabel7.setText("Total Invoice");

        jLabel8.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jLabel8.setText("invoicee count");
        jLabel8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jLabel8KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addGap(54, 54, 54))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String mobile = jTextField1.getText();
        String firstname = jTextField2.getText();
        String lastname = jTextField3.getText();
        String email = jTextField4.getText();

        if (mobile.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter mobile", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (mobile.matches("^07[01245678]{1}[0-9]{7}$")) {
            JOptionPane.showMessageDialog(this, "Please enter valid Mobile", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (firstname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter First Name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (lastname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Last Name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Email", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            JOptionPane.showMessageDialog(this, "Invalid email", "warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `customer` WHERE `mobile` = '" + mobile + "' OR `email` = '" + email + "'");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "Customer already regisitered", "warning", JOptionPane.WARNING_MESSAGE);

                } else {
                    MySQL.executeIUD("INSERT INTO `customer`(`mobile`,`first_name`,`last_name`,`email`,`point`) "
                            + "VALUE ('" + mobile + "','" + firstname + "','" + lastname + "','" + email + "','0') ");
                }

                loadCustomer("first_name", "ASC", jTextField1.getText());
                reset();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String mobile = jTextField1.getText();
        String firstname = jTextField2.getText();
        String lastname = jTextField3.getText();
        String email = jTextField4.getText();

        if (firstname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter First Name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (lastname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Last Name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Email", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            JOptionPane.showMessageDialog(this, "Invalid email", "warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `customer` WHERE `email` = '" + email + "'");

                boolean canUpdate = false;

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "Customer already regisitered", "warning", JOptionPane.WARNING_MESSAGE);

                    if (resultSet.getString(mobile).equals(mobile)) {
                        canUpdate = true;
                    } else {
                        JOptionPane.showMessageDialog(this, "Email already added", "warning", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    canUpdate = true;

                    if (canUpdate) {
                        MySQL.executeIUD("UPDATE `customer` SET `first_name` = '" + firstname + "',`last_name` = '" + lastname + "',`email` = '" + email + "'"
                                + "WHERE `mobile` = '" + mobile + "'");
                    }

                    loadCustomer("first_name", "ASC", jTextField1.getText());
                    reset();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        reset();
        loadCustomer("first_name", "ASC", jTextField1.getText());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        search();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jLabel8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel8KeyReleased
        search();
    }//GEN-LAST:event_jLabel8KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();

        String mobile = String.valueOf(jTable1.getValueAt(row, 0));
        String fname = String.valueOf(jTable1.getValueAt(row, 1));
        String lname = String.valueOf(jTable1.getValueAt(row, 2));
        String email = String.valueOf(jTable1.getValueAt(row, 3));

        jTextField1.setText(mobile);
        jTextField1.setEditable(false);

        jTextField2.setText(fname);
        jTextField3.setText(lname);
        jTextField4.setText(email);
        
        if (evt.getClickCount() == 2) {
            if (invoice != null) {
                invoice.getjLabel7().setText(String.valueOf(jTable1.getValueAt(row, 0)));
                invoice.getjLabel8().setText(String.valueOf(jTable1.getValueAt(row, 1)));
                invoice.getjTextField1().setText(String.valueOf(jTable1.getValueAt(row, 4)));
                this.dispose();
            }
        }

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT COUNT(id) FROM `invoice` WHERE `customer_mobile` = '" + mobile + "'");

            if (resultSet.next()) {
                jLabel8.setText(resultSet.getString(1));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    public static void main(String args[]) {

        FlatGitHubDarkIJTheme.setup();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerRegistration().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField1.grabFocus();

        jTextField1.setEditable(true);
        jTable1.clearSelection();
    }

    private void search() {
        int sort = jComboBox1.getSelectedIndex();

        if (sort == 0) {
            loadCustomer("first_name", "ASC", jTextField1.getText());
        } else if (sort == 1) {
            loadCustomer("first_name", "ASC", jTextField1.getText());

        } else if (sort == 2) {
            loadCustomer("first_name", "ASC", jTextField1.getText());

        } else if (sort == 3) {
            loadCustomer("first_name", "ASC", jTextField1.getText());

        }
    }
}
