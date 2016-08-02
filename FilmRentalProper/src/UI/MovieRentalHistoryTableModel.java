/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Model.Model;
import java.awt.Component;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rozsenich Levente
 */
class MovieRentalHistoryTableModel extends AbstractTableModel{

    private Model model;
    private final int COL_COUNT;
    private int selectedMovieId;
    
    public MovieRentalHistoryTableModel(Model model){
        super();
        
        this.model = model;
        COL_COUNT = 4;
    }
    
    public void setSelectedMovieId(int id){
        this.selectedMovieId = id;
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "Név";
            case 1:
                return "Kezdet";
            case 2:
                return "Lejár";
            case 3:
                return "Id";
        }
        return null;
    }
    
    @Override
    public int getRowCount() {
        try {
            return model.getMovieRentalHistory(selectedMovieId).size();
        } catch (SQLException ex) {
            Logger.getLogger(MovieRentalHistoryTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        return COL_COUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            return model.getMovieRentalHistoryValueAtIndex(selectedMovieId, rowIndex, columnIndex);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(MovieRentalHistoryTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
