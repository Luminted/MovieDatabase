/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Rozsenich Levente
 */
public class NonexistentMovieException extends Throwable{
    
    public NonexistentMovieException(){
        super("Nincs ilyen film az adatbázisban!");
    }
    
}
