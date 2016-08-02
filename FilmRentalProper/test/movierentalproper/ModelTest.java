/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierentalproper;

import Exceptions.DuplicateMovieException;
import Exceptions.DuplicateRentalException;
import Exceptions.EmptyFieldException;
import Exceptions.InvalidReleaseDateException;
import Exceptions.InvalidStartAndEndDateException;
import Exceptions.NonexistentMovieException;
import Model.Cache;
import Model.DatabaseManager;
import Model.Model;
import Model.Movie;
import Model.Rental;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rozsenich Levente
 */
public class ModelTest{
    
    private static Model controllModel;
    
    public ModelTest() throws ParseException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException, NumberFormatException, SQLException{
        controllModel = new Model(new Cache(new DatabaseManager()));
    }
    
    @BeforeClass
    public static void setUpClass() throws SQLException  {
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        for(Movie movie : controllModel.getMovies()){
            controllModel.removeMovie(movie.id);
        }
    }
    
    @Before
    public void setUp() throws SQLException{
        for(Movie movie : controllModel.getMovies()){
            controllModel.removeMovie(movie.id);
        }
    }
    
    @After
    public void tearDown() throws SQLException{
        for(Movie movie : controllModel.getMovies()){
            controllModel.removeMovie(movie.id);
        }
    }

