package gui;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubDarkIJTheme;
import util.MySQL;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class attendence extends javax.swing.JFrame {

    public attendence() {
        initComponents();
        loadDataToTable(); // Refresh table data
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Clock In");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Clock Out");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Employee", "Sign In Time", "Sign Out Time", "Daily Payment"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "E1", "E2", "E3", "E4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Select the Employee");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(102, 102, 102)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Get the selected employee
        String selectedEmployee = (String) jComboBox1.getSelectedItem();

        // Get the current date and time
        java.util.Date date = new java.util.Date();
        java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
        java.sql.Date sqlDate = new java.sql.Date(timestamp.getTime());

        // Check if the employee has already clocked in
        if (isAlreadyClockedIn(selectedEmployee, sqlDate)) {
            String message = selectedEmployee + " has already clocked in for today.";
            System.out.println(message);
            JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Insert into database (example code, replace with actual DB code)
        boolean success = insertClockInRecord(selectedEmployee, timestamp);

        if (success) {
            String message = selectedEmployee + " clocked in at " + timestamp;
            System.out.println(message);
            JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
            loadDataToTable(); // Refresh table data
        } else {
            String message = "Clock in failed for " + selectedEmployee;
            System.out.println(message);
            JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Get the selected employee
        String selectedEmployee = (String) jComboBox1.getSelectedItem();

        // Get the current date and time
        java.util.Date date = new java.util.Date();
        java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());

        // Get the clock-in time from the database
        java.sql.Timestamp clockInTime = getClockInTime(selectedEmployee);

        if (clockInTime == null) {
            String message = "No clock-in record found for " + selectedEmployee;
            System.out.println(message);
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
            String message = selectedEmployee + " clocked out at " + timestamp + ". Daily salary: $" + dailySalary;
            loadDataToTable(); // Refresh table data
            System.out.println(message);
            JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String message = "Failed to clock out " + selectedEmployee;
            System.out.println(message);
            JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private boolean insertClockInRecord(String employee, java.sql.Timestamp timestamp) {
        try {
            String query = "INSERT INTO ap (`employee`, `date`, `in-time`) VALUES ('" + employee + "', '" + new java.sql.Date(timestamp.getTime()) + "', '" + timestamp + "')";
            return MySQL.executeIUD(query) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean insertClockOutRecord(String employee, java.sql.Timestamp timestamp, double dailySalary) {
        try {
            String query = "UPDATE ap SET `out-time` = '" + timestamp + "', `payment` = " + dailySalary + " WHERE `employee` = '" + employee + "' AND `date` = '" + new java.sql.Date(timestamp.getTime()) + "' AND `out-time` IS NULL";
            return MySQL.executeIUD(query) > 0;
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return 0;
    }

    private void loadDataToTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing data

        try {
            String query = "SELECT `date`, `employee`, `in-time`, `out-time`, `payment` FROM ap";
            ResultSet rs = MySQL.executeSearch(query);
            while (rs.next()) {
                Object[] row = {
                    rs.getDate("date"),
                    rs.getString("employee"),
                    rs.getTimestamp("in-time"),
                    rs.getTimestamp("out-time"),
                    rs.getDouble("payment")
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return false;
    }


    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    }//GEN-LAST:event_jComboBox1ActionPerformed

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
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
