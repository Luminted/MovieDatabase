/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Model.Model;
import com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets;
import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Rozsenich Levente
 */
public class PanicButton extends JButton{
    
    private MoviePanel moviePanel;
    private RentalPanel rentalPanel;
    private JPanel leftPanel;
    private JPanel centerPanel;
    private JPanel rightPanel;
    private MovieDetailsScrollPane movieDetail;
    private RentalDetailsScrollPane rentalDetail;
    private JPanel buttonPanel;
    private MovieRentalHistoryPanel historyPanel;
    private Model model;
    
    public PanicButton(MoviePanel moviePanel, RentalPanel rentalPanel, JPanel leftPanel, JPanel centerPanel, JPanel rightPanel, JPanel buttonPanel, MovieDetailsScrollPane movieDetail, RentalDetailsScrollPane rentalDetai, MovieRentalHistoryPanel historyPanel, Model model){
        this.moviePanel = moviePanel;
        this.rentalPanel = rentalPanel;
        this.leftPanel = leftPanel;
        this.centerPanel = centerPanel;
        this.rightPanel = rightPanel;
        this.buttonPanel = buttonPanel;
        this.rentalDetail = rentalDetai;
        this.movieDetail = movieDetail;
        this.historyPanel = historyPanel;
        this.model = model;
        
        this.setIcon(new ImageIcon("resources/deal-with-it.png"));
        this.addActionListener(pressAction);
    }
    
    AbstractAction pressAction = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(moviePanel.isAnySelected()){
                    if(!moviePanel.getSelectedMovie().isLegal()){
                        movieDetail.update("", new String[0], new String[0], "");
                        historyPanel.updateDisplay(-1);
                    }
                }
                if(rentalPanel.isAnySelected()){
                    if(!model.getMovieById(rentalPanel.getSelectedRentalMovieId()).isLegal()){
                        rentalDetail.update("", "", "", "");
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(getParent(), "Hiba történt az adatbázis elérésekor.");
            }
                        
            moviePanel.setIsLegalShown(!moviePanel.isLegalShown());
            rentalPanel.setIsLegalShown(!rentalPanel.isLegalShown());
            
            if(!moviePanel.isLegalShown()){
                rightPanel.setBackground(Color.GRAY);
                leftPanel.setBackground(Color.GRAY);
                centerPanel.setBackground(Color.GRAY);
                buttonPanel.setBackground(Color.GRAY);
                movieDetail.setBackground(Color.GRAY);
                rentalDetail.setBackground(Color.GRAY);
                moviePanel.setBackground(Color.GRAY);
                rentalPanel.setBackground(Color.GRAY);
                historyPanel.setBackground(Color.GRAY);
                
            }else{
                rightPanel.setBackground(Color.LIGHT_GRAY);
                leftPanel.setBackground(Color.LIGHT_GRAY);
                centerPanel.setBackground(Color.LIGHT_GRAY);
                buttonPanel.setBackground(Color.LIGHT_GRAY);
                movieDetail.setBackground(Color.LIGHT_GRAY);
                rentalDetail.setBackground(Color.LIGHT_GRAY);
                moviePanel.setBackground(Color.LIGHT_GRAY);
                rentalPanel.setBackground(Color.LIGHT_GRAY);
                historyPanel.setBackground(Color.LIGHT_GRAY);
        }
        
    }
    };
    
}
