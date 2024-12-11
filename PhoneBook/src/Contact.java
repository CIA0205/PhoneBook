
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Contact extends javax.swing.JFrame {

    DefaultTableModel dm;

    private ResultSet rs;

    public Contact() {
        initComponents();
        Connect();
        Get();
        id_auto();
        sortAndUpdateIds();
    }
    Connection con;
    PreparedStatement pst;
    UpdateStatement updateStmt;

    Contact(String msg1, String msg2, String msg3) {
        initComponents();
        phone.setText(msg1);
        phone.setText(msg2);
        name.setText(msg3);

    }

    public void sortAndUpdateIds() {
        Connection con = null;
        PreparedStatement selectStmt = null;
        PreparedStatement updateIdStmt = null;
        PreparedStatement checkExistingIdStmt = null;
        ResultSet resultSet = null;
        ResultSet existingIdCheckResult = null;

        try {
            // Step 1: Connect to the database
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/phonebook", "root", "");

            // Step 2: Retrieve and sort the data alphabetically by name
            String selectQuery = "SELECT id, name FROM final ORDER BY name ASC";
            selectStmt = (PreparedStatement) con.prepareStatement(selectQuery);
            resultSet = selectStmt.executeQuery();

            // Step 3: Prepare for sequential ID update
            String updateIdQuery = "UPDATE final SET id = ? WHERE id = ?";
            updateIdStmt = (PreparedStatement) con.prepareStatement(updateIdQuery);

            // Check if the nextId already exists in the database
            String checkExistingIdQuery = "SELECT id FROM final WHERE id = ?";

            checkExistingIdStmt = (PreparedStatement) con.prepareStatement(checkExistingIdQuery);

            // Initialize the next available ID
            int nextId = 1;

            while (resultSet.next()) {
                int currentId = resultSet.getInt("id");

                // Check if the current nextId already exists in the database
                checkExistingIdStmt.setInt(1, nextId);
                existingIdCheckResult = checkExistingIdStmt.executeQuery();

                // If the nextId does not exist, update the current ID with the nextId
                if (!existingIdCheckResult.next()) {
                    updateIdStmt.setInt(1, nextId); // Set the new sequential ID
                    updateIdStmt.setInt(2, currentId); // Match by the current ID
                    updateIdStmt.executeUpdate();
                }

                nextId++; // Increment the next available ID
            }

            // Success message
            //JOptionPane.showMessageDialog(this, "Data sorted alphabetically and IDs updated successfully!");
            // Reload the table data after updating IDs
            Get(); // Make sure 'Get' method reloads the data into the table or GUI

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Contact.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: Unable to load database driver.");
        } catch (SQLException ex) {
            Logger.getLogger(Contact.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (existingIdCheckResult != null) {
                    existingIdCheckResult.close();
                }
                if (selectStmt != null) {
                    selectStmt.close();
                }
                if (updateIdStmt != null) {
                    updateIdStmt.close();
                }
                if (checkExistingIdStmt != null) {
                    checkExistingIdStmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Contact.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void id_auto() {
        try {
            // Step 1: Connect to the database
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/phonebook", "root", "");

            // Step 2: Query to find the maximum ID
            String sqlQuery = "SELECT MAX(id) AS max_id FROM final";
            try (PreparedStatement pst = (PreparedStatement) con.prepareStatement(sqlQuery);
                    ResultSet rs = pst.executeQuery()) {

                // Step 3: Calculate the next ID
                int nextId = 1; // Default to 1 if the table is empty
                if (rs.next() && rs.getInt("max_id") != 0) {
                    nextId = rs.getInt("max_id") + 1; // Increment the maximum ID by 1
                }

                // Step 4: Set the next ID in the text field
                num.setText(String.valueOf(nextId));

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Contact.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: Unable to load database driver.");
        } catch (SQLException ex) {
            Logger.getLogger(Contact.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }

    }

    public void Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/phonebook", "root", "");
            System.out.println("Connection established successfully.");
        } catch (SQLException ex) {
            Logger.getLogger(Contact.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Contact.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        name = new javax.swing.JTextField();
        phone = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        update = new javax.swing.JButton();
        add = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        s = new javax.swing.JTextField();
        num = new javax.swing.JTextField();
        cb = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        combo = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("PHONE NUMBER:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("NAME:");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Phone", "Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(0).setHeaderValue("Id");
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(1).setHeaderValue("Phone");
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(2).setHeaderValue("Name");
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneActionPerformed(evt);
            }
        });
        phone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phoneKeyTyped(evt);
            }
        });

        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        add.setText("Add");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        jLabel1.setText("Search:");

        s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sActionPerformed(evt);
            }
        });
        s.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(add)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(update)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(s, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(update)
                .addComponent(add)
                .addComponent(delete)
                .addComponent(jLabel1)
                .addComponent(s, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        num.setEditable(false);
        num.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numActionPerformed(evt);
            }
        });
        num.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                numKeyReleased(evt);
            }
        });

        cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "D", "All" }));
        cb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbItemStateChanged(evt);
            }
        });
        cb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("ID:");

        combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Non", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", " " }));
        combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboItemStateChanged(evt);
            }
        });

        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(cb, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton1)
                                    .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(64, 64, 64))))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(62, 62, 62))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
