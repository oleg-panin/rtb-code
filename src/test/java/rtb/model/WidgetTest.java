/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtb.model;

import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author oleg
 */
public class WidgetTest {
    
    public WidgetTest() {
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
    
    @Test
    public void testCalcRightX() {
        System.out.println("calcRightX");
        Widget instance = new Widget(10, 20, 1, 2, 1111);
        
        int result = instance.calcRightX();
        int expResult = 11;
        assertEquals(expResult, result);
    }

    @Test
    public void testCalcBottomY() {
        System.out.println("calcBottomY");
        Widget instance = new Widget(10, 20, 1, 2, 1111);
        
        int expResult = 22;
        int result = instance.calcBottomY();
        assertEquals(expResult, result);
    }

    @Test
    public void testContains() {
        System.out.println("contains");
        Widget wg_container = new Widget (0, 0, 150, 200, 0);
        Widget wg1 = new Widget(50, 50, 100, 100, 1);
        Widget wg2 = new Widget(50, 100, 100, 100, 2);
        Widget wg3 = new Widget(100, 100, 100, 100, 3);
        
        assertTrue(wg_container.contains(wg1));
        assertTrue(wg_container.contains(wg2));
        assertFalse(wg_container.contains(wg3));
    }

    @Test
    public void testCtors() {
        System.out.println("testCtors");
        Widget wg = new Widget(1, 2, 3, 4, 5);
        
        assertEquals(wg.getX(), 1);
        assertEquals(wg.getY(), 2);
        assertEquals(wg.getWidth(), 3);
        assertEquals(wg.getHeight(), 4);
        assertEquals(wg.getZindex(), 5);
        assertNotNull(wg.getTimeModification());
        assertFalse(wg.getUuid().isEmpty());
        
        String uuid = UUID.randomUUID().toString().toLowerCase();
        ZonedDateTime timeModification = ZonedDateTime.now();
        
        wg = new Widget(1, 2, 3, 4, 5, uuid, timeModification);
        
        assertEquals(wg.getX(), 1);
        assertEquals(wg.getY(), 2);
        assertEquals(wg.getWidth(), 3);
        assertEquals(wg.getHeight(), 4);
        assertEquals(wg.getZindex(), 5);
        assertNotNull(wg.getTimeModification());
        assertFalse(wg.getUuid().isEmpty());
        assertEquals(wg.getTimeModification(), timeModification);
    }
}
