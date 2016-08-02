/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Model.Model;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rozsenich Levente
 */
public class RentalTableModel extends AbstractTableModel{

    private int colCount;
    private final Model model;
    private boolean isLegalShown;
    
    public RentalTableModel(Model model){
        super();
        this.model = model;
        this.isLegalShown = true;
        colCount = 4;
    }
    
    public void setIsLegalShown(boolean bool){
        this.isLegalShown = bool;
    }
    
    @Override
    public int getRowCount() {
        try{
        if(isLegalShown){
            return model.getRentals().size();
        }else{
            return model.getLegalRentals().size();
        }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Hiba történt az adatbázis elérésekor.");
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        return colCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try{
        if(isLegalShown){
            if(columnIndex == 1 || columnIndex == 3){
                return new SimpleDateFormat("yyyy.MM.dd").format(model.getRentalsValueAtIndex(rowIndex, columnIndex));
            }
            return model.getRentalsValueAtIndex(rowIndex, columnIndex);
        }else{
            if(columnIndex == 1 || columnIndex == 3){
                return new SimpleDateFormat("yyyy.MM.dd").format(model.getLegalRentalValueAtIndex(rowIndex, columnIndex));
            }
            return model.getLegalRentalValueAtIndex(rowIndex, columnIndex);
        }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Hiba történt az adatbázis elérésekor.");
        }
        return null;
    }
    
}
