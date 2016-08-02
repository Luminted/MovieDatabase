/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/**
 *
 * @author Rozsenich Levente
 */
public class MovieDetailsScrollPane extends JScrollPane{
    private JLabel titleLable;
    private JLabel directorsLable;
    private JLabel actorsLable;
    private JLabel releaseLable;
    private JPanel viewPanel;
    
    public MovieDetailsScrollPane(){
        super();
        
        titleLable = new JLabel("Cím: ");
        directorsLable = new JLabel("Rendező(k): ");
        actorsLable = new JLabel("Szereplő(k): ");
        releaseLable = new JLabel ("Megjelenés éve: ");
        viewPanel = new JPanel(new GridLayout(4,1));
        
        this.setBorder(BorderFactory.createTitledBorder("Film részletei"));
        this.setOpaque(true);
        this.setBackground(Color.LIGHT_GRAY);
        viewPanel.add(titleLable);
        viewPanel.add(directorsLable);
        viewPanel.add(actorsLable);
        viewPanel.add(releaseLable);
        this.getViewport().add(viewPanel);
    }
    
    public void update(String title, String[] directors, String[] actors, String release){
        this.titleLable.setText("Cím: " + title);
        
        String directorsInStringFormat = "";
        for(String director : directors){
            directorsInStringFormat += director + ", ";
        }
        if(directorsInStringFormat != ""){
             directorsInStringFormat = directorsInStringFormat.substring(0, directorsInStringFormat.length()-2);
        }
        this.directorsLable.setText("Rendező(k): " + directorsInStringFormat);
        
        String actorsInStringFormat = "";
        for(String actor : actors){
            actorsInStringFormat += actor + ", ";
        }
        if(actorsInStringFormat != ""){
            actorsInStringFormat = actorsInStringFormat.substring(0, actorsInStringFormat.length()-2);
        }
        this.actorsLable.setText("Szereplő(k): " + actorsInStringFormat);
        
        this.releaseLable.setText("Megjelenés Éve: " + release);
    }
}
