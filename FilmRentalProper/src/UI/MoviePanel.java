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
import com.sun.org.apache.bcel.internal.generic.TABLESWITCH;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import static javax.swing.ListSelectionModel.SINGLE_SELECTION;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Rozsenich Levente
 */
public class MoviePanel extends JPanel{
    
    private JTable table;
    private Model model;
    private JScrollPane scrollPane;
    private MovieTableModel movieTableModel;
    private TableRowSorter<MovieTableModel> sorter;
    private RowFilter<MovieTableModel, Object> filter;
    private JTextField searchBar;
    private RowFilter<MovieTableModel, Object> rowFilter;
    private JButton addButton;
    private JButton deleteButton;
    private JButton rentButton;
    private JPanel buttonPanel;
    private MovieDetailsScrollPane details;
    private MovieRentalHistoryPanel movieRentalHistoryPanel;
    private boolean isLegalShown;
    private RentalPanel rentalPanel;
    private MovieCellRenderer renderer;


    public MoviePanel(Model model, MovieRentalHistoryPanel movieRentalHistoryPanel, MovieDetailsScrollPane details, RentalPanel rentalPanel){
        
        super(new BorderLayout());
        
        this.model = model;
        this.details = details;
        this.rentalPanel = rentalPanel;
        this.movieRentalHistoryPanel = movieRentalHistoryPanel;
        isLegalShown = true;
        
        initTable();
        initSearchBar(searchBar);
        initButtons();
        
        scrollPane = new JScrollPane(table);
        
        this.setOpaque(true);
        this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(BorderFactory.createTitledBorder("Filmek"));
        this.add(searchBar, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.add(scrollPane, BorderLayout.CENTER);
        
        this.validate();
        this.repaint();
    }
    
    public void setIsLegalShown(boolean bool){
        this.isLegalShown = bool;
        
        movieTableModel.setIsLegalShown(this.isLegalShown);
        movieTableModel.fireTableDataChanged();
    }
    
    public boolean isLegalShown(){
        return this.isLegalShown;
    }
    
    public Movie getSelectedMovie() throws SQLException{
        return model.getMovieAtIndex(table.convertRowIndexToModel(table.getSelectedRow()));
    }
    
    public boolean isAnySelected(){
        return !(table.getSelectedRow() == -1);
    }
    
    private void initTable(){
        this.movieTableModel = new MovieTableModel(model);
        this.sorter = new TableRowSorter<MovieTableModel>(movieTableModel);
        this.filter = RowFilter.regexFilter("(?i)");
        this.sorter.setRowFilter(filter);
        this.renderer = new MovieCellRenderer(model);
        JTable table = new JTable(movieTableModel);
        table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("B")).setCellRenderer(renderer);
        
        table.getColumnModel().removeColumn(table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("A")));
        table.getColumnModel().removeColumn(table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("C")));
        table.getColumnModel().removeColumn(table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("D")));
        table.getColumnModel().removeColumn(table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("E")));
        table.getColumnModel().removeColumn(table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("F")));
        
        this.filter = RowFilter.regexFilter("(?i)");
        this.sorter.setRowFilter(filter);
        table.setRowSorter(sorter);
        table.setTableHeader(null);
        table.setSelectionMode(SINGLE_SELECTION);
        table.setFillsViewportHeight(true);
        table.addMouseListener(selectionListener);
        
        this.table = table;
    }
    
    private void initButtons(){
        this.buttonPanel = new JPanel(new BorderLayout());
        this.addButton = new JButton(add);
        this.deleteButton = new JButton(delete);
        this.rentButton = new JButton(rent);
        
        this.addButton.setText("Hozzáad");
        this.deleteButton.setText("Töröl");
        this.rentButton.setText("Kölcsönad");
        
        this.buttonPanel.add(addButton, BorderLayout.WEST);
        this.buttonPanel.add(deleteButton, BorderLayout.CENTER);
        this.buttonPanel.add(rentButton, BorderLayout.EAST);
    }
    
    private void initSearchBar(JTextField searchBar){
        searchBar = new JTextField("Szűrés");
        searchBar.setAction(search);
        
        this.searchBar = searchBar;
    }
    
    private void updateFilter(String regex){
        this.filter = rowFilter.regexFilter(regex, 1, 4);
        this.sorter.setRowFilter(filter);
        
        this.validate();
        this.repaint();
    }
    
    private void updateDetailComponents() throws SQLException{
        if(table.getSelectedRow() != -1){
            Movie movie = model.getMovieAtIndex(table.convertRowIndexToModel(table.getSelectedRow()));
            details.update((String)movie.getValueAtIndex(1), (String[])movie.getValueAtIndex(2), (String[])movie.getValueAtIndex(3), "" + (int)movie.getValueAtIndex(4));
            movieRentalHistoryPanel.updateDisplay(movie.id);
        }else{
            details.update("", new String[0], new String[0], "");
            movieRentalHistoryPanel.updateDisplay(-1);
        }
        
        this.validate();
        this.repaint();
    }
    
    AbstractAction search = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField text = (JTextField)e.getSource();
            updateFilter("(?i)" + text.getText());
        }
    };

    MouseListener selectionListener = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
            if(table.getSelectedRow() != -1){
                try {
                    updateDetailComponents();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(getParent(), "Hiba történt az adatbázis elérésekor.");
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        
        }

        @Override
        public void mouseExited(MouseEvent e) {
             }
    };
    
    AbstractAction add = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                AddMovieDialog.display(model);
            } catch (EmptyFieldException ex) {
                JOptionPane.showMessageDialog(scrollPane, ex.getLocalizedMessage());
            } catch (InvalidReleaseDateException ex) {
                JOptionPane.showMessageDialog(scrollPane, ex.getLocalizedMessage());
            } catch (DuplicateMovieException ex) {
                JOptionPane.showMessageDialog(scrollPane, ex.getLocalizedMessage());
            } catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(scrollPane, "A megjelenés évét csak számokkal lehet megadni!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(getParent(), "Hiba történt az adatbázis elérésekor.");
            }
            try {
                if(model.getMovies().size() > 0){
                    movieTableModel.fireTableDataChanged();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(getParent(), "Hiba történt az adatbázis elérésekor.");
            }
        }
    };
            
    AbstractAction delete = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {
           if(table.getSelectedRow() != -1){
               try {
                   model.removeMovie(model.getMovieAtIndex(table.convertRowIndexToModel(table.getSelectedRow())).id);
                   movieTableModel.fireTableDataChanged();
                   rentalPanel.updateDisplay();
                       updateDetailComponents();
               } catch (SQLException ex) {
                   JOptionPane.showMessageDialog(getParent(), "Hiba történt az adatbázis elérésekor.");
                   ex.printStackTrace();
               }
           }
        }
    };
    
    AbstractAction rent = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(table.getSelectedRow() >= 0){
                    if(!model.getMovieAtIndex(table.convertRowIndexToModel(table.getSelectedRow())).isLegal()){
                        JOptionPane.showMessageDialog(scrollPane, "A film kalózmásolat");
                    }
                    AddRentalDialog.display(model, table);
                    movieTableModel.fireTableDataChanged();
                }
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(scrollPane, "Rossz dátum formátum! Várt formátum: éééé.hh.nn");
            } catch (NonexistentMovieException ex) {
                JOptionPane.showMessageDialog(scrollPane, ex.getLocalizedMessage());
            } catch (DuplicateRentalException ex) {
                JOptionPane.showMessageDialog(scrollPane, ex.getLocalizedMessage());
            } catch (InvalidStartAndEndDateException ex) {
                JOptionPane.showMessageDialog(scrollPane, ex.getLocalizedMessage());
            } catch (EmptyFieldException ex) {
                JOptionPane.showMessageDialog(scrollPane, ex.getLocalizedMessage());
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(getParent(), "Hiba történt az adatbázis elérésekor.");
            }
            rentalPanel.updateDisplay();
        }
    };
    
}
