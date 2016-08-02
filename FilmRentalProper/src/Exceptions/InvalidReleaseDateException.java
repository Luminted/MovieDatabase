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
public class InvalidReleaseDateException extends Throwable{
    
    public InvalidReleaseDateException(){
        super("A megjelenési éve nem lehet korábbi mint 1800");
    }
}
