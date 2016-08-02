/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierentalproper;

import Model.Rental;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sun.java2d.pipe.SpanShapeRenderer.Simple;

/**
 *
 * @author rrlev_000
 */
public class RentalTest {
    
    public RentalTest() {
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
     * Test of equals method, of class Rental.
     */
    @Test
    public void testEqualsTrue() throws ParseException {
        System.out.println("equals");
        Object obj = new Rental(1, "Bólya Iván", new SimpleDateFormat("yyyy.MM.dd").parse("2016.01.01"), new SimpleDateFormat("yyyy.MM.dd").parse("2016.03.24"), 1);
        Rental instance = new Rental(2, "Bólya Iván", new SimpleDateFormat("yyyy.MM.dd").parse("2016.01.01"), new SimpleDateFormat("yyyy.MM.dd").parse("2016.03.24"), 1);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
        /**
     * Test of equals method, of class Rental.
     */
    @Test
    public void testEqualsFalseName() throws ParseException {
        System.out.println("equals");
        Object obj = new Rental(1, "Bólya Iván", new SimpleDateFormat("yyyy.MM.dd").parse("2016.01.01"), new SimpleDateFormat("yyyy.MM.dd").parse("2016.03.24"), 1);
        Rental instance = new Rental(2, "Blank Zoltán", new SimpleDateFormat("yyyy.MM.dd").parse("2016.01.01"), new SimpleDateFormat("yyyy.MM.dd").parse("2016.03.24"), 1);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
            /**
     * Test of equals method, of class Rental.
     */
    @Test
    public void testEqualsFalseStartDate() throws ParseException {
        System.out.println("equals");
        Object obj = new Rental(1, "Bólya Iván", new SimpleDateFormat("yyyy.MM.dd").parse("2016.01.01"), new SimpleDateFormat("yyyy.MM.dd").parse("2016.03.24"), 1);
        Rental instance = new Rental(1, "Bólya Iván", new SimpleDateFormat("yyyy.MM.dd").parse("2016.02.01"), new SimpleDateFormat("yyyy.MM.dd").parse("2016.03.24"), 1);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
            /**
     * Test of equals method, of class Rental.
     */
    @Test
    public void testEqualsFalseEndDate() throws ParseException {
        System.out.println("equals");
        Object obj = new Rental(1, "Bólya Iván", new SimpleDateFormat("yyyy.MM.dd").parse("2016.01.01"), new SimpleDateFormat("yyyy.MM.dd").parse("2016.03.24"), 1);
        Rental instance = new Rental(1, "Bólya Iván", new SimpleDateFormat("yyyy.MM.dd").parse("2016.01.01"), new SimpleDateFormat("yyyy.MM.dd").parse("2016.12.24"), 1);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
            /**
     * Test of equals method, of class Rental.
     */
    @Test
    public void testEqualsFalseId() throws ParseException {
        System.out.println("equals");
        Object obj = new Rental(1, "Bólya Iván", new SimpleDateFormat("yyyy.MM.dd").parse("2016.01.01"), new SimpleDateFormat("yyyy.MM.dd").parse("2016.03.24"), 1);
        Rental instance = new Rental(1, "Bólya Ivánn", new SimpleDateFormat("yyyy.MM.dd").parse("2016.01.01"), new SimpleDateFormat("yyyy.MM.dd").parse("2016.03.24"), 2);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of getRentalValueAtIndex method, of class Rental.
     */
    @Test
    public void testGetRentalValueAtIndexName() throws ParseException {
        System.out.println("getRentalValueAtIndex");
        int index = 0;
        int id = 1;
        String name = "Bólya Iván";
        Date startDate = new SimpleDateFormat("yyyy.MM.dd").parse("2016.01.01");
        Date endDate = new SimpleDateFormat("yyyy.MM.dd").parse("2016.03.15");
        int m_id = 1;
        Rental instance = new Rental(id, name, startDate, endDate, m_id);
        Object expResult = name;
        Object result = instance.getRentalValueAtIndex(index);
        assertEquals(expResult, result);
    }
    
        /**
     * Test of getRentalValueAtIndex method, of class Rental.
     */
    @Test
    public void testGetRentalValueAtIndexStartDate() throws ParseException {
        System.out.println("getRentalValueAtIndex");
        int index = 1;
        int id = 1;
        String name = "Bólya Iván";
        Date startDate = new SimpleDateFormat("yyyy.MM.dd").parse("2016.01.01");
        Date endDate = new SimpleDateFormat("yyyy.MM.dd").parse("2016.03.15");
        int m_id = 1;
        Rental instance = new Rental(id, name, startDate, endDate, m_id);
        Object expResult = startDate;
        Object result = instance.getRentalValueAtIndex(index);
        assertEquals(expResult, result);
    }
    
        /**
     * Test of getRentalValueAtIndex method, of class Rental.
     */
    @Test
    public void testGetRentalValueAtIndexEndDate() throws ParseException {
        System.out.println("getRentalValueAtIndex");
        int index = 2;
        int id = 1;
        String name = "Bólya Iván";
        Date startDate = new SimpleDateFormat("yyyy.MM.dd").parse("2016.01.01");
        Date endDate = new SimpleDateFormat("yyyy.MM.dd").parse("2016.03.15");
        int m_id = 1;
        Rental instance = new Rental(id, name, startDate, endDate, m_id);
        Object expResult = endDate;
        Object result = instance.getRentalValueAtIndex(index);
        assertEquals(expResult, result);
    }
    
        /**
     * Test of getRentalValueAtIndex method, of class Rental.
     */
    @Test
    public void testGetRentalValueAtIndexId() throws ParseException {
        System.out.println("getRentalValueAtIndex");
        int index = 3;
        int id = 1;
        String name = "Bólya Iván";
        Date startDate = new SimpleDateFormat("yyyy.MM.dd").parse("2016.01.01");
        Date endDate = new SimpleDateFormat("yyyy.MM.dd").parse("2016.03.15");
        int m_id = 1;
        Rental instance = new Rental(id, name, startDate, endDate, m_id);
        Object expResult = m_id;
        Object result = instance.getRentalValueAtIndex(index);
        assertEquals(expResult, result);
    }
    


}
