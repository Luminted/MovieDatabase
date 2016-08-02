/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Exceptions.DuplicateMovieException;
import Exceptions.DuplicateRentalException;
import Exceptions.EmptyFieldException;
import Exceptions.InvalidReleaseDateException;
import Exceptions.InvalidStartAndEndDateException;
import Exceptions.NonexistentMovieException;
import java.awt.List;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Rozsenich Levente
 */
public class Model {
    
    private Cache cache;
    
    public Model(Cache cache) throws ParseException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException, NumberFormatException, SQLException{
        this.cache = cache;
        
        init();
    }
    
    public void addMovie(String title, String directors, String actors, int release, boolean isLegal) throws EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException, NumberFormatException, SQLException{
        
        if(title.equals("")){
            throw new EmptyFieldException("A cím nem lehet üres");
        }
        
        if(directors.equals("")){
            throw new EmptyFieldException("A rendezők listája nem lehet üres");
        }
        
        if(actors.equals("")){
            throw new EmptyFieldException("A szereplők listája nem lehet üres");
        }
        
        
        if(release < 1800){
            throw new InvalidReleaseDateException();
            
        }
        
        Movie newMovie = createMovie(generateMovieID(), title, directors, actors, release, isLegal);
        
        ArrayList<Movie> movies = cache.getMovies();
        
        for(Movie movie : movies){
            if(movie.equals(newMovie)){
                throw new DuplicateMovieException();
            }
        }
        
        cache.addMovie(newMovie);
    }
    
    public void addRental(String name, String startDate, String endDate, int movieID) throws ParseException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException, EmptyFieldException, SQLException{
        
        if(name.equals("")){
            throw new EmptyFieldException("A név nem lehet üres!");
        }
        
        if(startDate.equals("")){
            throw new EmptyFieldException("A kezdődátum nem lehet üres!");
        }
            
                
        if(endDate.equals("")){
            throw new EmptyFieldException("A lejárat dátuma nem lehet üres!");
        }
        
        Date startDateAsDate = new SimpleDateFormat("yyyy.MM.dd").parse(startDate);
        Date endDateAsDate = new SimpleDateFormat("yyyy.MM.dd").parse(endDate);
        
        if(startDateAsDate.compareTo(endDateAsDate) > 0){
            throw new InvalidStartAndEndDateException();
        }
        
        Rental newRental = createRental(name, startDateAsDate, endDateAsDate, movieID);
        
        ArrayList<Rental> rentals = cache.getRentals();
        
        for(Rental rental : rentals){
            if(rental.equals(newRental)){
                throw new DuplicateRentalException();
            }
        }
        
        for(Movie movie : cache.getMovies()){
            if(movie.id == movieID){
                cache.addRental(newRental);
                return;
            }
        }
        
        throw new NonexistentMovieException();
    }
    
    public ArrayList<Movie> getLegalMovies() throws SQLException{
        ArrayList<Movie> legalMovies = new ArrayList<>();
        
        for(Movie movie : cache.getMovies()){
            if(movie.isLegal() == true){
                legalMovies.add(movie);
            }
        }
        return legalMovies;
    }
    
    public ArrayList<Movie> getMovies() throws SQLException{
        return cache.getMovies();
    }
    
    public ArrayList<Rental> getRentals() throws SQLException{
        return cache.getRentals();
    }
    
    public Movie getMovieAtIndex(int index) throws SQLException{
        return cache.getMovies().get(index);
    }
    
    public Movie getMovieById(int id) throws SQLException{
        for(Movie movie : cache.getMovies()){
            if(movie.id == id){
                return movie;
            }
        }
        return null;
    }
    
    public Object getMovieValueAtIndex(int rowIndex, int colIndex) throws SQLException{
        return cache.getMovies().get(rowIndex).getValueAtIndex(colIndex);
    }
    
    public Object getLegalMovieValueAtIndex(int rowIndex, int colIndex) throws SQLException{
        return getLegalMovies().get(rowIndex).getValueAtIndex(colIndex);
    }
    
    public Movie getLegalMovieAtIndex(int index) throws SQLException{
        return getLegalMovies().get(index);
    }
    
