/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierentalproper;

import Model.Movie;
import java.util.Arrays;
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
public class MovieTest {
    
    public MovieTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of equals method, of class Movie.
     */
    @Test
    public void testEqualsTrueAllTheSame() {
        System.out.println("equals");
        Object obj = new Movie(1, "Hot Fuzz", "Edgard Wright", "Simon Pegg", 2008, true);
        Movie instance = new Movie(1, "Hot Fuzz", "Edgard Wright", "Simon Pegg", 2008, true);;
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
        /**
     * Test of equals method, of class Movie.
     */
    @Test
    public void testEqualsTrueNonMatchingId() {
        System.out.println("equals");
        Object obj = new Movie(3, "Hot Fuzz", "Edgard Wright", "Simon Pegg", 2008, true);
        Movie instance = new Movie(1, "Hot Fuzz", "Edgard Wright", "Simon Pegg", 2008, true);;
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
        /**
     * Test of equals method, of class Movie.
     */
    @Test
    public void testEqualsFalseTitle() {
        System.out.println("equals");
        Object obj = new Movie(1, "The World's End", "Edgard Wright", "Simon Pegg", 2008, true);
        Movie instance = new Movie(1, "Hot Fuzz", "Edgard Wright", "Simon Pegg", 2008, true);;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
        /**
     * Test of equals method, of class Movie.
     */
    @Test
    public void testEqualsFalseDirectors() {
        System.out.println("equals");
        Object obj = new Movie(1, "Hot Fuzz", "Poe Daemon", "Simon Pegg", 2008, true);
        Movie instance = new Movie(1, "Hot Fuzz", "Edgard Wright", "Simon Pegg", 2008, true);;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
        /**
     * Test of equals method, of class Movie.
     */
    @Test
    public void testEqualsFalseActros() {
        System.out.println("equals");
        Object obj = new Movie(1, "Hot Fuzz", "Edgard Wright", "Ham Biscuit", 2008, true);
        Movie instance = new Movie(1, "Hot Fuzz", "Edgard Wright", "Simon Pegg", 2008, true);;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
        /**
     * Test of equals method, of class Movie.
     */
    @Test
    public void testEqualsFalseRelease() {
        System.out.println("equals");
        Object obj = new Movie(1, "Hot Fuzz", "Edgard Wright", "Simon Pegg", 1845, true);
        Movie instance = new Movie(1, "Hot Fuzz", "Edgard Wright", "Simon Pegg", 2008, true);;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEqualsFalseIsLegal() {
        System.out.println("equals");
        Object obj = new Movie(1, "Hot Fuzz", "Edgard Wright", "Simon Pegg", 2008, false);
        Movie instance = new Movie(1, "Hot Fuzz", "Edgard Wright", "Simon Pegg", 2008, true);;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of getValueAtIndex method, of class Movie.
     */
    @Test
    public void testGetValueAtIndexId() {
        System.out.println("getValueAtIndex");
        int index = 0;
        int id = 1;
        String title = "Hot Fuzz";
        String directors = "Edgard Wrigth";
        String actors = "Simon Pegg";
        int release = 2008;
        boolean isLegal = true;
        Movie instance = new Movie(id, title, directors, actors, release, isLegal);
        Object expResult = id;
        Object result = instance.getValueAtIndex(index);
        assertEquals(expResult, result);
    }
    
        /**
     * Test of getValueAtIndex method, of class Movie.
     */
    @Test
    public void testGetValueAtIndexTitle() {
        System.out.println("getValueAtIndex");
        int index = 1;
        int id = 1;
        String title = "Hot Fuzz";
        String directors = "Edgard Wrigth";
        String actors = "Simon Pegg";
        int release = 2008;
        boolean isLegal = true;
        Movie instance = new Movie(id, title, directors, actors, release, isLegal);
        Object expResult = title;
        Object result = instance.getValueAtIndex(index);
        assertEquals(expResult, result);
    }
    
        /**
     * Test of getValueAtIndex method, of class Movie.
     */
    @Test
    public void testGetValueAtIndexDirectors() {
        System.out.println("getValueAtIndex");
        int index = 2;
        int id = 1;
        String title = "Hot Fuzz";
        String directors = "Edgard Wrigth";
        String actors = "Simon Pegg";
        int release = 2008;
        boolean isLegal = true;
        Movie instance = new Movie(id, title, directors, actors, release, isLegal);
        Object expResult = new String[]{"Edgard Wrigth"};
        Object result = instance.getValueAtIndex(index);
        
    }
    
        /**
     * Test of getValueAtIndex method, of class Movie.
     */
    @Test
    public void testGetValueAtIndexActors() {
        System.out.println("getValueAtIndex");
        int index = 3;
        int id = 1;
        String title = "Hot Fuzz";
        String directors = "Edgard Wrigth";
        String actors = "Simon Pegg";
        int release = 2008;
        boolean isLegal = true;
        Movie instance = new Movie(id, title, directors, actors, release, isLegal);
        Object expResult = new String[]{"Simon Pegg"};
        Object result = instance.getValueAtIndex(index);
        assertTrue(Arrays.deepEquals((String[])expResult, (String[])result));
    }
    
        /**
     * Test of getValueAtIndex method, of class Movie.
     */
    @Test
    public void testGetValueAtIndexRelease() {
        System.out.println("getValueAtIndex");
        int index = 4;
        int id = 1;
        String title = "Hot Fuzz";
        String directors = "Edgard Wrigth";
        String actors = "Simon Pegg";
        int release = 2008;
        boolean isLegal = true;
        Movie instance = new Movie(id, title, directors, actors, release, isLegal);
        Object expResult = release;
        Object result = instance.getValueAtIndex(index);
        assertEquals(expResult, result);
    }
    
        /**
     * Test of getValueAtIndex method, of class Movie.
     */
    @Test
    public void testGetValueAtIndexIsLegal() {
        System.out.println("getValueAtIndex");
        int index = 5;
        int id = 1;
        String title = "Hot Fuzz";
        String directors = "Edgard Wrigth";
        String actors = "Simon Pegg";
        int release = 2008;
        boolean isLegal = true;
        Movie instance = new Movie(id, title, directors, actors, release, isLegal);
        Object expResult = isLegal;
        Object result = instance.getValueAtIndex(index);
        assertEquals(expResult, result);
    }
    
}
