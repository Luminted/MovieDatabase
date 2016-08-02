package Model;

import Exceptions.DuplicateMovieException;
import Exceptions.DuplicateRentalException;
import Exceptions.EmptyFieldException;
import Exceptions.InvalidReleaseDateException;
import Exceptions.InvalidStartAndEndDateException;
import Exceptions.NonexistentMovieException;
import UI.GUI;
import java.sql.SQLException;
import java.text.ParseException;

/**
 *
 * @author Rozsenich Levente
 */
public class MovieRentalProper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, InvalidReleaseDateException, DuplicateMovieException, EmptyFieldException, DuplicateRentalException, InvalidStartAndEndDateException, NonexistentMovieException, NumberFormatException, SQLException {
        new GUI().setVisible(true);
    }
    
}