try {
    // Get the values from the text fields
    String Phone = phone.getText();
    String Name = name.getText();

    // Validate fields are not empty
    if (Phone.isEmpty() || Name.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Phone and Name cannot be empty.");
        return;
    }

    // Check for existing phone number
    String phoneCheckQuery = "SELECT COUNT(*) FROM final WHERE phone = ?";
    try (PreparedStatement phoneCheckStmt = (PreparedStatement) con.prepareStatement(phoneCheckQuery)) {
        phoneCheckStmt.setString(1, Phone);
        ResultSet phoneCheckRs = phoneCheckStmt.executeQuery();

        if (phoneCheckRs.next() && phoneCheckRs.getInt(1) > 0) {
            JOptionPane.showMessageDialog(this, "This phone number already exists.");
            return;
        }
    }

    // Calculate the next ID based on the current count of rows
    String countQuery = "SELECT COUNT(*) FROM final";
    int nextId = 1; // Default to 1 if the table is empty
    try (PreparedStatement countStmt = (PreparedStatement) con.prepareStatement(countQuery)) {
        ResultSet countRs = countStmt.executeQuery();
        if (countRs.next()) {
            nextId = countRs.getInt(1) + 1; // Assign ID as row count + 1
        }
    }

    // Insert the new record with the calculated ID
    String insertQuery = "INSERT INTO final (id, phone, name) VALUES (?, ?, ?)";
    try (PreparedStatement insertStmt = (PreparedStatement) con.prepareStatement(insertQuery)) {
        insertStmt.setInt(1, nextId);
        insertStmt.setString(2, Phone);
        insertStmt.setString(3, Name);

        int rowsAffected = insertStmt.executeUpdate();
        if (rowsAffected == 1) {
            JOptionPane.showMessageDialog(this, "Record added successfully!");

            // Reload and display data in alphabetical order
            Get();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to save the record.");
        }
    }
} catch (SQLException ex) {
    Logger.getLogger(Contact.class.getName()).log(Level.SEVERE, null, ex);
    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
}
    }//GEN-LAST:event_addActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        // TODO add your handling code here:

        int selectedRowIndex = table.getSelectedRow();

        if (selectedRowIndex != -1 && selectedRowIndex < table.getRowCount()) {
            // Retrieve data from the selected row
            Object No = table.getValueAt(selectedRowIndex, 0);
            Object Phone = table.getValueAt(selectedRowIndex, 1);
            Object Name = table.getValueAt(selectedRowIndex, 2);

            // Check for null values
            if (Phone != null && Phone != null && Name != null) {
                // Convert data to strings
                String msg1 = No.toString();
                String msg2 = Phone.toString();
                String msg3 = Name.toString();

                // Now you can use the retrieved data as needed
                new Update2(msg1, msg2, msg3).setVisible(true);
            } else {
                // Handle case where any of the values retrieved from the table are null
                JOptionPane.showMessageDialog(this, "Error: One or more values retrieved from the table are null.");
            }
        } else {
            // Handle case where no row is selected or selected row index is out of bounds
            JOptionPane.showMessageDialog(this, "Please select a valid row from the table.");
        }
    }//GEN-LAST:event_updateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        // Assuming 'table' is your JTable
        int selectedRow = table.getSelectedRow();  // Get selected row index

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a record first.");
        } else {
            // Get the ID from the selected row (assuming it's in the first column)
            String Num = table.getValueAt(selectedRow, 0).toString();

            // Confirm deletion
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete this record?",
                    "Delete Confirmation",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                try (Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/phonebook", "root", "")) {
                    // Step 1: Delete the selected record from the database
                    String deleteQuery = "DELETE FROM final WHERE id = ?";
                    try (PreparedStatement pst = (PreparedStatement) con.prepareStatement(deleteQuery)) {
                        pst.setString(1, Num);  // Set ID parameter
                        int rowsAffected = pst.executeUpdate();

                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null, "Record successfully deleted.");

                            // Step 2: Reassign IDs to fill gaps
                            String updateQuery = "UPDATE final SET id = id - 1 WHERE id > ?";
                            try (PreparedStatement updateStmt = (PreparedStatement) con.prepareStatement(updateQuery)) {
                                updateStmt.setString(1, Num); // Update IDs for records greater than the deleted one
                                updateStmt.executeUpdate();
                            }

                            // Step 3: Reload the table to reflect the deletion
                            Get();
                            id_auto();// Assuming Get() reloads the table data
                        } else {
                            JOptionPane.showMessageDialog(null, "No record found with the provided ID.");
                        }
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Contact.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Error occurred while deleting the record: " + ex.getMessage());
                }
            }
        }


    }//GEN-LAST:event_deleteActionPerformed

    private void numActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numActionPerformed
        // Define the query for counting records in the 'final' table

    }//GEN-LAST:event_numActionPerformed

    private void sKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sKeyReleased
        // TODO add your handling code here:
        // Assuming 's' is your search text field

        s.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSearchResults();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSearchResults();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSearchResults();
            }

            private void updateSearchResults() {
                String searchText = s.getText().trim(); // Trim whitespace

                // Validate search text
                if (searchText == null || searchText.isEmpty()) {
                    // If search is empty, show all rows and clear text fields
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
                    table.setRowSorter(sorter);
                    sorter.setRowFilter(null);

                    // Clear search bar and associated text fields
                    num.setText("");
                    phone.setText("");
                    name.setText("");
                    return; // Exit early since no further filtering is needed
                }

                // Filter the table rows based on search text
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
                table.setRowSorter(sorter);

                try {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText)); // Case-insensitive filtering
                } catch (PatternSyntaxException ex) {
                    sorter.setRowFilter(null); // Clear filter on invalid regex
                    System.err.println("Invalid search text: " + ex.getMessage());
                }

                // Automatically populate text fields if a match is found
                int rowCount = table.getRowCount();
                if (rowCount > 0) {
                    int selectedRow = table.convertRowIndexToModel(0); // Ensure correct model row index
                    String selectedNum = model.getValueAt(selectedRow, 0).toString();
                    String selectedPhone = model.getValueAt(selectedRow, 1).toString();
                    String selectedName = model.getValueAt(selectedRow, 2).toString();

                    num.setText(selectedNum);
                    phone.setText(selectedPhone);
                    name.setText(selectedName);
                } else {
                    // Clear text fields if no match is found
                    num.setText("");
                    phone.setText("");
                    name.setText("");
                }
            }
        });
    }//GEN-LAST:event_sKeyReleased

    private void sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sActionPerformed

    private void phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneActionPerformed


    }//GEN-LAST:event_phoneActionPerformed

    private void cbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cbActionPerformed

    private void cbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbItemStateChanged

    }//GEN-LAST:event_cbItemStateChanged

    private void phoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneKeyTyped
        // TODO add your handling code here:
        phone.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                // Allow only digits, not letters or special characters
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    e.consume(); // Prevent the character from being typed
                    JOptionPane.showMessageDialog(null, "Only Numbers Are Allowed.");
                    phone.setText("");
                }

            }
        });

    }//GEN-LAST:event_phoneKeyTyped

    private void comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboItemStateChanged
        // TODO add your handling code here:
        combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterTableByFirstLetter();
            }
        });
    }