    /**
     * Test of getMovies method, of class Model
     */
    @Test
    public void testGetMovies() throws EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException, NumberFormatException, SQLException, ParseException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException{
        System.out.println("getMovies");
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie("Super", "James Gunn", "Rainn Wilson, Others", 2000, true);
        instance.addMovie("Git", "Allen Smithy", "Harrison Ford", 1985, true);
        instance.addMovie("Pirates Treasure", "long Thong John", "Mateys", 1800, false);
        ArrayList<Movie> expResult = new ArrayList<>();
        expResult.add(new Movie(1, "Super", "James Gunn", "Rainn Wilson, Others", 2000, true));
        expResult.add(new Movie(2, "Git", "Allen Smithy", "Harrison Ford", 1985, true));
        expResult.add(new Movie(3, "Pirates Treasure", "long Thong John", "Mateys", 1800, false));
        ArrayList<Movie> result = instance.getMovies();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getRentals method, of class Model
     */
    @Test
    public void testGetRentals() throws ParseException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException, NumberFormatException, SQLException{
        System.out.println("getRentals");
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie("Super", "James Gunn", "Rainn Wilson, Others", 2000, true);
        instance.addMovie("Git", "Allen Smithy", "Harrison Ford", 1985, true);
        instance.addMovie("Pirates Treasure", "long Thong John", "Mateys", 1800, false);
        instance.addRental("Misi", "2016.01.02", "2016.03.05", 2);
        instance.addRental("Mátyás", "2016.10.22", "2016.12.05", 1);
        instance.addRental("Rózsa", "2016.03.23", "2016.05.28", 2);
        ArrayList expResult = new ArrayList();
        expResult.add(new Rental(1, "Misi", new SimpleDateFormat("yyyy.MM.dd").parse("2016.01.02"), new SimpleDateFormat("yyyy.MM.dd").parse("2016.03.05"), 2));
        expResult.add(new Rental(2, "Mátyás", new SimpleDateFormat("yyyy.MM.dd").parse("2016.10.22"), new SimpleDateFormat("yyyy.MM.dd").parse("2016.12.05"), 1));
        expResult.add(new Rental(3, "Rózsa", new SimpleDateFormat("yyyy.MM.dd").parse("2016.03.23"), new SimpleDateFormat("yyyy.MM.dd").parse("2016.05.28"), 2));
        ArrayList result = instance.getRentals();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of addMovie method, of class Model.
     */
    @Test
    public void testAddMovieProgperUse() throws Exception, ParseException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException {
        String title = "Super";
        String directors = "James Gunn";
        String actors = "Rainn Wilson, Others";
        int release = 1955;
        boolean isLegal = true;
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie(title, directors, actors, release, isLegal);
    }
    
        /**
     * Test of addMovie method, of class Model.
     */
    @Test (expected = EmptyFieldException.class)
    public void testAddMovieEmptyTitle() throws Exception, ParseException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException {
        String title = "";
        String directors = "James Gunn";
        String actors = "Rainn Wilson, Others";
        int release = 1955;
        boolean isLegal = true;
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie(title, directors, actors, release, isLegal);
    }

    
        /**
     * Test of addMovie method, of class Model.
     */
    @Test (expected = EmptyFieldException.class)
    public void testAddMovieEmptyDirectors() throws Exception, ParseException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException {
        String title = "Super";
        String directors = "";
        String actors = "Rainn Wilson, Others";
        int release = 1955;
        boolean isLegal = true;
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie(title, directors, actors, release, isLegal);
    }

    
        /**
     * Test of addMovie method, of class Model.
     */
    @Test (expected = EmptyFieldException.class)
    public void testAddMovieEmptyActors() throws Exception, ParseException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException {
        String title = "Super";
        String directors = "James Gunn";
        String actors = "";
        int release = 1955;
        boolean isLegal = true;
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie(title, directors, actors, release, isLegal);
    }

    
        /**
     * Test of addMovie method, of class Model.
     */
    @Test (expected = InvalidReleaseDateException.class)
    public void testAddMovieTooEarlyReleaseDate() throws Exception, ParseException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException {
        String title = "Super";
        String directors = "James Gunn";
        String actors = "Rainn Wilson, Others";
        int release = 1799;
        boolean isLegal = true;
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie(title, directors, actors, release, isLegal);
    }
    
            /**
     * Test of addMovie method, of class Model.
     */
    @Test (expected = DuplicateMovieException.class)
    public void testAddMovieDuplicateMovie() throws Exception, ParseException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException {
        String title = "Super";
        String directors = "James Gunn";
        String actors = "Rainn Wilson, Others";
        int release = 2000;
        boolean isLegal = true;
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie(title, directors, actors, release, isLegal);
        instance.addMovie(title, directors, actors, release, isLegal);
    }

    /**
     * Test of addRental method, of class Model.
     */
    @Test
    public void testAddRentalProperUse() throws Exception, ParseException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException {
        System.out.println("addRental");
        String name = "Jó Joe";
        String startDate = "2016.01.02";
        String endDate = "2016.05.05";
        int movieID = 1;
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie("Super", "James Gunn", "Rainn Wilson, Others", 1955, true);
        instance.addRental(name, startDate, endDate, movieID);
    }
    
        /**
     * Test of addRental method, of class Model.
     */
    @Test (expected = EmptyFieldException.class)
    public void testAddRentalEmptyName() throws Exception, ParseException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException {
        System.out.println("addRental");
        String name = "";
        String startDate = "2016.01.02";
        String endDate = "2016.05.05";
        int movieID = 1;
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie("Super", "James Gunn", "Rainn Wilson, Others", 2000, true);
        instance.addRental(name, startDate, endDate, movieID);
    }
    
        @Test (expected = EmptyFieldException.class)
    public void testAddRentalEmptyStartDate() throws Exception, ParseException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException {
        System.out.println("addRental");
        String name = "Jó Joe";
        String startDate = "";
        String endDate = "2016.05.05";
        int movieID = 1;
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie("Super", "James Gunn", "Rainn Wilson, Others", 2000, true);
        instance.addRental(name, startDate, endDate, movieID);
    }
    
        @Test (expected = EmptyFieldException.class)
    public void testAddRentalEmptyEndDate() throws Exception, ParseException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException {
        System.out.println("addRental");
        String name = "Jó Joe";
        String startDate = "2016.01.02";
        String endDate = "";
        int movieID = 1;
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie("Super", "James Gunn", "Rainn Wilson, Others", 2000, true);
        instance.addRental(name, startDate, endDate, movieID);
    }
    
        /**
     * Test of addRental method, of class Model.
     */
    @Test (expected = ParseException.class)
    public void testAddRentalInvalidStartDateFormat() throws Exception, ParseException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException {
        System.out.println("addRental");
        String name = "Jó Joe";
        String startDate = "2016:01:02";
        String endDate = "2016.05.05";
        int movieID = 1;
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie("Super", "James Gunn", "Rainn Wilson, Others", 2000, true);
        instance.addRental(name, startDate, endDate, movieID);
    }
    
        /**
     * Test of addRental method, of class Model.
     */
    @Test (expected = ParseException.class)
    public void testAddRentalInvalidEndDateFormat() throws Exception, ParseException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException {
        System.out.println("addRental");
        String name = "Jó Joe";
        String startDate = "2016.01.02";
        String endDate = "2016:05:05";
        int movieID = 1;
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie("Super", "James Gunn", "Rainn Wilson, Others", 2000, true);
        instance.addRental(name, startDate, endDate, movieID);
    }
    
        /**
     * Test of addRental method, of class Model.
     */
    @Test (expected = InvalidStartAndEndDateException.class)
    public void testAddRentalTooEarlyEndDate() throws Exception, ParseException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException {
        System.out.println("addRental");
        String name = "Jó Joe";
        String startDate = "2016.05.02";
        String endDate = "2016.01.05";
        int movieID = 1;
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie("Super", "James Gunn", "Rainn Wilson, Others", 2000, true);
        instance.addRental(name, startDate, endDate, movieID);
    }
    
            /**
     * Test of addRental method, of class Model.
     */
    @Test (expected = NonexistentMovieException.class)
    public void testAddRentalNonExistentMovie() throws Exception, ParseException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException {
        System.out.println("addRental");
        String name = "Jó Joe";
        String startDate = "2016.01.02";
        String endDate = "2016.05.05";
        int movieID = 3;
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie("Super", "James Gunn", "Rainn Wilson, Others", 2000, true);
        instance.addRental(name, startDate, endDate, movieID);
    }
    
                /**
     * Test of addRental method, of class Model.
     */
    @Test (expected = DuplicateRentalException.class)
    public void testAddRentalDuplicateRental() throws Exception, ParseException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException {
        System.out.println("addRentalDuplicate");
        String name = "Jó Joe";
        String startDate = "2016.01.02";
        String endDate = "2016.05.05";
        int movieID = 1;
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie("Super", "James Gunn", "Rainn Wilson, Others", 2000, true);
        instance.addRental(name, startDate, endDate, movieID);
        instance.addRental(name, startDate, endDate, movieID);
    }

    /**
     * Test of getLegalMovies method, of class Model.
     */
    @Test
    public void testGetLegalMovies() throws ParseException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException, NumberFormatException, SQLException {
        System.out.println("getLegalMovies");
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie("Super", "James Gunn", "Rainn Wilson, Others", 2000, true);
        instance.addMovie("Git", "Allen Smithy", "Harrison Ford", 1985, true);
        instance.addMovie("Pirates Treasure", "long Thong John", "Mateys", 1800, false);
        ArrayList<Movie> expResult = new ArrayList<Movie>();
        expResult.add(new Movie(1, "Super", "James Gunn", "Rainn Wilson, Others", 2000, true));
        expResult.add(new Movie(1, "Git", "Allen Smithy", "Harrison Ford", 1985, true));
        ArrayList<Movie> result = instance.getLegalMovies();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMovieById method, of class Model.
     */
    @Test
    public void testGetMovieById() throws ParseException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException, NumberFormatException, SQLException {
        System.out.println("getMovieById");
        int id = 3;
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie("Super", "James Gunn", "Rainn Wilson, Others", 2000, true);
        instance.addMovie("Git", "Allen Smithy", "Harrison Ford", 1985, true);
        instance.addMovie("Pirates Treasure", "long Thong John", "Mateys", 1800, false);
        Movie expResult = new Movie(2, "Pirates Treasure", "long Thong John", "Mateys", 1800, false);
        Movie result = instance.getMovieById(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getMovieRentalHistory method, of class Model.
     */
    @Test
    public void testGetMovieRentalHistory() throws ParseException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException, SQLException {
        System.out.println("getMovieRentalHistory");
        int id = 2;
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie("Super", "James Gunn", "Rainn Wilson, Others", 2000, true);
        instance.addMovie("Git", "Allen Smithy", "Harrison Ford", 1985, true);
        instance.addMovie("Pirates Treasure", "long Thong John", "Mateys", 1800, false);
        instance.addRental("Misi", "2016.01.02", "2016.03.05", 2);
        instance.addRental("Mátyás", "2016.10.22", "2016.12.05", 1);
        instance.addRental("Rózsa", "2016.03.23", "2016.05.28", 2);
        ArrayList expResult = new ArrayList();
        expResult.add(new Rental(1, "Misi", new SimpleDateFormat("yyyy.MM.dd").parse("2016.01.02"), new SimpleDateFormat("yyyy.MM.dd").parse("2016.03.05"), 2));
        expResult.add(new Rental(2, "Rózsa", new SimpleDateFormat("yyyy.MM.dd").parse("2016.03.23"), new SimpleDateFormat("yyyy.MM.dd").parse("2016.05.28"), 2));
        ArrayList result = instance.getMovieRentalHistory(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getMovieRentalHistoryValueAtIndex method, of class Model.
     */
    @Test
    public void testGetMovieRentalHistoryValueAtIndexCheckIfReturnsString1() throws ParseException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException, NumberFormatException, SQLException {
        System.out.println("getMovieRentalHistoryValueAtIndex");
        int id = 2;
        int rowIndex = 1;
        int colIndex = 2;
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie("Super", "James Gunn", "Rainn Wilson, Others", 2000, true);
        instance.addMovie("Git", "Allen Smithy", "Harrison Ford", 1985, true);
        instance.addMovie("Pirates Treasure", "long Thong John", "Mateys", 1800, false);
        instance.addRental("Misi", "2016.01.02", "2016.03.05", 2);
        instance.addRental("Mátyás", "2016.10.22", "2016.12.05", 1);
        instance.addRental("Rózsa", "2016.03.23", "2016.05.28", 2);
        Object result = instance.getMovieRentalHistoryValueAtIndex(id, rowIndex, colIndex);
        assertTrue(result instanceof String);
    }
    
        /**
     * Test of getMovieRentalHistoryValueAtIndex method, of class Model.
     */
    @Test
    public void testGetMovieRentalHistoryValueAtIndexCheckIfReturnsString2() throws ParseException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException, NumberFormatException, SQLException {
        System.out.println("getMovieRentalHistoryValueAtIndex");
        int id = 2;
        int rowIndex = 1;
        int colIndex = 1;
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie("Super", "James Gunn", "Rainn Wilson, Others", 2000, true);
        instance.addMovie("Git", "Allen Smithy", "Harrison Ford", 1985, true);
        instance.addMovie("Pirates Treasure", "long Thong John", "Mateys", 1800, false);
        instance.addRental("Misi", "2016.01.02", "2016.03.05", 2);
        instance.addRental("Mátyás", "2016.10.22", "2016.12.05", 1);
        instance.addRental("Rózsa", "2016.03.23", "2016.05.28", 2);
        Object result = instance.getMovieRentalHistoryValueAtIndex(id, rowIndex, colIndex);
        assertTrue(result instanceof String);
    }

    /**
     * Test of getLegalRentals method, of class Model.
     */
    @Test
    public void testGetLegalRentals() throws ParseException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException, NumberFormatException, SQLException {
        System.out.println("getLegalRentals");
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie("Super", "James Gunn", "Rainn Wilson, Others", 2000, true);
        instance.addMovie("Git", "Allen Smithy", "Harrison Ford", 1985, true);
        instance.addMovie("Pirates Treasure", "long Thong John", "Mateys", 1800, false);
        instance.addRental("Misi", "2016.01.02", "2016.03.05", 2);
        instance.addRental("Mátyás", "2016.10.22", "2016.12.05", 3);
        instance.addRental("Rózsa", "2016.03.23", "2016.05.28", 2);
        ArrayList expResult = new ArrayList();
        expResult.add(new Rental(1, "Misi", new SimpleDateFormat("yyyy.MM.dd").parse("2016.01.02"), new SimpleDateFormat("yyyy.MM.dd").parse("2016.03.05"), 2));
        expResult.add(new Rental(2, "Rózsa", new SimpleDateFormat("yyyy.MM.dd").parse("2016.03.23"), new SimpleDateFormat("yyyy.MM.dd").parse("2016.05.28"), 2));
        ArrayList<Rental> result = instance.getLegalRentals();
        assertEquals(expResult, result);
    }

    /**
     * Test of removeMovie method, of class Model.
     */
    @Test
    public void testRemoveMovie() throws ParseException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException, NumberFormatException, SQLException {
        System.out.println("removeMovie");
        int index = 1;
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie("Super", "James Gunn", "Rainn Wilson, Others", 2000, true);
        instance.addMovie("Git", "Allen Smithy", "Harrison Ford", 1985, true);
        instance.addMovie("Pirates Treasure", "long Thong John", "Mateys", 1800, false);
        int expResult = instance.getMovies().size()-1;
        instance.removeMovie(index);
        int result = instance.getMovies().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of removeRental method, of class Model.
     */
    @Test
    public void testRemoveRental() throws ParseException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException, NumberFormatException, SQLException {
        System.out.println("removeRental");
        int index = 1;
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie("Super", "James Gunn", "Rainn Wilson, Others", 2000, true);
        instance.addMovie("Git", "Allen Smithy", "Harrison Ford", 1985, true);
        instance.addMovie("Pirates Treasure", "long Thong John", "Mateys", 1800, false);
        instance.addRental("Misi", "2016.01.02", "2016.03.05", 2);
        instance.addRental("Mátyás", "2016.10.22", "2016.12.05", 3);
        instance.addRental("Rózsa", "2016.03.23", "2016.05.28", 2);
        int expResult = instance.getRentals().size() - 1;
        instance.removeRental(index);
        int result = instance.getRentals().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of isRented method, of class Model.
     */
    @Test
    public void testIsRentedTrue() throws ParseException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException, SQLException {
        System.out.println("isRented");
        int index = 1;
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie("Super", "James Gunn", "Rainn Wilson, Others", 2000, true);
        instance.addMovie("Git", "Allen Smithy", "Harrison Ford", 1985, true);
        instance.addMovie("Pirates Treasure", "long Thong John", "Mateys", 1800, false);
        instance.addRental("Misi", "2016.01.02", "2016.03.05", 2);
        instance.addRental("Mátyás", "2016.10.22", "2016.12.05", 3);
        instance.addRental("Rózsa", "2016.03.23", "2016.05.28", 2);
        boolean expResult = false;
        boolean result = instance.isRented(index);
    }
    
        /**
     * Test of isRented method, of class Model.
     */
    @Test
    public void testIsRentedFalse() throws ParseException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException, NonexistentMovieException, DuplicateRentalException, InvalidStartAndEndDateException, NumberFormatException, SQLException {
        System.out.println("isRented");
        int index = 0;
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie("Super", "James Gunn", "Rainn Wilson, Others", 2000, true);
        instance.addMovie("Git", "Allen Smithy", "Harrison Ford", 1985, true);
        instance.addMovie("Pirates Treasure", "long Thong John", "Mateys", 1800, false);
        instance.addRental("Misi", "2016.01.02", "2016.03.05", 2);
        instance.addRental("Mátyás", "2016.10.22", "2016.12.05", 3);
        instance.addRental("Rózsa", "2016.03.23", "2016.05.28", 2);
        boolean expResult = false;
        boolean result = instance.isRented(index);
        assertEquals(expResult, result);
    }

    /**
     * Test of isRentalOverdue method, of class Model.
     */
    @Test
    public void testIsRentalOverdueTrue() throws ParseException, NonexistentMovieException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException, DuplicateRentalException, InvalidStartAndEndDateException, SQLException {
        System.out.println("isRentalOverdue");
        int index = 0;
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie("Super", "James Gunn", "Rainn Wilson, Others", 2000, true);
        instance.addRental("Misi", "2016.01.02", "2016.03.05", 1);
        boolean expResult = true;
        boolean result = instance.isRentalOverdue(index);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isRentalOverdue method, of class Model.
     */
    @Test
    public void testIsRentalOverdueFalse() throws ParseException, NonexistentMovieException, EmptyFieldException, InvalidReleaseDateException, DuplicateMovieException, DuplicateRentalException, InvalidStartAndEndDateException, SQLException {
        System.out.println("isRentalOverdue");
        int index = 0;
        Model instance = new Model(new Cache(new DatabaseManager()));
        instance.addMovie("Super", "James Gunn", "Rainn Wilson, Others", 2000, true);
        instance.addRental("Misi", "2016.01.02", "2016.10.05", 1);
        boolean expResult = false;
        boolean result = instance.isRentalOverdue(index);
        assertEquals(expResult, result);
    }
    
}
