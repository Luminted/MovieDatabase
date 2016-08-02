/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Rozsenich Levente
 */
public class Rental {
    
    public final int id;
    private String name;
    private Date startDate;
    private Date endDate;
    public final int movieID;

    public Rental(int id, String name, Date startDate, Date endDate, int movieID) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.movieID = movieID;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.startDate);
        hash = 79 * hash + Objects.hashCode(this.endDate);
        hash = 79 * hash + this.movieID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rental other = (Rental) obj;
        if (!this.name.equals(other.name)) {
            return false;
        }
        if (!this.startDate.equals(other.startDate)) {
            return false;
        }
        if (!this.endDate.equals(other.endDate)) {
            return false;
        }
        if (this.movieID != other.movieID) {
            return false;
        }
        return true;
    }
    
    public Object getRentalValueAtIndex(int index){
        switch(index){
            case 0:
                return name;
            case 1:
                return startDate;
            case 2:
                return endDate;
            case 3:
                return movieID;
        }
        
        return null;
    }

    @Override
    public String toString() {
        return "Rental{" + "name=" + name + ", startDate=" + startDate.toString() + ", endDate=" + endDate.toString() + ", movieID=" + movieID + '}';
    }
    
    
    
}