    public Rental getRentalAtIndex(int index) throws SQLException{
        return cache.getRentals().get(index);
    }
    
    public Object getRentalsValueAtIndex(int rowIndex, int colIndex) throws SQLException{
        return cache.getRentals().get(rowIndex).getRentalValueAtIndex(colIndex);
    }
    
    public ArrayList getMovieRentalHistory(int id) throws SQLException{
        ArrayList<Rental> history = new ArrayList<>();
        
        for(Rental rental : cache.getRentals()){
            if(rental.movieID == id){
                history.add(rental);
            }
        }
        
        return history;
    }
    
    public Object getLegalRentalValueAtIndex(int rowIndex, int colIndex) throws SQLException{
        return getLegalRentals().get(rowIndex).getRentalValueAtIndex(colIndex);
    }
    
    public Object getMovieRentalHistoryValueAtIndex(int id, int rowIndex, int colIndex) throws SQLException{
        Rental rental = (Rental)getMovieRentalHistory(id).get(rowIndex);
        if(colIndex == 0 || colIndex == 3){
            return rental.getRentalValueAtIndex(colIndex);
        }else{
            return new SimpleDateFormat("yyyy.MM.dd").format(rental.getRentalValueAtIndex(colIndex));
        }
    }
    
    public ArrayList<Rental> getLegalRentals() throws SQLException{
        ArrayList<Rental> result = new ArrayList<Rental>();
        
        for(Rental rental : getRentals()){
            for(Movie movie : getLegalMovies()){
                if(movie.id == rental.movieID){
                    result.add(rental);
                }
            }
        }
        return result;
    }
    
    public void removeMovie(int id) throws SQLException{
        cache.removeMovie(id);
    }
    
    public void removeRental(int id) throws SQLException{
        cache.removeRental(id);
    }
    
    public boolean isRented(int index) throws SQLException{
        for(Rental rentail : cache.getRentals()){
            if(getMovieAtIndex(index).id == rentail.movieID){
                return true;
            }
        }
        return false;
    }
    
    public boolean isRentalOverdue(int index) throws SQLException{
        Date endDate = (Date)getRentalAtIndex(index).getRentalValueAtIndex(2);
        if(endDate.compareTo(new Date()) < 0){
            return true;
        }
        return false;
    }
    
    private Movie createMovie(int id, String title, String directors, String actors, int release, boolean isLegal){
        return new Movie(id, title, directors, actors, release, isLegal);
    }
    
    private Rental createRental(String name, Date startDate, Date endDate, int movieID) throws SQLException{
        return new Rental(generateRentalID(), name, startDate,endDate, movieID);
    }
    

    
    private int generateMovieID() throws SQLException{
        
        if(cache.getMovies().isEmpty()){
            return 1;
        }
        
        int max = cache.getMovies().get(0).id;
        
        for(Movie movie : cache.getMovies()){
            if(movie.id > max){
                max = movie.id;
            }
        }
        
        return max + 1;
    }
    
    private int generateRentalID() throws SQLException{
        if(cache.getRentals().size() == 0){
            return 1;
        }
        
        int max = cache.getRentals().get(0).id;
        
        for(Rental rental : cache.getRentals()){
            if(max < rental.id){
                max = rental.id;
            }
        }
        return max + 1;
    }
    
    private void init() throws ParseException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException, NumberFormatException, SQLException{
        addMovie("Holtak Hajnala", "Zack Snyder", "Random Bloke, Random Girl", 2006, true);
        addMovie("Haláli hullák hajnala", "Edmund Wright", "Simon Pegg", 2004, true);
        addMovie("Gone Girl", "Allen Smithy", "Ben Affleck", 2015, false);
        addMovie("Super", "James Gunn, Others", "The guy from Ofice", 2010, false);
        
        addRental("Kopsz Jenő", "2016.01.22", "2016.06.02", 2);
        addRental("Kopsz Jenő", "2016.01.23", "2016.06.03", 2);
        addRental("Kopsz Jenő", "2016.01.24", "2016.06.04", 2);
        addRental("Kopsz Jenő", "2016.01.25", "2016.04.05", 2);
        addRental("Le Töltöm", "2016.11.25", "2016.12.05", 4);
        
    }
    
    
    
}
