/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Model.Model;
import java.awt.Color;
import java.awt.Component;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Rozsenich Levente
 */
public class RentalCellRenderer extends DefaultTableCellRenderer{
    
        private Model model;

    public RentalCellRenderer(Model model) {
        super();
        this.model = model;
    }
    
    
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
            try {
                if(model.isRentalOverdue(table.convertRowIndexToModel(row))){
                    setOpaque(true);
                    setBackground(Color.red);
                }else{
                    setOpaque(false);
                }   } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Hiba történt az adatbázis elérésekor.");
            }
        return this;
    }
}
