package businessLogic;

import entity.Artist;
import boundary.MainGui;
import boundary.MainLogin;
import entity.Freelancer;
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.transaction.xa.XAResource;

public abstract class WindowManager {

    private static int authLogged;
    protected static Artist tmpArtist = null;
    protected static Freelancer tmpFreelancer = null;
    protected static JLabel welcome = null;

    //Window Management
    protected static JPanel currentWindow = null;
    protected static JPanel lastWindow = null;
    protected static JFrame mainFrame;
    protected static MainLogin loginFrame;

    //======================================= Main ==========================================
    public static void openLogin() {
        loginFrame = new MainLogin();
    }
    public static void startMain(){
        mainFrame = new MainGui();
        SessionsInTheRoom.getDM().setVisible(false);
        setWelcome();
    }
    public static void closeMain() {
        mainFrame.dispose();
        loginFrame = new MainLogin();
        clean();
        SessionsInTheRoom.getDM().setVisible(false);
    }
    public static void closeLogin() {
        loginFrame.dispose();
        mainFrame = new MainGui();
        SessionsInTheRoom.getDM().setVisible(false);
        setWelcome();
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
        getCurrentWindow().setBounds(208, 90, 850, 580);
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

    public static MainLogin getLoginFrame() {
        return loginFrame;
    }
    
    public static String getAuthType() {
        String toReturn = null;

        switch (authLogged) {
            case 1:
                toReturn = "Artist";
                break;
            case 2:
                toReturn = "Muza Representative";
                break;
            case 3:
                toReturn = "Freelancer";
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
                tmpFreelancer = null;
                break;
            case 3:
                tmpFreelancer = (Freelancer)user;
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

    public static Freelancer getTmpFreelancer() {
        return tmpFreelancer;
    }

    public static void clean() {
        authLogged = 0;
        currentWindow = null;
        welcome = null;
        tmpArtist = null;
        tmpFreelancer = null;
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
    
    private static void setWelcome() {
        welcome = new JLabel();
        welcome.setFont(new java.awt.Font("Dialog", 0, 36));
        welcome.setForeground(new java.awt.Color(255, 255, 255));
        mainFrame.getContentPane().add(welcome, mainFrame.getContentPane().countComponents()-1);
        welcome.setBounds(208, 30, 580, 50);
        if (getTmpArtist() != null)
            welcome.setText("Welcome "+getTmpArtist().getStageName());
        else if (getTmpFreelancer() != null)
            welcome.setText("Welcome "+getTmpFreelancer().getFreelancerName());
        else 
            welcome.setText("Welcome "+getAuthType());
    }
}
