package gui;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubDarkIJTheme;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import util.MySQL;

public class stock_managment extends javax.swing.JFrame {

    private Invoice invoice;

    public void setinvoice(Invoice invoice) {
        this.invoice = invoice;
    }
    
    private Return_and_Refund raf;

    public void setraf(Return_and_Refund raf) {
        this.raf = raf;
    }
    
    Date date = new Date();
    String newDate = new SimpleDateFormat("yyyy-MM-dd").format(date);

    public stock_managment() {
        initComponents();
        loadStock();
        jComboBox2.grabFocus();
        jComboBox2.setEnabled(false);
        jLabel12.setText(newDate);
    }
    
    private void loadProduct() {
        try {

            String query = "SELECT * FROM `product` INNER JOIN `stock` ON `product`.`id` = `stock`.`product_id` "
                    + " INNER JOIN `brand` ON `brand`.`id` = `product`.`brand_id` "
                    + " INNER JOIN `category` ON `category`.`id` = `product`.`category_id`"
                    + " INNER JOIN `size` ON `size`.`id` = `product`.`size_id`"
                    + " INNER JOIN `colour` ON `colour`.`id` = `product`.`colour_id`";

            int row = jTable2.getSelectedRow();

            if (row != -1) {
                String pid = String.valueOf(jTable2.getValueAt(row, 1));
                query += "WHERE `product`.`id` = '" + pid + "' ";
            }

            if (query.contains("WHERE")) {
                query += "AND ";
            } else {
                query += "WHERE ";
            }

            String sort = String.valueOf(jComboBox2.getSelectedItem());

            query += "ORDER BY ";

            query = query.replace("WHERE ORDER BY ", "ORDER BY ");
            query = query.replace("AND ORDER BY ", "ORDER BY ");

            if (sort.equals("Product ID ASC")) {
                query += "`product`.`id` ASC";
            } else if (sort.equals("Product ID DESC")) {
                query += "`product`.`id` DESC";
            } else if (sort.equals("Brand ID ASC")) {
                query += "`brand`.`id` ASC";
            } else if (sort.equals("Brand ID DESC")) {
                query += "`brand`.`id` DESC";
            } else if (sort.equals("Brand ASC")) {
                query += "`brand`.`name` ASC";
            } else if (sort.equals("Brand DESC")) {
                query += "`brand`.`name` DESC";
            } else if (sort.equals("Product ASC")) {
                query += "`product`.`name` ASC";
            } else if (sort.equals("Product DESC")) {
                query += "`product`.`name` DESC";
            } else if (sort.equals("Category ASC")) {
                query += "`category`.`name` ASC";
            } else if (sort.equals("Category DESC")) {
                query += "`category`.`name` DESC";
            } else if (sort.equals("Size ASC")) {
                query += "`size`.`name` ASC";
            } else if (sort.equals("Size DESC")) {
                query += "`size`.`name` DESC";
            } else if (sort.equals("Colour ASC")) {
                query += "`colour`.`name` ASC";
            } else if (sort.equals("Colour DESC")) {
                query += "`colour`.`name` DESC";
            }

            ResultSet rs = MySQL.executeSearch(query);

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString("product.id"));
                v.add(rs.getString("brand.id"));
                v.add(rs.getString("brand.name"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("category.name"));
                v.add(rs.getString("size.size"));
                v.add(rs.getString("colour.name"));

                dtm.addRow(v);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadStock() {
        try {

            String query = "SELECT * FROM `stock` INNER JOIN `product` ON `stock`.`product_id` = `product`.`id` "
                    + " INNER JOIN `brand` ON `brand`.`id` = `product`.`brand_id` ";

            if (query.contains("WHERE")) {
                query += "AND ";
            } else {
                query += "WHERE ";
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
                query += "`stock`.`price` >= '" + min_price + "' ";

            } else if (min_price == 0 && max_price > 0) {
                query += "`stock`.`price` <= '" + max_price + "' ";

            } else if (min_price > 0 && max_price > 0) {
                query += "`stock`.`price` >= '" + min_price + "' AND `stock`.`price` <= '" + max_price + "' ";
            }

            Date start = null;
            Date end = null;

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            if (jDateChooser1.getDate() != null) {
                start = jDateChooser1.getDate();
                query += "`stock`.`mfd` > '" + format.format(start) + "' AND ";
            }

            if (jDateChooser2.getDate() != null) {
                end = jDateChooser2.getDate();
                query += "`stock`.`exp` < '" + format.format(end) + "' ";
            }

            String sort = String.valueOf(jComboBox1.getSelectedItem());

            query += "ORDER BY ";

            query = query.replace("WHERE ORDER BY ", "ORDER BY ");
            query = query.replace("AND ORDER BY ", "ORDER BY ");

            if (sort.equals("Stock ID ASC")) {
                query += "`stock`.`id` ASC";
            } else if (sort.equals("Stock ID DESC")) {
                query += "`stock`.`id` DESC";
            } else if (sort.equals("Product ID ASC")) {
                query += "`product`.`id` ASC";
            } else if (sort.equals("Product ID DESC")) {
                query += "`product`.`id` DESC";
            } else if (sort.equals("Brand ASC")) {
                query += "`brand`.`id` ASC";
            } else if (sort.equals("Brand DESC")) {
                query += "`brand`.`id` DESC";
            } else if (sort.equals("Name ASC")) {
                query += "`product`.`name` ASC";
            } else if (sort.equals("Name DESC")) {
                query += "`product`.`name` DESC";
            } else if (sort.equals("Selling Price ASC")) {
                query += "`stock`.`price` ASC";
            } else if (sort.equals("Selling Price DESC")) {
                query += "`stock`.`price` DESC";
            } else if (sort.equals("Quantity ASC")) {
                query += "`stock`.`qty` ASC";
            } else if (sort.equals("Quantity DESC")) {
                query += "`stock`.`qty` DESC";
            }

            ResultSet rs = MySQL.executeSearch(query);

            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString("stock.id"));
                v.add(rs.getString("product.id"));
                v.add(rs.getString("brand.name"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("stock.price"));
                v.add(rs.getString("qty"));
                v.add(rs.getString("mfd"));
                v.add(rs.getString("exp"));

                dtm.addRow(v);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jButton5 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField1.setText("0");

        jLabel7.setText("Selling Price");

        jLabel8.setText("To");

        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField2.setText("0");

        jButton5.setText("Find");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel9.setText("Stock in");

        jButton6.setText("Find");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Clear All");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Product Id", "Brand", "Name", "Selling Price", "Quantity", "Stock in Date", "Stock out Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel10.setText("Out");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Available Stocks");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("date");

        jLabel2.setText("Sort By");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Stock ID ASC", "Stock ID DESC", "Product ID ASC", "Product ID DESC", "Brand ASC", "Brand DESC", "Name ASC", "Name DESC", "Selling Price ASC", "Selling Price DESC", "Quantity ASC", "Quantity DESC" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFormattedTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(280, 280, 280)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel12))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)
                                .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addComponent(jButton5))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(jButton6)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Brand Id", "Brand Name", "Product Name", "Category", "Size", "Colour"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel6.setText("Sort By");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Product ID ASC", "Product ID DESC", "Brand ID ASC", "Brand ID DESC", "Brand ASC", "Brand DESC", "Product ASC", "Product DESC", "Category ASC", "Category DESC", "Size ASC", "Size DESC", "Colour ASC", "Colour DESC" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jButton3.setText("Clear All");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Related Products");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(133, 133, 133)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3)
                        .addComponent(jLabel3)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked

        loadProduct();
        jComboBox2.setEnabled(true);

        /*if (evt.getClickCount() == 1) {
            if (raf != null) {
                int selectedRow = jTable2.getSelectedRow();
                raf.getjLabel10().setText(String.valueOf(jTable2.getValueAt(selectedRow, 0)));
                raf.getjLabel13().setText(String.valueOf(jTable2.getValueAt(selectedRow, 2)));
                raf.getjLabel17().setText(String.valueOf(jTable2.getValueAt(selectedRow, 3)));
                raf.getjLabel15().setText(String.valueOf(jTable2.getValueAt(selectedRow, 4)));
                raf.getjLabel19().setText(String.valueOf(jTable2.getValueAt(selectedRow, 6)));
                raf.getjLabel21().setText(String.valueOf(jTable2.getValueAt(selectedRow, 7)));
                raf.getjLabel27().setText(String.valueOf(jTable2.getValueAt(selectedRow, 5)));
                this.dispose();
            }
        }*/

        /*if (evt.getClickCount() == 2) {
            if (invoice != null) {
                int selectedRow2 = jTable2.getSelectedRow();
                invoice.getjLabel10().setText(String.valueOf(jTable2.getValueAt(selectedRow2, 0)));
                invoice.getjLabel13().setText(String.valueOf(jTable2.getValueAt(selectedRow2, 2)));
                invoice.getjLabel17().setText(String.valueOf(jTable2.getValueAt(selectedRow2, 3)));
                invoice.getjLabel15().setText(String.valueOf(jTable2.getValueAt(selectedRow2, 4)));
                invoice.getjLabel19().setText(String.valueOf(jTable2.getValueAt(selectedRow2, 6)));
                invoice.getjLabel21().setText(String.valueOf(jTable2.getValueAt(selectedRow2, 7)));
                invoice.getjLabel27().setText(String.valueOf(jTable2.getValueAt(selectedRow2, 5)));
                this.dispose();
            }
        }*/
        
        if (evt.getClickCount() == 3) {

            int selectedRow1 = jTable2.getSelectedRow();
            String pid = String.valueOf(jTable2.getValueAt(selectedRow1, 1));
            String brand = String.valueOf(jTable2.getValueAt(selectedRow1, 2));
            String name = String.valueOf(jTable2.getValueAt(selectedRow1, 3));
            String price = String.valueOf(jTable2.getValueAt(selectedRow1, 4));
            String qty = String.valueOf(jTable2.getValueAt(selectedRow1, 5));
            String sin = String.valueOf(jTable2.getValueAt(selectedRow1, 6));
            String sout = String.valueOf(jTable2.getValueAt(selectedRow1, 7));

            single_stock_view stv = new single_stock_view(this, true, pid, brand, name, price, qty, sin, sout);
            stv.setVisible(true);
        }
        
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        loadStock();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        loadStock();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        reset();
        loadStock();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        resetProductUI();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        loadProduct();
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        loadStock();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    public static void main(String args[]) {
        FlatGitHubDarkIJTheme.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new stock_managment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables

        private void resetProductUI() {
        jComboBox2.setSelectedIndex(0);
        DefaultTableModel model2 = (DefaultTableModel) jTable1.getModel();
        model2.setRowCount(0);
        jComboBox2.setEnabled(false);
    }

    private void reset() {
        jComboBox1.setSelectedIndex(0);
        jComboBox1.grabFocus();
        jTable2.clearSelection();
        jFormattedTextField1.setText("0");
        jFormattedTextField2.setText("0");
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        jComboBox2.setEnabled(false);
    }
}
