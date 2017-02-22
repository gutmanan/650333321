/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import businessLogic.CreateStudioControl;
import java.util.ArrayList;
import businessLogic.StudioRatesControl;
import businessLogic.WindowManager;
import entity.E_CITIES;
import entity.Freelancer;
import entity.RatingBar;
import entity.RatingBarCell;
import entity.Studio;
import entity.StudiosFreelancers;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import org.hsqldb.lib.HashMap;

/**
 *
 * @author Shai Gutman
 */
public class StudioRates extends javax.swing.JPanel {
    private Map<String, StudiosFreelancers> hm;
    public StudioRates() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        studioComboBox = new javax.swing.JComboBox<>();
        studioNameLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        updateButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        existedRoomsLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        wallpaper = new javax.swing.JLabel();

        setOpaque(false);
        setLayout(null);

        studioComboBox.addItem("Select Studio");
        for (Studio s : CreateStudioControl.getStudios())
        studioComboBox.addItem(s.toString());
        studioComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studioComboBoxActionPerformed(evt);
            }
        });
        add(studioComboBox);
        studioComboBox.setBounds(190, 80, 180, 30);

        studioNameLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        studioNameLabel.setForeground(new java.awt.Color(0, 0, 0));
        studioNameLabel.setText("Studio number:");
        add(studioNameLabel);
        studioNameLabel.setBounds(60, 90, 130, 20);

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("About studio:");
        add(jLabel7);
        jLabel7.setBounds(470, 90, 150, 20);

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Name:");
        add(jLabel8);
        jLabel8.setBounds(470, 110, 340, 20);

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Address:");
        add(jLabel9);
        jLabel9.setBounds(470, 130, 340, 20);

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Email:");
        add(jLabel10);
        jLabel10.setBounds(470, 150, 340, 20);

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Phone number:");
        add(jLabel11);
        jLabel11.setBounds(470, 170, 340, 20);

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Description:");
        add(jLabel12);
        jLabel12.setBounds(470, 190, 340, 20);

        updateButton.setText("Update");
        add(updateButton);
        updateButton.setBounds(570, 510, 140, 26);

        jScrollPane2.setBorder(null);
        jScrollPane2.setOpaque(false);
        jScrollPane2.setBorder(null);
        jScrollPane2.getViewport().setOpaque(false);
        jScrollPane2.getViewport().setBorder(null);

        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.setOpaque(false);
        jTable1.setShowHorizontalLines(false);
        jTable1.setShowVerticalLines(false);
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setBorder(null);
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBorder(null);
        jScrollPane2.setViewportView(jTable1);

        add(jScrollPane2);
        jScrollPane2.setBounds(60, 220, 650, 270);

        existedRoomsLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        existedRoomsLabel.setForeground(new java.awt.Color(0, 0, 0));
        existedRoomsLabel.setText("Freelancers:");
        add(existedRoomsLabel);
        existedRoomsLabel.setBounds(60, 190, 130, 20);

        titleLabel.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(0, 0, 0));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Studio Rates For Freelancers");
        add(titleLabel);
        titleLabel.setBounds(0, 10, 850, 40);

        wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/container3.png"))); // NOI18N
        add(wallpaper);
        wallpaper.setBounds(0, 0, 850, 580);
    }// </editor-fold>//GEN-END:initComponents

    private void studioComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studioComboBoxActionPerformed
        if (studioComboBox.getSelectedItem() == null || studioComboBox.getSelectedIndex() == 0) {
            return;
        }
        Studio tmpStudio = null;
        for (Studio s : CreateStudioControl.getStudios()) {
            if (String.valueOf(s.getStudioID()).equals(String.valueOf(studioComboBox.getSelectedItem()))) {
                tmpStudio = s;
            }
        }
        jLabel8.setText(jLabel8.getText()+" "+tmpStudio.getStudioName());
        jLabel9.setText(jLabel9.getText()+" "+tmpStudio.getAddress());
        jLabel10.setText(jLabel10.getText()+" "+tmpStudio.getEmail());
        jLabel11.setText(jLabel11.getText()+" "+tmpStudio.getPhoneNum());
        jLabel12.setText(jLabel12.getText()+" "+tmpStudio.getDescription());
                hm = new java.util.HashMap<>();
        for (Freelancer f : StudioRatesControl.getFreelancersWorkingWith(tmpStudio.getStudioID())) {
            hm.put(f.getFreelancerID(),new StudiosFreelancers(tmpStudio.getStudioID(), f.getFreelancerID(), StudioRatesControl.getRankOf(tmpStudio.getStudioID(), f.getFreelancerID())));
        }
        setTable(tmpStudio.getStudioID());
    }//GEN-LAST:event_studioComboBoxActionPerformed

