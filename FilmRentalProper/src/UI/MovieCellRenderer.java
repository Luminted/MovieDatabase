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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Rozsenich Levente
 */
public class MovieCellRenderer extends DefaultTableCellRenderer{

    private Model model;

    public MovieCellRenderer(Model model) {
        super();
        this.model = model;
    }
    
    
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        try {
            if(model.isRented(table.convertRowIndexToModel(row))){
                setOpaque(true);
                setBackground(Color.yellow);
            }else{
                setOpaque(false);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Hiba történt az adatbázis elérésekor.");
        }
        return this;
    }
    
    
    
}
