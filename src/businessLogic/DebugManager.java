/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import boundary.MainLogin;
import java.awt.Color;

/**
 *
 * @author Shai Gutman
 */
public abstract class DebugManager extends javax.swing.JFrame {
    private static int objects = 0;
    /**
     * Creates new form debugManager
     */
    public DebugManager() {
        initComponents();
        SessionsInTheRoom.setDebug(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        xmlLabel1 = new javax.swing.JLabel();
        xmlLabel = new javax.swing.JLabel();
        xmlstatusLabel1 = new javax.swing.JLabel();
        xmlstatusLabel = new javax.swing.JLabel();
        objectsCounterLabel = new javax.swing.JLabel();
        counterLabel = new javax.swing.JLabel();
        freelancerBtn = new javax.swing.JButton();
        artistBtn = new javax.swing.JButton();
        adminBtn = new javax.swing.JButton();
        statusLabel = new javax.swing.JLabel();
        databaseLabel = new javax.swing.JLabel();
        quickLog = new javax.swing.JLabel();
        wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Debug window");
        setAlwaysOnTop(true);
        setFocusTraversalPolicyProvider(true);
        setPreferredSize(new java.awt.Dimension(305, 400));
        setResizable(false);
        getContentPane().setLayout(null);

        xmlLabel1.setForeground(new java.awt.Color(0, 0, 0));
        xmlLabel1.setText("XML export status:");
        getContentPane().add(xmlLabel1);
        xmlLabel1.setBounds(10, 200, 130, 16);

        xmlLabel.setForeground(new java.awt.Color(0, 0, 0));
        xmlLabel.setText("XML import status:");
        getContentPane().add(xmlLabel);
        xmlLabel.setBounds(10, 180, 130, 16);

        xmlstatusLabel1.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(xmlstatusLabel1);
        xmlstatusLabel1.setBounds(130, 200, 150, 16);

        xmlstatusLabel.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(xmlstatusLabel);
        xmlstatusLabel.setBounds(130, 180, 150, 16);

        objectsCounterLabel.setForeground(new java.awt.Color(0, 0, 0));
        objectsCounterLabel.setText("New objects:");
        getContentPane().add(objectsCounterLabel);
        objectsCounterLabel.setBounds(10, 220, 90, 16);

        counterLabel.setText(String.valueOf(objects));
        counterLabel.setForeground(Color.RED);
        getContentPane().add(counterLabel);
        counterLabel.setBounds(90, 220, 150, 16);

        freelancerBtn.setText("Freelancer");
        freelancerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                freelancerBtnActionPerformed(evt);
            }
        });
        getContentPane().add(freelancerBtn);
        freelancerBtn.setBounds(150, 130, 100, 20);

        artistBtn.setText("Artist");
        artistBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                artistBtnActionPerformed(evt);
            }
        });
        getContentPane().add(artistBtn);
        artistBtn.setBounds(80, 130, 70, 20);

        adminBtn.setText("Admin");
        adminBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminBtnActionPerformed(evt);
            }
        });
        getContentPane().add(adminBtn);
        adminBtn.setBounds(10, 130, 70, 20);

        statusLabel.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(statusLabel);
        statusLabel.setBounds(115, 160, 150, 16);

        databaseLabel.setForeground(new java.awt.Color(0, 0, 0));
        databaseLabel.setText("Database status:");
        getContentPane().add(databaseLabel);
        databaseLabel.setBounds(10, 160, 100, 16);

        quickLog.setForeground(new java.awt.Color(0, 0, 0));
        quickLog.setText("Quick login:");
        getContentPane().add(quickLog);
        quickLog.setBounds(10, 110, 80, 16);

        wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/debugContainer.png"))); // NOI18N
        getContentPane().add(wallpaper);
        wallpaper.setBounds(0, 0, 300, 400);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adminBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminBtnActionPerformed
        if (WindowManager.getLoginFrame() == null)
            return;
        if (WindowManager.getMainFrame() != null)
            WindowManager.closeMain();
        WindowManager.getLoginFrame().extLogin("Admin", "Admin");
        WindowManager.getLoginFrame().openMain();
    }//GEN-LAST:event_adminBtnActionPerformed

    private void artistBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_artistBtnActionPerformed
        if (WindowManager.getLoginFrame() == null)
            return;
        if (WindowManager.getMainFrame() != null)
            WindowManager.closeMain();
        WindowManager.getLoginFrame().extLogin("anb@gmail.com", "123");
        WindowManager.getLoginFrame().openMain();
    }//GEN-LAST:event_artistBtnActionPerformed

    private void freelancerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_freelancerBtnActionPerformed
        if (WindowManager.getLoginFrame() == null)
            return;
        if (WindowManager.getMainFrame() != null)
            WindowManager.closeMain();
        WindowManager.getLoginFrame().extLogin("avi@gmail.com", "123");
        WindowManager.getLoginFrame().openMain();
    }//GEN-LAST:event_freelancerBtnActionPerformed

    public static void setDatabaseStatus(Boolean flag) {
        if (flag) {
            statusLabel.setText("connected");
            statusLabel.setForeground(Color.green);
            return;
        }
        statusLabel.setText("not connected");
        statusLabel.setForeground(Color.red);
    }
    
    public static void setXMLStatus(Boolean flag) {
        if (flag) {
            xmlstatusLabel.setText("imported");
            xmlstatusLabel.setForeground(Color.green);
            return;
        }
        xmlstatusLabel.setText("not imported");
        xmlstatusLabel.setForeground(Color.red);
    }
    
    public static void setXML1Status(Boolean flag) {
        if (flag) {
            xmlstatusLabel1.setText("exported");
            xmlstatusLabel1.setForeground(Color.green);
            return;
        }
        xmlstatusLabel1.setText("not exported");
        xmlstatusLabel1.setForeground(Color.red);
    }
    
    public static void objectsPlusPlus() {
        objects++;
        counterLabel.setText(String.valueOf(objects));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JButton adminBtn;
    private static javax.swing.JButton artistBtn;
    private static javax.swing.JLabel counterLabel;
    private static javax.swing.JLabel databaseLabel;
    private static javax.swing.JButton freelancerBtn;
    private static javax.swing.JLabel objectsCounterLabel;
    private static javax.swing.JLabel quickLog;
    private static javax.swing.JLabel statusLabel;
    private static javax.swing.JLabel wallpaper;
    private static javax.swing.JLabel xmlLabel;
    private static javax.swing.JLabel xmlLabel1;
    private static javax.swing.JLabel xmlstatusLabel;
    private static javax.swing.JLabel xmlstatusLabel1;
    // End of variables declaration//GEN-END:variables
}
