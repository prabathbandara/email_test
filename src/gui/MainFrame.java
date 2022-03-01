/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainFrame extends javax.swing.JFrame {

    public MainFrame() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/gmail.png")));
        jTextArea1.setEditable(false);
        this.setResizable(false);
        setDefauldProperties();
    }

    public void readMails() {
        if (!emailTxt.getText().isEmpty()) {
            if (!passwordTxt.getText().isEmpty()) {

                int cnf = JOptionPane.showConfirmDialog(this, "Do you want to read emails.?", "CONFIRMATION", JOptionPane.YES_NO_OPTION);
                if (cnf == JOptionPane.YES_OPTION) {
                    check(this, "imap.gmail.com", emailTxt.getText(), passwordTxt.getText());
                }

            } else {
                JOptionPane.showMessageDialog(this, "Please Enter Password.!", "WARNING", JOptionPane.WARNING_MESSAGE);
                passwordTxt.grabFocus();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please Enter Valid Email.!", "WARNING", JOptionPane.WARNING_MESSAGE);
            emailTxt.grabFocus();
        }
    }

    public void setMailCount(int count) {
        jLabel5.setText(count + " Emails Found..");
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

    public void setDefauldProperties() {

        prop1 = "mail.imap.host";
        prop_1 = "imap.gmail.com";
        prop2 = "mail.imap.port";
        prop_2 = "993";
        prop3 = "mail.imap.connectiontimeout";
        prop_3 = "5000";
        prop4 = "mail.imap.timeout";
        prop_4 = "5000";
        str = "imaps";

    }

    public static void setCustomProperties(String prop1, String prop_1, String prop2, String prop_2, String prop3, String prop_3, String prop4, String prop_4, String str) {

        MainFrame.prop1 = prop1;
        MainFrame.prop_1 = prop_1;
        MainFrame.prop2 = prop2;
        MainFrame.prop_2 = prop_2;
        MainFrame.prop3 = prop3;
        MainFrame.prop_3 = prop_3;
        MainFrame.prop4 = prop4;
        MainFrame.prop_4 = prop_4;
        MainFrame.str = str;

    }

    public void launchProperties() {
        PropertiesConfig pc = new PropertiesConfig();
        pc.getDefaultProp(prop1, prop_1, prop2, prop_2, prop3, prop_3, prop4, prop_4, str, this);
        pc.setVisible(true);
    }

    public void check(MainFrame mf, String host, String user, String password) {
        try {
            new Thread() {
                @Override
                public void run() {
                    jTextArea1.setText(null);
                    jLabel6.setText("Reading.. Please Wait.!");
                    setFieldsStatus(false);

                    Set<String> emlsSt = new HashSet<>();

                    try {

                        //create properties field
                        Properties properties = new Properties();

//                        properties.setProperty("mail.imap.host", "imap.gmail.com");
//                        properties.setProperty("mail.imap.port", "993");
//                        properties.setProperty("mail.imap.connectiontimeout", "5000");
//                        properties.setProperty("mail.imap.timeout", "5000");

                        properties.setProperty(prop1, prop_1);
                        properties.setProperty(prop2, prop_2);
                        properties.setProperty(prop3, prop_3);
                        properties.setProperty(prop4, prop_4);

                        Session emailSession = Session.getDefaultInstance(properties);

                        Store store = emailSession.getStore(str);

                        store.connect(host, user, password);

                        Folder emailFolder = store.getFolder("INBOX");
                        emailFolder.open(Folder.READ_ONLY);

                        Message[] messages = emailFolder.getMessages();
                        System.out.println("messages.length---" + messages.length);

                        jLabel5.setText(messages.length + " Emails Found.!");

                        String emlFnl = "";

                        for (int i = 0, n = messages.length; i < n; i++) {

                            Message message = messages[i];

                            jLabel6.setText("Reading.. Please Wait.! - " + i);

                            if (message.getFrom()[0].toString().contains("<")) {
                                String[] splEml = message.getFrom()[0].toString().split("<");
                                emlFnl = splEml[1].replace(">", "");
                            } else {
                                emlFnl = message.getFrom()[0].toString();
                            }

                            emlsSt.add(emlFnl);

                            jTextArea1.setText(jTextArea1.getText() + emlFnl + "\n");
                            
//                            System.out.println("---------------------------------");
//                emailTxt += message.getFrom()[0] + "\n";
//                System.out.println("Subject: " + message.getSubject());
//                            System.out.println("From: " + message.getFrom()[0]);
//                System.out.println("Date: " + message.getSentDate());
                        }

                        emailFolder.close(false);
                        store.close();

                        jLabel6.setText("Removing Duplicates.!");

                        mf.setMailCount(emlsSt.size());
                        jTextArea1.setText(null);
                        emlsSt.forEach((eml) -> {
                            jTextArea1.setText(jTextArea1.getText() + eml + "\n");
                            try {
                                this.sleep(200);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        });

                        jLabel6.setText("Completed.!");

                        int cnf = JOptionPane.showConfirmDialog(MainFrame.this, "Reading Completed.! \nDo you want to export emails.?", "CONFIRMATION", JOptionPane.YES_NO_OPTION);
                        if (cnf == JOptionPane.YES_OPTION) {
                            exportTxt();
                        }

                        setFieldsStatus(true);

                    } catch (Exception e) {
                        e.printStackTrace();
                        jLabel4.setText(e.getMessage());
                        jLabel6.setText("Stoped.! Error Found.!");
                        setFieldsStatus(true);
                    }

                }

            }.start();

        } catch (Exception e) {
            e.printStackTrace();
            jLabel4.setText(e.getMessage());
            jLabel6.setText("Stoped.! Error Found.!");
            setFieldsStatus(true);
        }

    }

    public void setLabelsClear() {
        jLabel4.setText(null);
        jLabel6.setText(null);
    }

    public void setFieldsStatus(boolean stts) {
        emailTxt.setEditable(stts);
        passwordTxt.setEditable(stts);
        jButton2.setEnabled(stts);
        jButton1.setEnabled(stts);
        jButton3.setEnabled(stts);
    }

    public void clearAll() {
        int cnf = JOptionPane.showConfirmDialog(this, "Do you want to clear all.?", "CONFIRMATION", JOptionPane.YES_OPTION);
        if (cnf == JOptionPane.YES_OPTION) {

            emailTxt.setText(null);
            passwordTxt.setText(null);
            jTextArea1.setText(null);
            jLabel5.setText(null);
            setLabelsClear();
        }
    }

    public void exportTxt() {
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Save As");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter(".txt", "txt");
        jfc.setFileFilter(fnef);

        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String dte = sdf.format(new Date());

        jfc.setSelectedFile(new File("email_backup_" + dte + ".txt"));
        int excelChooser = jfc.showSaveDialog(this);

        if (excelChooser == JFileChooser.APPROVE_OPTION) {

            File fileToSave = jfc.getSelectedFile();

            try {
                FileWriter fw = new FileWriter(fileToSave);
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write(jTextArea1.getText());

                JOptionPane.showMessageDialog(MainFrame.this, "Export Completed.!", "INFO", JOptionPane.INFORMATION_MESSAGE);
                bw.close();
                fw.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        emailTxt = new javax.swing.JTextField();
        passwordTxt = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Email Reader");

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Email");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel2.setText("Password");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        emailTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                emailTxtMouseClicked(evt);
            }
        });
        emailTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTxtActionPerformed(evt);
            }
        });
        jPanel2.add(emailTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 400, 30));

        passwordTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                passwordTxtMouseClicked(evt);
            }
        });
        passwordTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTxtActionPerformed(evt);
            }
        });
        jPanel2.add(passwordTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 400, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/eraser.png"))); // NOI18N
        jButton1.setText("  Clear");
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, 100, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 102));
        jLabel3.setText("Email Reader");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 400, 10));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/read.png"))); // NOI18N
        jButton2.setText("  Read");
        jButton2.setBorderPainted(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setFocusPainted(false);
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 110, 40));

        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 360, 20));
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 400, 20));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 400, 260));

        jLabel6.setForeground(new java.awt.Color(0, 0, 204));
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 170, 20));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/export.png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setFocusPainted(false);
        jButton3.setFocusable(false);
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
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 40, 50));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/gear.png"))); // NOI18N
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setFocusPainted(false);
        jButton4.setFocusable(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 40, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        clearAll();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        readMails();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void emailTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTxtActionPerformed

    }//GEN-LAST:event_emailTxtActionPerformed

    private void passwordTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordTxtActionPerformed

    }//GEN-LAST:event_passwordTxtActionPerformed

    private void emailTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emailTxtMouseClicked
        setLabelsClear();
    }//GEN-LAST:event_emailTxtMouseClicked

    private void passwordTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordTxtMouseClicked
        setLabelsClear();
    }//GEN-LAST:event_passwordTxtMouseClicked

    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseEntered

    }//GEN-LAST:event_jButton3MouseEntered

    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseExited

    }//GEN-LAST:event_jButton3MouseExited

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if (!jTextArea1.getText().isEmpty()) {
            exportTxt();
        } else {
            JOptionPane.showMessageDialog(this, "No emails found.!", "WARNING", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        launchProperties();
    }//GEN-LAST:event_jButton4ActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField emailTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPasswordField passwordTxt;
    // End of variables declaration//GEN-END:variables
}