public List<E_CITIES> ListOfCity(String country){
    List<E_CITIES> cityList = new ArrayList<E_CITIES>();

    for (E_CITIES city : E_CITIES.values()){
        if (country.equals(city.getCountry()) && !cityList.contains(city))
            cityList.add(city);
    }
    Collections.sort(cityList);
    return cityList;
}
    
public void setTable(int selectedStudio){
    if (hm.isEmpty()) {
        jTable1.setModel(new DefaultTableModel());
        return;
    }
    DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int col) {
            return false;
        }
    }; 
    jTable1.setModel(model);
    jTable1.setOpaque(false);
    jTable1.setBorder(null);
    jScrollPane2.setOpaque(false);
    jScrollPane2.setBorder(null);
    jScrollPane2.getViewport().setOpaque(false);
    jScrollPane2.getViewport().setBorder(null);
    ((DefaultTableCellRenderer)jTable1.getDefaultRenderer(Object.class)).setOpaque(false);
    model.addColumn("ID"); 
    model.addColumn("Frist name"); 
    model.addColumn("Last name");
    model.addColumn("Productivity");
    model.addColumn("Rating");
    jTable1.setRowHeight(30);
    TableColumn tc = jTable1.getColumnModel().getColumn(4);
    model.setRowCount(0);
    for (Freelancer f : StudioRatesControl.getFreelancersWorkingWith(selectedStudio)) {
        RatingBarCell rbc = new RatingBarCell();
        tc.setCellEditor(rbc);
        tc.setCellRenderer(rbc);
        rbc.getCellEditorValue().setRate(hm.get(f.getFreelancerID()).getRating());
        model.addRow(new Object[]{f.getFreelancerID(),f.getFirst(), f.getLast(),"50", rbc.getCellEditorValue()});
    }
    final RatingBar updated = new RatingBar();
    jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (jTable1.getSelectedRow() > -1) {
                    if (((RatingBar)jTable1.getValueAt(jTable1.getSelectedRow(), 4)).getRate() != -1)
                        return;
                    if (jTable1.getSelectedColumn() == 4 ) {
                        JOptionPane.showMessageDialog(null,updated,"Update Rating", JOptionPane.PLAIN_MESSAGE);
                        jTable1.setValueAt(updated, jTable1.getSelectedRow(), 4);
                        hm.get(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0))).setRating(updated.getRate());
                        setTable(hm.get(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0))).getStudioID());
                        return;
                    }
                }       
            }
        });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> dayBox;
    private javax.swing.JComboBox<String> dayBox1;
    private javax.swing.JLabel existedRoomsLabel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> monthBox;
    private javax.swing.JComboBox<String> monthBox1;
    private javax.swing.JInternalFrame newAccountFrame;
    private javax.swing.JInternalFrame newAccountFrame1;
    private javax.swing.JButton registerButton;
    private javax.swing.JButton registerButton1;
    private javax.swing.JComboBox<String> studioComboBox;
    private javax.swing.JLabel studioNameLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JButton updateButton;
    private javax.swing.JLabel wallpaper;
    private javax.swing.JComboBox<String> yearBox;
    private javax.swing.JComboBox<String> yearBox1;
    // End of variables declaration//GEN-END:variables
}
