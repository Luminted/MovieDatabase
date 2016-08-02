/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Exceptions.DuplicateRentalException;
import Exceptions.EmptyFieldException;
import Exceptions.InvalidStartAndEndDateException;
import Exceptions.NonexistentMovieException;
import Model.Model;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Rozsenich Levente
 */
public class AddRentalDialog {
    
    public static void display(Model model, JTable table) throws ParseException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException, EmptyFieldException, SQLException {
        JTextField name = new JTextField();
        JTextField startDate = new JTextField();
        JTextField endDate = new JTextField();
        
        JPanel panel = new JPanel(new GridLayout(0, 1));
        
        panel.add(new JLabel("Név:"));
        panel.add(name);
        panel.add(new JLabel("Kezdet:"));
        panel.add(startDate);
        panel.add(new JLabel("Lejár:"));
        panel.add(endDate);

        int result = JOptionPane.showConfirmDialog(null, panel, "Kölcsönadás",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if(result == JOptionPane.OK_OPTION){
            int id = (Integer)table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 0);
            
            model.addRental(name.getText(), startDate.getText(), endDate.getText(), id);
        }
    } 
}
