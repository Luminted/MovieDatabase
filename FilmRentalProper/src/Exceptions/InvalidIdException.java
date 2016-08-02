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
public class InvalidIdException extends Throwable{
    
    public InvalidIdException(){
        super("Nem megfelelő azonosító!");
    }
    
}
