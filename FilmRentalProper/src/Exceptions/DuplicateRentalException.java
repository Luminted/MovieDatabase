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
public class DuplicateRentalException extends Throwable{
    
    public DuplicateRentalException(){
        super("Ez a kölcsönzés már szerepel az adatbázisban");
    }
    
}
