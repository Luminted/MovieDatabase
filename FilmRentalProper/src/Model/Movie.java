/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author Rozsenich Levente
 */
public class Movie {
    
    public final int id;
    private String title;
    private String[] directors;
    private String[] actors;
    private int release;
    private boolean isLegal;

    public Movie(int id, String title, String directors, String actors, int release, boolean isLegal) {
        this.id = id;
        this.title = title;
        this.directors = commaSeparatedStringToArray(directors);
        this.actors = commaSeparatedStringToArray(actors);
        this.release = release;
        this.isLegal = isLegal;
    }
    
    public Movie(int id, String title, String[] directors, String[] actors, int release, boolean isLegal) {
        this.id = id;
        this.title = title;
        this.directors = directors;
        this.actors = actors;
        this.release = release;
        this.isLegal = isLegal;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.id;
        hash = 17 * hash + Objects.hashCode(this.title);
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
        final Movie other = (Movie) obj;
        if (!this.title.equals(other.title)) {
            return false;
        }
        if (!Arrays.deepEquals(this.directors, other.directors)) {
            return false;
        }
        if (!Arrays.deepEquals(this.actors, other.actors)) {
            return false;
        }
        if (this.release != other.release) {
            return false;
        }
        if (this.isLegal != other.isLegal) {
            return false;
        }
        return true;
    }
    
    public boolean isLegal(){
        return this.isLegal;
    }
    
    public Object getValueAtIndex(int index){
        switch(index){
            case 0:
                return this.id;
            case 1:
                return this.title;
            case 2:
                return this.directors;
            case 3:
                return this.actors;
            case 4: 
                return this.release;
            case 5:
                return this.isLegal;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", title=" + title + ", directors=" + Arrays.toString(directors) + ", actors=" + Arrays.toString(actors) + ", release=" + release + ", isLegal=" + isLegal + '}';
    }
    
    public String actorsToString(){
        return Arrays.toString(actors).substring(1, Arrays.toString(actors).length()-1);
    }
    
    public String directorsToString(){
        return Arrays.toString(directors).substring(1, Arrays.toString(directors).length()-1);
    }
   
    private String[] commaSeparatedStringToArray(String stringToProcess){
        String[] array = stringToProcess.split(",");
        
        for(int i = 0; i < array.length; ++i){
            array[i] = array[i].trim();
        }
        return array;
    }
    
}
