/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Exceptions.DuplicateMovieException;
import Exceptions.DuplicateRentalException;
import Exceptions.EmptyFieldException;
import Exceptions.InvalidReleaseDateException;
import Exceptions.InvalidStartAndEndDateException;
import Exceptions.NonexistentMovieException;
import Model.Model;
import Model.Movie;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import static javax.swing.ListSelectionModel.SINGLE_SELECTION;
import javax.swing.RowFilter;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Rozsenich Levente
 */
public class MovieRentalHistoryPanel extends JPanel{
    
    private JTable table;
    private Model model;
    private JScrollPane scrollPane;
    private JLabel movieTilte;

    public MovieRentalHistoryPanel(Model model){
        
        super(new BorderLayout());
        
        this.model = model;
        
        initTable();
        
        scrollPane = new JScrollPane(table);
        this.movieTilte = new JLabel();
        this.setOpaque(true);
        this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(BorderFactory.createTitledBorder("Kölcsönzési napló"));
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(movieTilte, BorderLayout.NORTH);
        
        this.validate();
        this.repaint();
    }
    
    private void initTable(){
        JTable table = new JTable(new MovieRentalHistoryTableModel(model));
        
        table.getColumnModel().removeColumn(table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("Id")));
                
        table.setFillsViewportHeight(true);
        
        this.table = table;
    }
    
    public void updateDisplay(int id) throws SQLException{
        MovieRentalHistoryTableModel tableModel = (MovieRentalHistoryTableModel)this.table.getModel();
        if(id != -1){
            movieTilte.setText((String)model.getMovieById(id).getValueAtIndex(1));
        }else{
            movieTilte.setText("");
        }
        tableModel.setSelectedMovieId(id);
        tableModel.fireTableDataChanged();
    }
}
