package gui;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubDarkIJTheme;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import util.MySQL;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class attendence extends javax.swing.JFrame {

    public attendence() {
        initComponents();
        loadDataToTable(); // Refresh table data
        checkUserAccess();
        header();
        jLabel3.setText(SignIn.getEmployeeEmail());
    }

    // Method to get employee type based on email
    public int getEmployeeType(String email) throws Exception {
        String query = "SELECT employee_type_id FROM employee WHERE email = '" + email + "'";
        ResultSet rs = MySQL.executeSearch(query);
        if (rs.next()) {
            return rs.getInt("employee_type_id");
        } else {
            throw new Exception("Employee not found");
        }
    }

    // Method to check user type and control access
    public void checkUserAccess() {
        try {
            String email = SignIn.getEmployeeEmail();
            int employeeType = getEmployeeType(email);

            if (employeeType == 1) {
                // Admin user, allow access to all users
                loadEmployeeNames(true);
            } else if (employeeType == 2) {
                // Normal user, restrict access
                loadEmployeeNames(false);
            }
        } catch (Exception e) {
            Logger logger = SignIn.getLoggerObjet();
            logger.log(Level.WARNING, "Wrong Operation", e);
        }
    }

    private void loadEmployeeNames(boolean isAdmin) {
        try {
            String query = "SELECT * FROM employee";
            ResultSet rs = MySQL.executeSearch(query);
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            model.addElement("Select");
            while (rs.next()) {
                if (isAdmin) {
                    model.addElement(rs.getString("first_name") + " " + rs.getString("last_name"));
                } else {
                    // If not admin, only add the logged-in user's name
                    String email = SignIn.getEmployeeEmail();
                    if (rs.getString("email").equals(email)) {
                        model.addElement(rs.getString("first_name") + " " + rs.getString("last_name"));
                    }
                }
            }
            jComboBox1.setModel(model);
        } catch (Exception e) {
            Logger logger = SignIn.getLoggerObjet();
            logger.log(Level.WARNING, "Wrong Operation", e);
        }
    }

    private void header() {
        JTableHeader header = jTable1.getTableHeader();
        header.setFont(new Font("Consolas", Font.BOLD, 14));
        header.setForeground(Color.white);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.setDefaultRenderer(Object.class, renderer);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setBackground(new java.awt.Color(0, 153, 153));
        jButton1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Clock In");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 153, 153));
        jButton2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Clock Out");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Employee", "Sign In Time", "Sign Out Time", "Daily Payment"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jComboBox1.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "E1", "E2", "E3", "E4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel1.setText("Select the Employee");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/attendance.png"))); // NOI18N
        jLabel2.setText("Payrall and Attendence");

        jButton3.setBackground(new java.awt.Color(0, 153, 153));
        jButton3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Print Report");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton3MouseExited(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel3.setText("Employee Email");

        jButton4.setBackground(new java.awt.Color(0, 153, 153));
        jButton4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("DELETE");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton4MouseExited(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(283, 283, 283)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {

            String selectEmployee = String.valueOf(jComboBox1.getSelectedItem());

            String query = "SELECT * FROM ap WHERE employee = ?";
            PreparedStatement pstmt = MySQL.prepareStatement(query);
            pstmt.setString(1, selectEmployee);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                JOptionPane.showMessageDialog(
                        this,
                        selectEmployee + "has already clocked in for today", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            } else {

                if (jComboBox1.getSelectedItem() != null && !jComboBox1.getSelectedItem().equals("Select")) {
                    String selectedEmployee = (String) jComboBox1.getSelectedItem();

                    // Get the current date and time
                    java.util.Date date = new java.util.Date();
                    java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
                    java.sql.Date sqlDate = new java.sql.Date(timestamp.getTime());

                    // Check if the employee has already clocked in
                    if (isAlreadyClockedIn(selectedEmployee, sqlDate)) {
                        String message = selectedEmployee + " has already clocked in for today.";

                        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    // Insert into database (example code, replace with actual DB code)
                    boolean success = insertClockInRecord(selectedEmployee, timestamp);

                    if (success) {
                        String message = selectedEmployee + " clocked in at " + timestamp;

                        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
                        loadDataToTable(); // Refresh table data
                    } else {
                        String message = "Clock in failed for " + selectedEmployee;

                        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {

                    JOptionPane.showMessageDialog(this, "Please Select a Employee", "warning", JOptionPane.WARNING_MESSAGE);

                }

            }

        } catch (Exception e) {
            Logger logger = SignIn.getLoggerObjet();
            logger.log(Level.WARNING, "Wrong Operation", e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {

            // Get the selected employee
            if (jComboBox1.getSelectedItem() != null && !jComboBox1.getSelectedItem().equals("Select")) {
                String selectedEmployee = (String) jComboBox1.getSelectedItem();

                // Get the current date and time
                java.util.Date date = new java.util.Date();
                java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());

                // Get the clock-in time from the database
                java.sql.Timestamp clockInTime = getClockInTime(selectedEmployee);

                if (clockInTime == null) {
                    String message = selectedEmployee + "has already clocked Out for today ";

                    JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                // Calculate the time worked in hours
                long millisecondsWorked = timestamp.getTime() - clockInTime.getTime();
                double hoursWorked = millisecondsWorked / (1000.0 * 60 * 60);

                // Get the pay rate from the database
                double payRate = 500;
//        double payRate = getPayRate(selectedEmployee);
//        String payRateMessage = "Pay rate: $" + payRate;
//        System.out.println(payRateMessage);
//        JOptionPane.showMessageDialog(this, payRateMessage, "Info", JOptionPane.INFORMATION_MESSAGE);

                // Calculate the daily salary
                double dailySalary = payRate * hoursWorked;

                // Update the database (example code, replace with actual DB code)
                boolean success = insertClockOutRecord(selectedEmployee, timestamp, dailySalary);

                if (success) {
                    String message = selectedEmployee + " clocked out at " + timestamp + ". Daily salary: Rs" + dailySalary;
                    loadDataToTable(); // Refresh table data

                    JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String message = "Failed to clock out " + selectedEmployee;

                    JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please Select a Employee", "warning", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception e) {
            Logger logger = SignIn.getLoggerObjet();
            logger.log(Level.WARNING, "Wrong Operation", e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private boolean insertClockInRecord(String employee, java.sql.Timestamp timestamp) {
        try {
            String query = "INSERT INTO ap (`employee`, `date`, `in-time`) VALUES ('" + employee + "', '" + new java.sql.Date(timestamp.getTime()) + "', '" + timestamp + "')";
            return MySQL.executeIUD(query) > 0;
        } catch (Exception e) {
            Logger logger = SignIn.getLoggerObjet();
            logger.log(Level.WARNING, "Wrong Operation", e);
            return false;
        }
    }

    private boolean insertClockOutRecord(String employee, java.sql.Timestamp timestamp, double dailySalary) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat("#.00");

            String query = "UPDATE ap SET `out-time` = '" + timestamp + "', `payment` = " + decimalFormat.format(dailySalary) + " WHERE `employee` = '" + employee + "' AND `date` = '" + new java.sql.Date(timestamp.getTime()) + "' AND `out-time` IS NULL";
            return MySQL.executeIUD(query) > 0;
        } catch (Exception e) {
            Logger logger = SignIn.getLoggerObjet();
            logger.log(Level.WARNING, "Wrong Operation", e);
            return false;
        }
    }

    private java.sql.Timestamp getClockInTime(String employee) {
        try {
            String query = "SELECT `in-time` FROM ap WHERE `employee` = '" + employee + "' AND `out-time` IS NULL";
            ResultSet rs = MySQL.executeSearch(query);
            if (rs.next()) {
                return rs.getTimestamp("in-time");
            }
        } catch (Exception e) {
            Logger logger = SignIn.getLoggerObjet();
            logger.log(Level.WARNING, "Wrong Operation", e);
        }
        return null;
    }

    private double getPayRate(String employee) {
        try {
            String query = "SELECT `rate` FROM `pay-rate` WHERE `name` = '" + employee + "'";
            ResultSet rs = MySQL.executeSearch(query);
            if (rs.next()) {
                return rs.getDouble("rate");
            }
        } catch (Exception e) {
            Logger logger = SignIn.getLoggerObjet();
            logger.log(Level.WARNING, "Wrong Operation", e);
        }
        return 0;
    }

    private void loadDataToTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing data

        try {

            DecimalFormat decimalFormat = new DecimalFormat("#.00");

            String query = "SELECT `date`, `employee`, `in-time`, `out-time`, `payment` FROM ap";
            ResultSet rs = MySQL.executeSearch(query);
            while (rs.next()) {
                Object[] row = {
                    rs.getDate("date"),
                    rs.getString("employee"),
                    rs.getTimestamp("in-time"),
                    rs.getTimestamp("out-time"),
                    decimalFormat.format(rs.getDouble("payment")) // Format payment
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            Logger logger = SignIn.getLoggerObjet();
            logger.log(Level.WARNING, "Wrong Operation", e);
        }
    }

    private boolean isAlreadyClockedIn(String employee, java.sql.Date date) {
        try {
            String query = "SELECT COUNT(*) FROM ap WHERE `employee` = '" + employee + "' AND `date` = '" + date + "' AND `in-time` IS NOT NULL AND `out-time` IS NULL";
            ResultSet rs = MySQL.executeSearch(query);
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            Logger logger = SignIn.getLoggerObjet();
            logger.log(Level.WARNING, "Wrong Operation", e);
        }
        return false;
    }


    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {

            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String path = "src/reports/Attendence.jasper";
            //InputStream path = this.getClass().getResourceAsStream("src/reportnew/invoice1.jasper");

            HashMap<String, Object> params = new HashMap<>();
            params.put("Parameter1", jLabel3.getText());
            params.put("Parameter2", dateTime);

            Connection connection = MySQL.getConnection();

            JasperPrint jasperPrint = JasperFillManager.fillReport(path, params, connection);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            Logger logger = SignIn.getLoggerObjet();
            logger.log(Level.WARNING, "Wrong Operation", e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        jButton1.setForeground(Color.black);
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        jButton1.setForeground(Color.white);
    }//GEN-LAST:event_jButton1MouseExited

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        jButton2.setForeground(Color.black);
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited
        jButton2.setForeground(Color.white);
    }//GEN-LAST:event_jButton2MouseExited

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseEntered
        jButton3.setForeground(Color.black);
    }//GEN-LAST:event_jButton3MouseEntered

    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseExited
        jButton3.setForeground(Color.white);
    }//GEN-LAST:event_jButton3MouseExited

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        try {

            String logemail = jLabel3.getText();

            ResultSet resultSet = MySQL.executeSearch("SELECT `employee_type_id` FROM `employee` INNER JOIN `employee_type` ON "
                    + " `employee`.`employee_type_id` = `employee_type`.`id` WHERE `email`='" + logemail + "' ");

            if (resultSet.next()) {

                String empTypeid = resultSet.getString("employee_type_id");

                if (empTypeid.equals("1")) {
                    MySQL.executeIUD("DELETE FROM `ap`");

                    loadDataToTable();

                } else {
                    JOptionPane.showMessageDialog(this, "You are Not an Admin User", "warning", JOptionPane.WARNING_MESSAGE);
                }
            }

        } catch (Exception e) {
            Logger logger = SignIn.getLoggerObjet();
            logger.log(Level.WARNING, "Wrong Operation", e);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseEntered
        jButton4.setForeground(Color.black);
    }//GEN-LAST:event_jButton4MouseEntered

    private void jButton4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseExited
        jButton4.setForeground(Color.white);
    }//GEN-LAST:event_jButton4MouseExited

    public static void main(String args[]) {

        FlatGitHubDarkIJTheme.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new attendence().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