// Method to filter JTable by the first letter of names or show all rows sorted by ID
    private void filterTableByFirstLetter() {
        String selectedOption = combo.getSelectedItem().toString();

        // SQL query to filter by the selected first letter or show all rows sorted by ID
        String query;
        if (selectedOption.equalsIgnoreCase("Non") || selectedOption.equalsIgnoreCase("All")) {
            query = "SELECT * FROM final ORDER BY id ASC"; // Show all names sorted by ID
        } else {
            query = "SELECT * FROM final WHERE name LIKE ? ORDER BY name ASC"; // Filter by first letter
        }

        try (Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/phonebook", "root", "");
                PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query)) {

            if (!(selectedOption.equalsIgnoreCase("Non") || selectedOption.equalsIgnoreCase("All"))) {
                stmt.setString(1, selectedOption + "%"); // Add parameter for filtering by the first letter
            }

            ResultSet rs = stmt.executeQuery();

            // Clear and update the JTable with the filtered rows
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Clear existing rows

            while (rs.next()) {
                // Adjust these column names to match your database structure
                Object[] row = {
                    rs.getString("id"),
                    rs.getString("phone"),
                    rs.getString("name")
                };
                model.addRow(row);
            }

            // Notify if no matching rows are found
            if (model.getRowCount() == 0 && !(selectedOption.equalsIgnoreCase("Non") || selectedOption.equalsIgnoreCase("All"))) {
                JOptionPane.showMessageDialog(null, "No names found starting with " + selectedOption);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving data: " + ex.getMessage());
        }


    }//GEN-LAST:event_comboItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Get();
        id_auto();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void numKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_numKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Contact.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Contact.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Contact.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Contact.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Contact().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JComboBox<String> cb;
    private javax.swing.JComboBox<String> combo;
    private javax.swing.JButton delete;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField name;
    private javax.swing.JTextField num;
    private javax.swing.JTextField phone;
    private javax.swing.JTextField s;
    private javax.swing.JTable table;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables

    private void connect() {

    }

    public void Get() {
        Connection con = null;
        PreparedStatement selectStmt = null;
        ResultSet resultSet = null;

        try {
            // Step 1: Connect to the database
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/phonebook", "root", "");

            // Query to get all records sorted alphabetically by name
            String selectQuery = "SELECT id, phone, name FROM final ORDER BY name ASC";
            selectStmt = (PreparedStatement) con.prepareStatement(selectQuery);
            resultSet = selectStmt.executeQuery();

            // Clear existing rows from table or GUI before updating with new results
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Clear existing data from table

            // Step 2: Process result set and display records in sorted order
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String phone = resultSet.getString("phone");
                String name = resultSet.getString("name");

                // Add each row to the table
                model.addRow(new Object[]{id, phone, name});
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Contact.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (selectStmt != null) {
                    selectStmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Contact.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void prepareStatement(String delete_from_phones_where_no) {

    }

    private void filter(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static class JFrane {

        public JFrane() {
        }
    }

    private static class JOpptionPane {

        private static int showMessageDialog(JFrame frame, String are_You_Sure_You_Want_To_Delete_This, int YES_NO_OPTION) {
            return 0;
        }

        public JOpptionPane() {
        }
    }

}
