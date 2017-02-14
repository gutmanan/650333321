package init;

import core.Artist;
import gui.MainGui;
import gui.MainLogin;
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class WindowManager {

    private static int authLogged;
    protected static Artist tmpArtist = null;
    protected static JLabel welcome = null;

    //Window Management
    protected static JPanel currentWindow = null;
    protected static JPanel lastWindow = null;
    protected static JFrame mainFrame;
    protected static JFrame loginFrame;

    //======================================= Main ==========================================
    public static void openLogin() throws SQLException {
         loginFrame = new MainLogin();
    }
    public static void startMain(){
        mainFrame = new MainGui();
        welcome = new JLabel();
        welcome.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        welcome.setForeground(new java.awt.Color(255, 255, 255));
        mainFrame.getContentPane().add(welcome, mainFrame.getContentPane().countComponents()-1);
        welcome.setBounds(440, 30, 500, 50);
        if (getTmpArtist() != null)
            welcome.setText("Welcome "+getTmpArtist().getStageName());
        else
            welcome.setText("Welcome");
    }
    public static void closeMain() throws SQLException {
        mainFrame.dispose();
        loginFrame = new MainLogin();
    }
    public static void setCurrentWindow(JPanel panel) {
        currentWindow = panel;
    }
    public static JPanel getCurrentWindow() {
        return currentWindow;
    }
    public static void openWin(JPanel panel) {
        if (panel == null) {
            return;
        }
        if (getCurrentWindow() == null) {
            setCurrentWindow(panel);
        } else {
            if (panel == getCurrentWindow()) {
                panel.setVisible(true);
                return;
            } else {
                getCurrentWindow().setVisible(false);
                setLastWindow(getCurrentWindow());
                setCurrentWindow(panel);
            }
        }
        getCurrentWindow().setBounds(280, 90, 810, 595);
        mainFrame.getContentPane().add(getCurrentWindow(), mainFrame.getContentPane().countComponents()-1);
        mainFrame.getContentPane().setVisible(true);
        panel.setVisible(true);
        WindowManager.update();
        return;
    }

    public static void returnWindow() {
        if (getCurrentWindow() == null || getLastWindow() == null) {
            return;
        }
        getCurrentWindow().hide();
        JPanel tmp = getCurrentWindow();
        setCurrentWindow(getLastWindow());
        setLastWindow(tmp);

        WindowManager.update();
    }

    public static void setLastWindow(JPanel panel) {
        lastWindow = panel;
    }

    public static JPanel getLastWindow() {
        return lastWindow;
    }

    public static String getAuthType() {
        String toReturn = null;

        switch (authLogged) {
            case 1:
                toReturn = "Artist";
                break;
            case 2:
                toReturn = "";
                break;
            default:
                toReturn = "ERROR";
                break;
        }
        return toReturn;
    }

    public static JFrame getMainFrame() {
        return mainFrame;
    }

    public static int getAuthValue() {
        return authLogged;
    }

    public static void setUser(int AuthType, Object user) {
        if (AuthType <= 0) {
            return;
        }
        /*if (user == null) {
            return;
        }*/
        authLogged = AuthType;
        switch (AuthType) {
            case 1:
                tmpArtist = (Artist)user;
                break;
            case 2:
                tmpArtist = null;
                break;
            default:
                System.err.println("ERROR");
                break;
        }
        return;
    }

    public static void setTmpArtist(Artist agent) {
        tmpArtist = agent;
    }
    
    public static Artist getTmpArtist() {
        return tmpArtist;
    }

    public static void clean() {
        authLogged = 0;
        currentWindow = null;
        welcome = null;
        tmpArtist = null;
    }

    public static void update() {
        if (getCurrentWindow() == null) {
            return;
        }
        getCurrentWindow().setVisible(false);
        getCurrentWindow().setVisible(true);
    }

    public static void update(JFrame frame) {
        frame.setVisible(false);
        frame.setVisible(true);
    }
}