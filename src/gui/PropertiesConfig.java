package gui;

import static gui.MainFrame.prop1;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

public class PropertiesConfig extends javax.swing.JFrame {

    public PropertiesConfig() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/gear.png")));
    }

    static String prop1 = "";
    static String prop_1 = "";
    static String prop2 = "";
    static String prop_2 = "";
    static String prop3 = "";
    static String prop_3 = "";
    static String prop4 = "";
    static String prop_4 = "";
    static String str = "";

    MainFrame mf;

    public void getDefaultProp(String p1, String p_1, String p2, String p_2, String p3, String p_3, String p4, String p_4, String st, MainFrame mf) {

        this.mf = mf;

        prop1 = p1;
        prop_1 = p_1;
        prop2 = p2;
        prop_2 = p_2;
        prop3 = p3;
        prop_3 = p_3;
        prop4 = p4;
        prop_4 = p_4;
        str = st;

        jTextField2.setText(p1);
        jTextField1.setText(p_1);
        jTextField3.setText(p2);
        jTextField4.setText(p_2);
        jTextField6.setText(p3);
        jTextField5.setText(p_3);
        jTextField7.setText(p4);
        jTextField8.setText(p_4);
        jTextField9.setText(st);
    }

    public void restoreDefault() {
        
        jTextField2.setText("mail.imap.host");
        jTextField1.setText("imap.gmail.com");
        jTextField3.setText("mail.imap.port");
        jTextField4.setText("993");
        jTextField6.setText("mail.imap.connectiontimeout");
        jTextField5.setText("5000");
        jTextField7.setText("mail.imap.timeout");
        jTextField8.setText("5000");
        jTextField9.setText("imaps");
    }

    public void updateProperties() {
        int cnf = JOptionPane.showConfirmDialog(this, "Do you want to change Properties.?", "CONFIRMATION", JOptionPane.YES_NO_OPTION);
        if (cnf == JOptionPane.YES_NO_OPTION) {
            mf.setCustomProperties(jTextField2.getText(), jTextField1.getText(), jTextField3.getText(), jTextField4.getText(), jTextField6.getText(), jTextField5.getText(), jTextField7.getText(), jTextField8.getText(), jTextField9.getText());
            this.dispose();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Properties");

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 180, 30));
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 30));
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 260, 30));
        jPanel1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 100, 30));
        jPanel1.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, 100, 30));
        jPanel1.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 260, 30));
        jPanel1.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 260, 30));
        jPanel1.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, 100, 30));
        jPanel1.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 180, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/updated.png"))); // NOI18N
        jButton1.setText("  Update");
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, -1, 40));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/restore.png"))); // NOI18N
        jButton2.setText("  Default");
        jButton2.setBorderPainted(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setFocusPainted(false);
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, -1, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        int cnf = JOptionPane.showConfirmDialog(this, "Do you want to restore default Properties.?", "CONFIRMATION", JOptionPane.YES_NO_OPTION);
        if (cnf == JOptionPane.YES_OPTION) {
            restoreDefault();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        updateProperties();
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PropertiesConfig.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PropertiesConfig.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PropertiesConfig.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PropertiesConfig.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PropertiesConfig().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
