
import com.mysql.jdbc.Connection;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Update2 extends javax.swing.JFrame {

   
    public Update2() throws SQLException {
        initComponents();
        Connect();

    }
    Connection con;
    PreparedStatement pst;
     
Update2(String msg1, String msg2, String msg3) {
        initComponents();
        n.setText(msg1);
        p.setText(msg2);
        nn.setText(msg3);
     }
 public void Connect() throws SQLException{
       try {
          Class.forName("com.mysql.jdbc.Driver");   
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/phonebook", "root", "");
        System.out.println("Database connected successfully.");
    } catch (ClassNotFoundException ex) {
        JOptionPane.showMessageDialog(null, "JDBC Driver not found: " + ex.getMessage());
        ex.printStackTrace();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Failed to connect to the database: " + ex.getMessage());
        ex.printStackTrace();
    }
}

private void verifyConnection() {
   
       try {

            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/phonebook", "root", "");
            System.out.println("Connection established successfully.");
    }       catch (SQLException ex) {
                Logger.getLogger(Contact.class.getName()).log(Level.SEVERE, null, ex);
            }
 }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        p = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        nn = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        n = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("PHONE NUMBER:");

        p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pActionPerformed(evt);
            }
        });
        p.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("NAME:");

        jButton1.setText("Update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("ID:");

        n.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(nn, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(p, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(n, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jButton1)
                        .addGap(86, 86, 86)
                        .addComponent(jButton2)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(n, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(121, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
        String Id = n.getText();  // Text from the "num" field
        String Phone = p.getText();  // Text from the "phone" field
        String Name = nn.getText();  // Text from the "name" field

// Check if any of the fields are empty or null
if (Id.isEmpty() || Phone.isEmpty() || Name.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Please fill all fields before updating.");
    return;  // Exit if any field is empty
}

try {
    

if (con != null) {
    JOptionPane.showMessageDialog(null, "Database connection is not established.");
    return;
}

if (n.getText().isEmpty() || p.getText().isEmpty() || nn.getText().isEmpty()) {
    JOptionPane.showMessageDialog(null, "All fields must be filled.");
    return;
}
if (con == null) {
    Connect(); // Call your method to establish a connection
    if (con == null) {
        JOptionPane.showMessageDialog(null, "Failed to establish a database connection.");
        return;
    }
}
if (n == null || p == null || nn == null) {
    JOptionPane.showMessageDialog(null, "UI components are not properly initialized.");
    return;
}
    // Prepare the SQL statement
    pst = con.prepareStatement("UPDATE final SET phone=?, name=? WHERE id=?");

    // Set the values from text fields
    pst.setString(1, Phone);
    pst.setString(2, Name);
    pst.setString(3, Id);

    // Execute the update
    pst.executeUpdate();

    // Inform the user
    JOptionPane.showMessageDialog(this, "Contact Successfully Updated!!");

    // Reset the form or return to previous screen
    Contact w = new Contact();
    this.hide(); 
    w.setVisible(true);  

    // Refresh the data
    Connect(); 

} catch (SQLException ex) {
    Logger.getLogger(Update2.class.getName()).log(Level.SEVERE, null, ex);
    JOptionPane.showMessageDialog(this, "Error updating the contact.");
}

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Contact w = new Contact();
        this.hide();
        w.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pActionPerformed

    private void pKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pKeyTyped
        // TODO add your handling code here:
        p.addKeyListener(new KeyAdapter(){
        public void keyTyped(KeyEvent e){
        char c = e.getKeyChar();
        
        if(!Character.isDigit(c)){
        e.consume();
        JOptionPane.showMessageDialog(null, "Only Numbers Are Allowed.");
        p.setText("");
        }
        }       
        });
    }//GEN-LAST:event_pKeyTyped

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
            java.util.logging.Logger.getLogger(Update2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Update2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Update2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Update2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Update2().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Update2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField n;
    private javax.swing.JTextField nn;
    private javax.swing.JTextField p;
    // End of variables declaration//GEN-END:variables

   

}
