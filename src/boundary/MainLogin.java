/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import businessLogic.DBManager;
import entity.Artist;
import businessLogic.SessionsInTheRoom;
import businessLogic.WindowManager;
import entity.Freelancer;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author Shai Gutman
 */
public class MainLogin extends javax.swing.JFrame {

    /**
     * Creates new form MainLogin
     */
    public MainLogin() {
        initComponents();
        setVisible(true);
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

        newAccountFrame = new javax.swing.JInternalFrame();
        yearBox = new javax.swing.JComboBox<>();
        monthBox = new javax.swing.JComboBox<>();
        dayBox = new javax.swing.JComboBox<>();
        registerButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        birthdayLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        nicknameLabel = new javax.swing.JLabel();
        nicknameField = new javax.swing.JTextField();
        lastnameLabel = new javax.swing.JLabel();
        lastnameField = new javax.swing.JTextField();
        firstnameLabel = new javax.swing.JLabel();
        passwordLabel1 = new javax.swing.JLabel();
        firstnameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        uploadButton = new javax.swing.JButton();
        profileField = new javax.swing.JTextField();
        profileLabel = new javax.swing.JLabel();
        internalWallpaper = new javax.swing.JLabel();
        exitButton = new javax.swing.JButton();
        usernameArea = new javax.swing.JTextField();
        passwordArea = new javax.swing.JTextField();
        loginBtn = new javax.swing.JButton();
        createUserBtn = new javax.swing.JButton();
        wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Session in the room");
        setMaximumSize(new java.awt.Dimension(1200, 700));
        setMinimumSize(new java.awt.Dimension(1200, 700));
        setPreferredSize(new java.awt.Dimension(1205, 725));
        setResizable(false);
        getContentPane().setLayout(null);

        newAccountFrame.setBorder(null);
        newAccountFrame.setTitle("Create new account");
        newAccountFrame.setMaximumSize(new java.awt.Dimension(525, 400));
        newAccountFrame.setMinimumSize(new java.awt.Dimension(525, 400));
        newAccountFrame.setNormalBounds(new java.awt.Rectangle(325, 150, 545, 370));
        newAccountFrame.setPreferredSize(new java.awt.Dimension(525, 400));
        newAccountFrame.setVisible(false);
        newAccountFrame.getContentPane().setLayout(null);

        Date today = new Date(new java.util.Date().getTime());
        int begin = today.getYear()-100;
        String[] years =  new String[100];
        for (int i = 0; i < 100; i++)
        years[i] = ""+((begin++)+1900);
        yearBox.setModel(new javax.swing.DefaultComboBoxModel<>(years));
        newAccountFrame.getContentPane().add(yearBox);
        yearBox.setBounds(330, 270, 70, 25);

        String[] months =  new String[12];
        for (int i = 0; i < 12; i++)
        months[i] = ""+(i+1);
        monthBox.setModel(new javax.swing.DefaultComboBoxModel<>(months));
        newAccountFrame.getContentPane().add(monthBox);
        monthBox.setBounds(250, 270, 70, 25);

        String[] days =  new String[31];
        for (int i = 0; i < 31; i++)
        days[i] = ""+(i+1);
        dayBox.setModel(new javax.swing.DefaultComboBoxModel<>(days));
        newAccountFrame.getContentPane().add(dayBox);
        dayBox.setBounds(170, 270, 70, 25);

        registerButton.setText("Register");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });
        newAccountFrame.getContentPane().add(registerButton);
        registerButton.setBounds(300, 320, 90, 26);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        newAccountFrame.getContentPane().add(cancelButton);
        cancelButton.setBounds(410, 320, 80, 26);

        birthdayLabel.setText("Birthday:");
        newAccountFrame.getContentPane().add(birthdayLabel);
        birthdayLabel.setBounds(90, 280, 90, 16);

        emailLabel.setText("Email:");
        newAccountFrame.getContentPane().add(emailLabel);
        emailLabel.setBounds(90, 240, 90, 16);

        emailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailFieldActionPerformed(evt);
            }
        });
        newAccountFrame.getContentPane().add(emailField);
        emailField.setBounds(170, 240, 150, 20);

        nicknameLabel.setText("Stage Name:");
        newAccountFrame.getContentPane().add(nicknameLabel);
        nicknameLabel.setBounds(90, 180, 90, 16);

        nicknameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nicknameFieldActionPerformed(evt);
            }
        });
        newAccountFrame.getContentPane().add(nicknameField);
        nicknameField.setBounds(170, 180, 90, 20);

        lastnameLabel.setText("Last Name:");
        newAccountFrame.getContentPane().add(lastnameLabel);
        lastnameLabel.setBounds(280, 210, 90, 16);

        lastnameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastnameFieldActionPerformed(evt);
            }
        });
        newAccountFrame.getContentPane().add(lastnameField);
        lastnameField.setBounds(360, 210, 90, 20);

        firstnameLabel.setText("First Name:");
        newAccountFrame.getContentPane().add(firstnameLabel);
        firstnameLabel.setBounds(90, 210, 90, 16);

        passwordLabel1.setText("Please upload a profile picture:");
        newAccountFrame.getContentPane().add(passwordLabel1);
        passwordLabel1.setBounds(250, 90, 200, 16);

        firstnameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstnameFieldActionPerformed(evt);
            }
        });
        newAccountFrame.getContentPane().add(firstnameField);
        firstnameField.setBounds(170, 210, 90, 20);

        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });
        newAccountFrame.getContentPane().add(passwordField);
        passwordField.setBounds(330, 60, 120, 20);

        passwordLabel.setText("Password:");
        newAccountFrame.getContentPane().add(passwordLabel);
        passwordLabel.setBounds(250, 60, 90, 16);

        usernameLabel.setText("UUID:");
        newAccountFrame.getContentPane().add(usernameLabel);
        usernameLabel.setBounds(250, 30, 90, 16);

        usernameField.setEditable(false);
        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });
        usernameField.setText(UUID.randomUUID().toString().substring(0, 7));
        newAccountFrame.getContentPane().add(usernameField);
        usernameField.setBounds(330, 30, 120, 20);

        uploadButton.setText("Upload");
        uploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadButtonActionPerformed(evt);
            }
        });
        newAccountFrame.getContentPane().add(uploadButton);
        uploadButton.setBounds(250, 135, 80, 26);

        profileField.setEditable(false);
        newAccountFrame.getContentPane().add(profileField);
        profileField.setBounds(250, 110, 200, 20);

        profileLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/defaultProfile.png"))); // NOI18N
        newAccountFrame.getContentPane().add(profileLabel);
        profileLabel.setBounds(90, 30, 139, 131);

        internalWallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/container2.png"))); // NOI18N
        newAccountFrame.getContentPane().add(internalWallpaper);
        internalWallpaper.setBounds(0, 0, 545, 370);

        getContentPane().add(newAccountFrame);
        newAccountFrame.setBounds(325, 150, 545, 370);

        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/exitBtn.png"))); // NOI18N
        exitButton.setBorder(null);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(exitButton);
        exitButton.setBounds(1070, 610, 130, 40);

        usernameArea.setBorder(null);
        usernameArea.setOpaque(false);
        usernameArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usernameAreaKeyPressed(evt);
            }
        });
        getContentPane().add(usernameArea);
        usernameArea.setBounds(245, 340, 280, 30);

        passwordArea.setBorder(null);
        passwordArea.setOpaque(false);
        passwordArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordAreaKeyPressed(evt);
            }
        });
        getContentPane().add(passwordArea);
        passwordArea.setBounds(245, 390, 280, 30);

        loginBtn.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        loginBtn.setForeground(new java.awt.Color(255, 255, 255));
        loginBtn.setBorder(null);
        loginBtn.setContentAreaFilled(false);
        loginBtn.setLabel("Login");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });
        getContentPane().add(loginBtn);
        loginBtn.setBounds(213, 440, 310, 40);

        createUserBtn.setForeground(new java.awt.Color(51, 51, 51));
        createUserBtn.setText("Create new account");
        createUserBtn.setBorder(null);
        createUserBtn.setBorderPainted(false);
        createUserBtn.setContentAreaFilled(false);
        createUserBtn.setFocusPainted(false);
        createUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createUserBtnActionPerformed(evt);
            }
        });
        getContentPane().add(createUserBtn);
        createUserBtn.setBounds(200, 500, 140, 16);

        wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/SessionLogin.png"))); // NOI18N
        getContentPane().add(wallpaper);
        wallpaper.setBounds(0, 0, 1200, 700);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(1);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void usernameAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameAreaKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            openMain();
        }
    }//GEN-LAST:event_usernameAreaKeyPressed

    private void passwordAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordAreaKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            openMain();
        }
    }//GEN-LAST:event_passwordAreaKeyPressed

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        openMain();
    }//GEN-LAST:event_loginBtnActionPerformed

    private void createUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createUserBtnActionPerformed
        BasicInternalFrameUI mashu = (BasicInternalFrameUI)newAccountFrame.getUI();
        mashu.setNorthPane(null);
        newAccountFrame.setBorder(null);
        newAccountFrame.setVisible(true);
    }//GEN-LAST:event_createUserBtnActionPerformed

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        String username = usernameField.getText();
        String nickname = nicknameField.getText();
        String firstname = firstnameField.getText();
        String lastname = lastnameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        Integer birthYear = Integer.parseInt(String.valueOf(yearBox.getSelectedItem()))-1900;
        Integer birthMonth = Integer.parseInt(String.valueOf(monthBox.getSelectedItem()))-1;
        Integer birthDay = Integer.parseInt(String.valueOf(dayBox.getSelectedItem()));
        Date birthdate = new Date(birthYear, birthMonth, birthDay);
        Timestamp ts = new Timestamp(birthdate.getTime());
        String qry = "INSERT INTO tblFreelancer (FreelancerID, firstName, lastName, birthDate, eMail, stageName, Photo, password)"
        + "VALUES('"+username+"','"+firstname+"','"+lastname+"','"+ts+"',\""+email+"\",'"+nickname+"',\""+profileLabel.getIcon()+"\",'"+password+"')";
        if (DBManager.insert(qry) > -1) {
            JOptionPane.showMessageDialog(newAccountFrame,
                "Congratulations your account was created successfully!",
                "Account was created",
                JOptionPane.INFORMATION_MESSAGE);
            clearUserForm();
            newAccountFrame.setVisible(false);
        }
    }//GEN-LAST:event_registerButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        newAccountFrame.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void emailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailFieldActionPerformed

    private void nicknameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nicknameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nicknameFieldActionPerformed

    private void lastnameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastnameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastnameFieldActionPerformed

    private void firstnameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstnameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstnameFieldActionPerformed

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldActionPerformed

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldActionPerformed

    private void uploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadButtonActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File chosen = chooser.getSelectedFile();
        if (chosen == null)
        return;
        String filename = chosen.getAbsolutePath();
        BufferedImage image = null;
        try {
            image = ImageIO.read(chosen);
        } catch (IOException ex) {
            Logger.getLogger(MainLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        profileField.setText(filename);
        profileLabel.setIcon(new ImageIcon(resize(image,139,131)));
    }//GEN-LAST:event_uploadButtonActionPerformed

    public static BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }
    
    public void openMain() {
        if (usernameArea.getText().equals("") || passwordArea.getText().equals("")) {
            JOptionPane.showMessageDialog(this,
                "You must enter username & password",
                "Login error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (usernameArea.getText().equals("Admin") && passwordArea.getText().equals("Admin")) {
            WindowManager.setUser(2, null);
            this.dispose();
            WindowManager.startMain();
            return;
        } else {
            ResultSet artists = SessionsInTheRoom.getDB().query("SELECT tblArtist.*\n" +
                                                               "FROM tblArtist\n" +
                                                               "WHERE tblArtist.eMail=\""+usernameArea.getText()+"\"");
            ResultSet freelancers = SessionsInTheRoom.getDB().query("SELECT tblFreelancer.*\n" +
                                                               "FROM tblFreelancer\n" +
                                                               "WHERE tblFreelancer.eMail=\""+usernameArea.getText()+"\"");
            try {
                while (artists.next()) {
                    if (artists.getString(4).equals(passwordArea.getText())) {
                        WindowManager.setUser(1, new Artist(artists.getString(1), artists.getString(2), artists.getString(3), 
                                                           artists.getString(4)));
                        this.dispose();
                        WindowManager.startMain();
                        return;
                    }
                }
                while (freelancers.next()) {
                    if (freelancers.getString(8).equals(passwordArea.getText())) {
                        WindowManager.setUser(3, new Freelancer(freelancers.getString(1), freelancers.getString(2), freelancers.getString(3), 
                                                        freelancers.getDate(4), freelancers.getString(5), freelancers.getString(6), freelancers.getString(8)));
                        this.dispose();
                        WindowManager.startMain();
                        return;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        JOptionPane.showMessageDialog(this,
                "Incorrect username or password",
                "Login error",
                JOptionPane.ERROR_MESSAGE);
    }
    
    public void extLogin(String username, String password) {
        if (username == null || password == null) {
            return;
        }
        usernameArea.setText(username);
        passwordArea.setText(password);
    }
    
    private void clearUserForm() {
        usernameField.setText("");
        nicknameField.setText("");
        firstnameField.setText("");
        lastnameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        yearBox.setSelectedIndex(0);
        monthBox.setSelectedIndex(0);
        dayBox.setSelectedIndex(0);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel birthdayLabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton createUserBtn;
    private javax.swing.JComboBox<String> dayBox;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JButton exitButton;
    private javax.swing.JTextField firstnameField;
    private javax.swing.JLabel firstnameLabel;
    private javax.swing.JLabel internalWallpaper;
    private javax.swing.JTextField lastnameField;
    private javax.swing.JLabel lastnameLabel;
    private javax.swing.JButton loginBtn;
    private javax.swing.JComboBox<String> monthBox;
    private javax.swing.JInternalFrame newAccountFrame;
    private javax.swing.JTextField nicknameField;
    private javax.swing.JLabel nicknameLabel;
    private javax.swing.JTextField passwordArea;
    private javax.swing.JTextField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel passwordLabel1;
    private javax.swing.JTextField profileField;
    private javax.swing.JLabel profileLabel;
    private javax.swing.JButton registerButton;
    private javax.swing.JButton uploadButton;
    private javax.swing.JTextField usernameArea;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JLabel wallpaper;
    private javax.swing.JComboBox<String> yearBox;
    // End of variables declaration//GEN-END:variables
}
