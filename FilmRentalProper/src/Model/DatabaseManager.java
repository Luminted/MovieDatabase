/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Rozsenich Levente
 */
public class DatabaseManager {

    private static String url = "jdbc:derby://localhost:1527/MovieRental";
    
    public DatabaseManager() {
    }
    
    private Connection getConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url, "root", "root");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Hiba történt az adatbázis elérésekor.");
            e.printStackTrace();
        }
        return connection;
    }

    public ArrayList<Movie> getMovies() throws SQLException {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        
        Statement movieStatement;
        ResultSet movieResult;
        Connection conn = getConnection();
        String query = "SELECT * FROM MOVIES";
        
        movieStatement = conn.createStatement();
        movieResult = movieStatement.executeQuery(query);
        
        while(movieResult.next()){
            Statement parseStatement = conn.createStatement();
            int movieID = movieResult.getInt("M_ID");
            String title = movieResult.getString("TITLE");
            int release = movieResult.getInt("RELEASE");
            boolean legal = movieResult.getBoolean("LEGAL");
            
            query = "SELECT DIRECTORS.NAME FROM DIRECTORS LEFT JOIN MOVIEDIRECTORS ON DIRECTORS.D_ID = MOVIEDIRECTORS.D_ID WHERE MOVIEDIRECTORS.M_ID = " + movieID;
            ResultSet directorNames = parseStatement.executeQuery(query);
            
            ArrayList<String> directors = new ArrayList<>();
            while(directorNames.next()){
                directors.add(directorNames.getString("NAME"));
            }
            
            query = "SELECT NAME FROM ACTORS LEFT JOIN MOVIEACTORS ON ACTORS.A_ID = MOVIEACTORS.A_ID WHERE MOVIEACTORS.M_ID = " + movieID;
            ResultSet actorNames = parseStatement.executeQuery(query);
            
            ArrayList<String> actors = new ArrayList<>();
            while(actorNames.next()){
                actors.add(actorNames.getString("NAME"));
            }
            
            movies.add(new Movie(movieID, title, directors.toArray(new String[directors.size()]), actors.toArray(new String[actors.size()]), release, legal));
        }
        
        return movies;
    }

    public ArrayList<Rental> getRentals() throws SQLException {
        ArrayList<Rental> rentals = new ArrayList<Rental>();
        
        Statement statement;
        ResultSet result;
        Connection conn = getConnection();
        String query = "SELECT * FROM RENTALS";
        
        statement = conn.createStatement();
        result = statement.executeQuery(query);
        
        while(result.next()){
            rentals.add(new Rental(result.getInt("R_ID"), result.getString("NAME"), result.getDate("START_DATE"), result.getDate("END_DATE"), result.getInt("M_ID")));
        }
        
        return rentals;
    } 
    
    public void addMovie(Movie newMovie) throws SQLException{
        int movieID = newMovie.id;
        String title = (String) newMovie.getValueAtIndex(1);
        int release = (int) newMovie.getValueAtIndex(4); 
        boolean legal = (boolean) newMovie.getValueAtIndex(5);
        
        String query = "INSERT INTO MOVIES (M_ID, TITLE, RELEASE, LEGAL) VALUES( " + movieID + ", '" + title + "', " + release + ", " + legal + ")";
        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        statement.executeUpdate(query);
        
        ArrayList<String> newActors = getNewActors((String[]) newMovie.getValueAtIndex(3));
        ArrayList<String> newDirectors = getNewDirectors((String[]) newMovie.getValueAtIndex(2));
        
        commitNewActors(newActors);
        commitNewDirectors(newDirectors);
        
        updateActorsJunction((String[]) newMovie.getValueAtIndex(3), movieID);
        updateDirectorsJunction((String[]) newMovie.getValueAtIndex(2), movieID);
        
    }
    
    public void addRental(Rental newRental) throws SQLException, ParseException{
        int r_id = newRental.id;
        String name = (String) newRental.getRentalValueAtIndex(0);
        String startDate = new SimpleDateFormat("yyyy.MM.dd").format((Date)newRental.getRentalValueAtIndex(1));
        String endDate = new SimpleDateFormat("yyyy.MM.dd").format((Date)newRental.getRentalValueAtIndex(2));
        int m_id = (int) newRental.getRentalValueAtIndex(3);
        
        java.sql.Date SQLstartDate = new java.sql.Date(new SimpleDateFormat("yyyy.MM.dd").parse(startDate).getTime());
        java.sql.Date SQLendDate = new java.sql.Date(new SimpleDateFormat("yyyy.MM.dd").parse(endDate).getTime());
        
        String query = "INSERT INTO RENTALS(R_ID, M_ID, NAME, START_DATE, END_DATE) VALUES(?,?,?,?,?)";
        
        Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, r_id);
        statement.setInt(2, m_id);
        statement.setString(3, name);
        statement.setDate(4, SQLstartDate);
        statement.setDate(5, SQLendDate);
        
        statement.executeUpdate();
    }
    
    public void removeMovie(int id) throws SQLException{
        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        String query = "DELETE FROM RENTALS WHERE M_ID = " + id;
        statement.executeUpdate(query);
        
        query = "DELETE FROM MOVIEACTORS WHERE M_ID = " + id;
        statement.executeUpdate(query);
        
        query = "DELETE FROM MOVIEDIRECTORS WHERE M_ID = " + id;
        statement.executeUpdate(query);
        
        query = "DELETE FROM MOVIES WHERE M_ID = " + id;
        statement.executeUpdate(query);
    }
    
    public void removeRental(int id) throws SQLException{
        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        String query = "DELETE FROM RENTALS WHERE R_ID =" + id;
        
        statement.executeUpdate(query);
    }
    
    private ArrayList<String> getNewDirectors(String[] newMovieDirectors) throws SQLException{
        ArrayList<String> resultDirectors = new ArrayList<>(Arrays.asList(newMovieDirectors));
        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        String query = "SELECT NAME FROM DIRECTORS";
        ResultSet result = statement.executeQuery(query);
        
        while(result.next()){
            for(String newDirector : newMovieDirectors){
                if(result.getString("NAME").equals(newDirector)){
                    resultDirectors.remove(newDirector);
                }
            }
        }
        
        return resultDirectors;
    }
    
    private ArrayList<String> getNewActors(String[] newMovieActors) throws SQLException{
        ArrayList<String> resultActors =new ArrayList<String>(Arrays.asList(newMovieActors));
        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        String query = "SELECT NAME FROM ACTORS";
        ResultSet result = statement.executeQuery(query);
        
        while(result.next()){
            for(String newActor : newMovieActors){
                if(result.getString("NAME").equals(newActor)){
                    resultActors.remove(newActor);
                }
            }
        }
        return resultActors;
    }
    
    private void commitNewActors(ArrayList<String> actors) throws SQLException{
        Connection conn = getConnection();
        
        for(String actor : actors){
            Statement statement = conn.createStatement();
            String query = "INSERT INTO ACTORS (NAME) VALUES ( '" + actor + "')";
            statement.executeUpdate(query);
        }
    }
    
    private void commitNewDirectors(ArrayList<String> directors) throws SQLException{
        Connection conn = getConnection();
        
        for(String director: directors){
            Statement statement = conn.createStatement();
            String query = "INSERT INTO DIRECTORS (NAME) VALUES ( '" + director + "')";
            statement.executeUpdate(query);
        }
    }
    
    private void updateDirectorsJunction(String[] directors, int movieID) throws SQLException{
        Connection conn = getConnection();
        String query = "SELECT * FROM DIRECTORS";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(query);
        
        ArrayList<Integer> directorsToUpdateIDs = new ArrayList<>();
        while(result.next()){
            for(String director : directors){
                if(result.getString("NAME").equals(director)){
                    directorsToUpdateIDs.add(result.getInt("D_ID"));
                }
            }
        }
        
        for(int id : directorsToUpdateIDs){
            Statement updateStatement = conn.createStatement();
            query = "INSERT INTO MOVIEDIRECTORS(M_ID, D_ID) VALUES(" + movieID + "," + id + ")";
            updateStatement.executeUpdate(query);
        }
        
    }
    
    private void updateActorsJunction(String[] actors, int movieID) throws SQLException{
        Connection conn = getConnection();
        String query = "SELECT * FROM ACTORS";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(query);
        
        ArrayList<Integer> actorsToUpdateIDs = new ArrayList<>();
        while(result.next()){
            for(String actor : actors){
                if(result.getString("NAME").equals(actor)){
                    actorsToUpdateIDs.add(result.getInt("A_ID"));
                }
            }
        }
        
        for(int id : actorsToUpdateIDs){
            Statement updateStatement = conn.createStatement();
            query = "INSERT INTO MOVIEACTORS(M_ID, A_ID) VALUES(" + movieID + "," + id + ")";
            updateStatement.executeUpdate(query);
        }
    }
    
}
