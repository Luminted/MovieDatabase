/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Model.Movie;
import java.awt.Color;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Rozsenich Levente
 */
class RentalDetailsScrollPane extends JScrollPane{
    
    private JLabel name;
    private JLabel startDate;
    private JLabel endDate;
    private JLabel movieTitle;
    private JPanel viewPanel;

    /**
     *
     */
    public RentalDetailsScrollPane(){
        super();
        
        viewPanel = new JPanel(new GridLayout(4,1));
        name = new JLabel("Név: ");
        startDate = new JLabel("Kezdés: ");
        endDate = new JLabel("Lejár: ");
        movieTitle = new JLabel("Film: ");
        
        this.setBorder(BorderFactory.createTitledBorder("Kölcsönzés részletei"));
        this.setOpaque(true);
        this.setBackground(Color.LIGHT_GRAY);
        viewPanel.add(name);
        viewPanel.add(startDate);
        viewPanel.add(endDate);
        viewPanel.add(movieTitle);
        this.getViewport().add(viewPanel);
    }
    
    public void update(String name, String startDate, String endDate, String title){
        this.name.setText("Név: " + name);
        this.startDate.setText("Kezdés: " + startDate);
        this.endDate.setText("Lejár: " + endDate);
        this.movieTitle.setText("Film: " + title);
    }
}
