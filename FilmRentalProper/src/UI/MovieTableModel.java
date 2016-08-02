/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Model.DatabaseManager;
import Model.Model;
import javax.swing.table.AbstractTableModel;
import java.lang.String;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Rozsenich Levente
 */
class MovieTableModel extends AbstractTableModel{

    private Model model;
    private final int COL_COUNT;
    private boolean isLegalShown;
    
    public MovieTableModel(Model model){
        super();
        
        isLegalShown = true;
        this.model = model;
        COL_COUNT = 6;
    }
    
    public void setIsLegalShown(boolean bool){
        this.isLegalShown = bool;
    }
    
    @Override
    public int getRowCount() {
        try {
            
        
        if(isLegalShown){
                return model.getMovies().size();
        }else{
                return model.getLegalMovies().size();
        }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Hiba történt az adatbázis elérésekor.");
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        return COL_COUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try{
        if(isLegalShown){
            switch(columnIndex){
                case 0:
                    return model.getMovieValueAtIndex(rowIndex, columnIndex);
                case 1:
                    return model.getMovieValueAtIndex(rowIndex, columnIndex);
                case 2:
                    return model.getMovieAtIndex(rowIndex).actorsToString();
                case 3:
                    return model.getMovieAtIndex(rowIndex).directorsToString();
                case 4:
                    return model.getMovieValueAtIndex(rowIndex, columnIndex);
                case 5:
                    return model.getMovieValueAtIndex(rowIndex, columnIndex);
                default:
                    return null;
            }
        }else{
            switch(columnIndex){
                case 0:
                    return model.getLegalMovieValueAtIndex(rowIndex, columnIndex);
                case 1:
                    return model.getLegalMovieValueAtIndex(rowIndex, columnIndex);
                case 2:
                    return model.getLegalMovieAtIndex(rowIndex).actorsToString();
                case 3:
                    return model.getLegalMovieAtIndex(rowIndex).directorsToString();
                case 4:
                    return model.getLegalMovieValueAtIndex(rowIndex, columnIndex);
                case 5:
                    return model.getLegalMovieValueAtIndex(rowIndex, columnIndex);
                default:
                    return null;
            }
        }
        }catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Hiba történt az adatbázis elérésekor.");
        }
        return null;
    }
}
