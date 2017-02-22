/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class RatingBarCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{
    RatingBar panel;
    
    public RatingBarCell() {
        panel = new RatingBar();
    }
    private void updateData(RatingBar rb, boolean isSelected, JTable table) {
        this.panel = rb;
        if (isSelected) {
          panel.setBackground(table.getSelectionBackground());
          return;
        } else{
          panel.setBackground(table.getBackground());
        }
    }
 
    public Component getTableCellEditorComponent(JTable table, Object value,
        boolean isSelected, int row, int column) {
        RatingBar feed = (RatingBar)value;
        updateData(feed, true, table);
        return panel;
    }

    public RatingBar getCellEditorValue() {
      return (RatingBar) panel;
    }
 
    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
      RatingBar feed = (RatingBar)value;
      updateData(feed, isSelected, table);
      return panel;
    } 
}
