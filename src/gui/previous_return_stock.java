package gui;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import util.MySQL;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class previous_return_stock extends javax.swing.JFrame {

    private Return_damaged_stock rds;

    public void setrds(Return_damaged_stock rds) {
        this.rds = rds;
    }

    Date date = new Date();
    String newDate = new SimpleDateFormat("yyyy-MM-dd").format(date);

    public previous_return_stock() {
        initComponents();
        loadStock();
        jLabel12.setText(newDate);
        header();
        setIconImage(new ImageIcon("src/resources/icon.jpg").getImage());
    }

    private void header() {
        JTableHeader header = jTable1.getTableHeader();
        header.setFont(new Font("DL-Paras", Font.BOLD, 14));
        header.setForeground(Color.white);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.setDefaultRenderer(Object.class, renderer);

    }

    private void loadStock() {
        try {

            String query = "SELECT * FROM `return_stock` INNER JOIN `employee` ON `return_stock`.`employee_email` = `employee`.`email` "
                    + " INNER JOIN `supplier` ON `return_stock`.`supplier_mobile` = `supplier`.`mobile` "
                    + " INNER JOIN `payment_method` ON `return_stock`.`payment_method_id` = `payment_method`.`id`";

            if (query.contains("WHERE")) {
                query += "AND ";
            } else {
                query += "WHERE ";
            }

            String mobile = "";

            if (!jTextField1.getText().isEmpty()) {
                mobile = String.valueOf(jTextField1.getText());
                query += "`return_stock`.`supplier_mobile`  LIKE '%" + mobile + "%' ";
            }

            String email = "";

            if (!jTextField2.getText().isEmpty()) {
                email = String.valueOf(jTextField2.getText());
                query += "`return_stock`.`employee_email` LIKE '%" + email + "%' ";
            }

            double min_price = 0;
            double max_price = 0;

            if (!jFormattedTextField1.getText().isEmpty()) {
                min_price = Double.parseDouble(jFormattedTextField1.getText());
            }

            if (!jFormattedTextField2.getText().isEmpty()) {
                max_price = Double.parseDouble(jFormattedTextField2.getText());
            }

            if (min_price > 0 && max_price == 0) {
                query += "`return_stock`.`paidamount` >= '" + min_price + "' ";

            } else if (min_price == 0 && max_price > 0) {
                query += "`return_stock`.`paidamount` <= '" + max_price + "' ";

            } else if (min_price > 0 && max_price > 0) {
                query += "`return_stock`.`paidamount` >= '" + min_price + "' AND `return_stock`.`paidamount` <= '" + max_price + "' ";
            }

            Date start = null;
            Date end = null;

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            if (jDateChooser1.getDate() != null) {
                start = jDateChooser1.getDate();
                query += "`return_stock`.`date` > '" + format.format(start) + "' AND ";
            }

            if (jDateChooser2.getDate() != null) {
                end = jDateChooser2.getDate();
                query += "`return_stock`.`date` < '" + format.format(end) + "' ";
            }

            String sort = String.valueOf(jComboBox1.getSelectedItem());

            query += "ORDER BY ";

            query = query.replace("WHERE ORDER BY ", "ORDER BY ");
            query = query.replace("AND ORDER BY ", "ORDER BY ");

            if (sort.equals("Invoice ID ASC")) {
                query += "`return_stock`.`id` ASC";
            } else if (sort.equals("Invoice ID DESC")) {
                query += "`return_stock`.`id` DESC";
            } else if (sort.equals("Supplier mobile ASC")) {
                query += "`supplier`.`mobile` ASC";
            } else if (sort.equals("Supplier mobile DESC")) {
                query += "`supplier`.`mobile` DESC";
            } else if (sort.equals("Date ASC")) {
                query += "`return_stock`.`date` ASC";
            } else if (sort.equals("Date DESC")) {
                query += "`return_stock`.`date` DESC";
            } else if (sort.equals("Paid amount ASC")) {
                query += "`return_stock`.`paidamount` ASC";
            } else if (sort.equals("Paid amount DESC")) {
                query += "`return_stock`.`paidamount` DESC";
            }

            ResultSet rs = MySQL.executeSearch(query);

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString("return_stock.id"));
                v.add(rs.getString("supplier.mobile"));
                v.add(rs.getString("return_stock.date"));
                v.add(rs.getString("return_stock.paidamount"));
                v.add(rs.getString("payment_method.name"));
                v.add(rs.getString("return_stock.employee_email"));

                dtm.addRow(v);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton6 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("UL Dress Mart");

        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel2.setText("Sort By");

        jComboBox1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Invoice ID ASC", "Invoice ID DESC", "Supplier mobile ASC", "Supplier mobile DESC", "Date ASC", "Date DESC", "Paid amount ASC", "Paid amount DESC", " " }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel3.setText("Find by mobile");

        jLabel9.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel9.setText("Find by date from");

        jTextField1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jDateChooser1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N

        jButton6.setBackground(new java.awt.Color(0, 153, 153));
        jButton6.setFont(new java.awt.Font("DL-Paras.", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Find");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton6MouseExited(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel4.setText("Find by emp email");

        jTextField2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 153, 153));
        jButton2.setFont(new java.awt.Font("DL-Paras.", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Clear");
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

        jLabel1.setFont(new java.awt.Font("DL-Paras.", 1, 24)); // NOI18N
        jLabel1.setText("Previous Return Stocks");

        jLabel12.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("date");

        jTable1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Customer Mobile", "Date", "Paid Amount", "Payment Method", "Employee email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jDateChooser2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel5.setText("To");

        jLabel7.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel7.setText("Selling Price");

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField1.setText("0");
        jFormattedTextField1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel8.setText("To");

        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField2.setText("0");
        jFormattedTextField2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N

        jButton5.setBackground(new java.awt.Color(0, 153, 153));
        jButton5.setFont(new java.awt.Font("DL-Paras.", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Find");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton5MouseExited(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 153, 153));
        jButton1.setFont(new java.awt.Font("DL-Paras.", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Print Report");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel9))
                                        .addGap(9, 9, 9)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                                            .addComponent(jTextField1)
                                            .addComponent(jFormattedTextField1))
                                        .addGap(33, 33, 33)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                                                    .addComponent(jFormattedTextField2))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(18, 18, 18)
                                                .addComponent(jTextField2))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(143, 143, 143)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(26, 26, 26))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel12))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel9))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6))
                                    .addComponent(jButton6))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton5)
                                    .addComponent(jButton2))))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        loadStock();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        loadStock();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        if (evt.getClickCount() == 2) {

            try {
                int selectedRow1 = jTable1.getSelectedRow();
                String id = String.valueOf(jTable1.getValueAt(selectedRow1, 0));
                String mobile = String.valueOf(jTable1.getValueAt(selectedRow1, 1));
                String date = String.valueOf(jTable1.getValueAt(selectedRow1, 2));
                String amount = String.valueOf(jTable1.getValueAt(selectedRow1, 3));
                String method = String.valueOf(jTable1.getValueAt(selectedRow1, 4));
                String email = String.valueOf(jTable1.getValueAt(selectedRow1, 5));

                detailed_return_stock dtrs = new detailed_return_stock(this, true, id, mobile, date, amount, method, email);
                dtrs.setVisible(true);
                //this.dispose();

            } catch (ParseException ex) {
                Logger.getLogger(previous_return_stock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        loadStock();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        reset();
        loadStock();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        loadStock();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        loadStock();
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String path = "src/reports/PreviousReturnStock.jasper";
            //InputStream path = this.getClass().getResourceAsStream("src/reportnew/invoice1.jasper");

            HashMap<String, Object> params = new HashMap<>();

            params.put("Parameter9", dateTime);

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(path, params, dataSource);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
       jButton2.setForeground(Color.black);
    }//GEN-LAST:event_jButton2MouseEntered

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseExited
      jButton2.setForeground(Color.white);
    }//GEN-LAST:event_jButton2MouseExited

    private void jButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseEntered
       jButton6.setForeground(Color.black);
    }//GEN-LAST:event_jButton6MouseEntered

    private void jButton6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseExited
        jButton6.setForeground(Color.white);
    }//GEN-LAST:event_jButton6MouseExited

    private void jButton5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseEntered
       jButton5.setForeground(Color.black);
    }//GEN-LAST:event_jButton5MouseEntered

    private void jButton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseExited
        jButton5.setForeground(Color.white);
    }//GEN-LAST:event_jButton5MouseExited

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
     jButton1.setForeground(Color.black);
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        jButton1.setForeground(Color.white);
    }//GEN-LAST:event_jButton1MouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        jComboBox1.setSelectedIndex(0);
        jComboBox1.grabFocus();
        jTable1.clearSelection();
        jFormattedTextField1.setText("0");
        jFormattedTextField2.setText("0");
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        jTextField1.setText("");
        jTextField2.setText("");
    }
}
