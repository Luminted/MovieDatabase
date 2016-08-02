/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Rozsenich Levente
 */
public class Cache {
    ArrayList<Movie> movies;
    ArrayList<Rental> rentals;
    DatabaseManager DB;
    
    
    public Cache(DatabaseManager DB) throws SQLException{
        this.DB = DB;
        movies = DB.getMovies();
        rentals = DB.getRentals();
    }
    
    public ArrayList<Movie> getMovies(){
        return movies;
    }
    
    public ArrayList<Rental> getRentals(){
        return rentals;
    }
    
    public void addMovie(Movie newMovie) throws SQLException{
        DB.addMovie(newMovie);
        updateMovies();
    }
    
    public void addRental(Rental newRental) throws SQLException, ParseException{
        DB.addRental(newRental);
        updateRentals();
    }
    
    public void removeMovie(int id) throws SQLException{
        DB.removeMovie(id);
        updateMovies();
        updateRentals();
    }
    
    public void removeRental(int id) throws SQLException{
        DB.removeRental(id);
        updateRentals();
    }
    
    private void updateMovies() throws SQLException{
        movies = DB.getMovies();
    }
    
    private void updateRentals() throws SQLException{
        rentals = DB.getRentals();
    }
}
