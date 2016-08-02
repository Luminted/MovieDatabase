package UI;

import Model.Model;
import Model.Movie;
import Model.Rental;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.ListSelectionModel.SINGLE_SELECTION;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Rozsenich Levente
 */
public class RentalPanel extends JPanel{
    private JTable table;
    private RowFilter<RentalTableModel, Object> rowFilter;
    private RentalTableModel rentalTableModel;
    private TableRowSorter<RentalTableModel> sorter;
    private RowFilter<RentalTableModel, Object> filter;
    private JScrollPane scrollPane;
    private JTextField searchBar;
    private JButton deleteButton;
    private RentalDetailsScrollPane details;
    private Model model;
    private boolean isLegalShown;
    private RentalCellRenderer renderer;
    
    
    public RentalPanel(Model model, RentalDetailsScrollPane details){
        super();
        
        this.renderer = new RentalCellRenderer(model);
        this.model = model;
        initTable();
        initSearchBar();
        
        this.details = details;
        this.isLegalShown = true;
        this.scrollPane = new JScrollPane();
        this.scrollPane.getViewport().add(table);
        this.deleteButton = new JButton(delete);
        this.deleteButton.setText("Visszahoz");
        
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("Kölcsönzések"));
        
        this.add(searchBar, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(deleteButton, BorderLayout.SOUTH);
        
        this.revalidate();
        this.repaint();
    }
    
    public void updateDisplay(){
        rentalTableModel.fireTableDataChanged();
    }
    
    public void setIsLegalShown(boolean bool){
        this.isLegalShown = bool;
        rentalTableModel.setIsLegalShown(this.isLegalShown);
        rentalTableModel.fireTableDataChanged();
    }
    
    public boolean isLegalShown(){
        return this.isLegalShown;
    }
    
    private void initTable(){
        this.rentalTableModel = new RentalTableModel(model);
        this.sorter = new TableRowSorter<RentalTableModel>(rentalTableModel);
        this.filter = RowFilter.regexFilter("(?i)");
        this.sorter.setRowFilter(filter);
        table = new JTable(rentalTableModel);
        
        table.setRowSorter(sorter);
        table.setTableHeader(null);
        table.setSelectionMode(SINGLE_SELECTION);
        this.setOpaque(true);
        this.setBackground(Color.LIGHT_GRAY);
        table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("A")).setCellRenderer(this.renderer);
        System.out.println(this.renderer);
        
        table.getColumnModel().removeColumn(table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("B")));
        table.getColumnModel().removeColumn(table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("C")));
        table.getColumnModel().removeColumn(table.getColumnModel().getColumn(table.getColumnModel().getColumnIndex("D")));

        
        
        table.setFillsViewportHeight(true);
        table.addMouseListener(selectionListener);
        
        this.table = table;
    }
    
    private void initSearchBar(){
        searchBar = new JTextField("Szűrés");
        searchBar.setAction(search);
        
        this.searchBar = searchBar;
    }
    
    private String getMovieTitleById(int id) throws SQLException{
        for(Movie movie : model.getMovies()){
            if(movie.id == id){
                return (String)movie.getValueAtIndex(1);
            }
        }
        return null;
    }
    
    public int getSelectedRentalMovieId() throws SQLException{
        return (int) model.getRentalAtIndex(table.convertRowIndexToModel(table.getSelectedRow())).getRentalValueAtIndex(3);
    }
    
    public boolean isAnySelected(){
        return !(table.getSelectedRow() == -1);
    }
    
    private void updateFilter(String regex){
        this.filter = rowFilter.regexFilter(regex, 0,1,2);
        this.sorter.setRowFilter(filter);
        
        this.validate();
        this.repaint();
    }
    
        private void updateDetailComponents() throws SQLException{
            if(table.getSelectedRow() != -1){
                Rental rental = model.getRentalAtIndex(table.convertRowIndexToModel(table.getSelectedRow()));
                details.update((String)rental.getRentalValueAtIndex(0), new SimpleDateFormat("yyyy.MM.dd").format((Date)rental.getRentalValueAtIndex(1)), new SimpleDateFormat("yyyy.MM.dd").format((Date)rental.getRentalValueAtIndex(2)), (String)getMovieTitleById((int)rental.getRentalValueAtIndex(3)));
            }else{
                details.update("", "", "", "");
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
    
    AbstractAction delete = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(table.getSelectedRow() > -1){
                try {
                    model.removeRental(model.getRentalAtIndex(table.convertRowIndexToModel(table.getSelectedRow())).id);
                    rentalTableModel.fireTableRowsDeleted(table.convertRowIndexToModel(table.getSelectedRow()), table.convertRowIndexToModel(table.getSelectedRow()));
                        updateDetailComponents();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(getParent(), "Hiba történt az adatbázis elérésekor.");
                }
            }
        }
    };
    
    MouseListener selectionListener = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
            if(table.getSelectedRow() > -1){
                try {
                    updateDetailComponents();
                } catch (SQLException ex) {
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
}
