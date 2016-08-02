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
import Model.Cache;
import java.text.ParseException;
import javax.swing.JFrame;
import Model.DatabaseManager;
import Model.Model;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Rozsenich Levente
 */
public class GUI extends JFrame{

    private final Model model;
    private final JPanel mainPanel;
    private final JPanel leftPanel;
    private final JPanel centerPanel;
    private final JPanel rightPanel;
    private final MoviePanel moviePanel;
    private final MovieDetailsScrollPane movieDetails;
    private final MovieRentalHistoryPanel movieRentalHistoryPanel;
    private final RentalPanel rentalPanel;
    private final RentalDetailsScrollPane rentalDetails;
    private final PanicButton panicButton;
    private final JPanel buttonPanel;
    
    public GUI() throws ParseException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException, NumberFormatException, SQLException {
        mainPanel = new JPanel(new BorderLayout());
        leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(Color.LIGHT_GRAY);
        centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.LIGHT_GRAY);

        
        model = new Model(new Cache(new DatabaseManager()));
        
        movieDetails = new MovieDetailsScrollPane();
        movieRentalHistoryPanel = new MovieRentalHistoryPanel(model);
        rentalDetails = new RentalDetailsScrollPane();
        rentalPanel = new RentalPanel(model, rentalDetails);
        moviePanel = new MoviePanel(model,movieRentalHistoryPanel, movieDetails, rentalPanel);
        buttonPanel = new JPanel(new BorderLayout());
        panicButton = new PanicButton(moviePanel, rentalPanel, leftPanel, centerPanel, rightPanel, buttonPanel, movieDetails, rentalDetails, movieRentalHistoryPanel, model);
        
        buttonPanel.add(panicButton, BorderLayout.EAST);
        buttonPanel.setPreferredSize(new Dimension(50,50));
        buttonPanel.setOpaque(true);
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        
        moviePanel.setPreferredSize(new Dimension(250, 300));
        movieRentalHistoryPanel.setPreferredSize(new Dimension(250,200));
        movieDetails.setPreferredSize(new Dimension(100, 150));
        rentalDetails.setPreferredSize(new Dimension(100,150));
        rentalPanel.setPreferredSize(new Dimension(250, 800));
        
        leftPanel.add(moviePanel, BorderLayout.NORTH);
        leftPanel.add(movieRentalHistoryPanel, BorderLayout.SOUTH);
        
        centerPanel.add(movieDetails, BorderLayout.NORTH);
        centerPanel.add(rentalDetails, BorderLayout.SOUTH);

        
        rightPanel.add(rentalPanel, BorderLayout.CENTER);
        rightPanel.add(buttonPanel, BorderLayout.NORTH);
        
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        
        this.setTitle("Kölcsönző");
        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
    }
    
}
