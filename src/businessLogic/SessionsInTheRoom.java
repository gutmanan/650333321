package businessLogic;

import boundary.MainLogin;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

public class SessionsInTheRoom {

    private static DBManager DB = null;
    private static PDFManager PDF = null;
    private static XMLManager XML = null;
    private static DebugManager DM = null;
    private static String fileName = "SessionsInTheRoom";
    private static PrintStream logFile;
    public static void main(String[] args) {
        
        DM = new DebugManager() {};
        DB = new DBManager();
        PDF = new PDFManager();
        XML = new XMLManager("MuzaMusic_Shows");
        XML.importXML();
        WindowManager.openLogin();
    }

    public static void setDebug(JFrame frame) {
        JRootPane rootPane = frame.getRootPane();
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, InputEvent.CTRL_MASK), "myAction");
        rootPane.getActionMap().put("myAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (DM.isVisible()) {
                    DM.dispose();
                    return;
                }
                DM.setVisible(true);
            }
        });
    }
    
    public static DBManager getDB() {
        return DB;
    }

    public static void setDB(DBManager DB) {
        SessionsInTheRoom.DB = DB;
    }

    public static DebugManager getDM() {
        return DM;
    }
    
    public static PDFManager getPDF() {
        return PDF;
    }

    public static void setPDF(PDFManager PDF) {
        SessionsInTheRoom.PDF = PDF;
    }

    public static XMLManager getXML() {
        return XML;
    }
}
