/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtb.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rtb.Config;

/**
 *
 * @author oleg
 */
public class SafeWidgetTest {
    
    public SafeWidgetTest() {
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
    public void testApplayGeo() throws InterruptedException {
        System.out.println("applayGeo");
        
        Widget wg1 = new Widget(99,88,77,66,1111);
        Widget wg2 = new Widget(1,2,30,40,1111);
        SafeWidget swg1 = new SafeWidget(wg1);
        SafeWidget swg2 = new SafeWidget(wg2);
        
        ZonedDateTime zdt = swg1.getTimeModification();
        assertFalse(swg1.isChanged());
        TimeUnit.SECONDS.sleep(1);
        swg1.applay(swg2);
        assertTrue(swg1.isChanged());
        assertFalse(swg1.getTimeModification().equals(zdt));
        
        assertEquals(swg1.getX(),swg2.getX());
        assertEquals(swg1.getY(),swg2.getY());
        assertEquals(swg1.getWidth(),swg2.getWidth());
        assertEquals(swg1.getHeight(),swg2.getHeight());
    }
        
    @Test
    public void testGetX() {
        System.out.println("getX");
        
        Widget wg1 = new Widget(99,88,77,66,1111);
        SafeWidget swg1 = new SafeWidget(wg1);
        
        assertEquals(swg1.getX(),wg1.getX());
    }

    @Test
    public void testGetY() {
        System.out.println("getY");
        
        Widget wg1 = new Widget(99,88,77,66,1111);
        SafeWidget swg1 = new SafeWidget(wg1);
        
        assertEquals(swg1.getY(),wg1.getY());
    }

    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        
        Widget wg1 = new Widget(99,88,77,66,1111);
        SafeWidget swg1 = new SafeWidget(wg1);
        
        assertEquals(swg1.getWidth(),wg1.getWidth());
    }

    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        
        Widget wg1 = new Widget(99,88,77,66,1111);
        SafeWidget swg1 = new SafeWidget(wg1);
        
        assertEquals(swg1.getHeight(),wg1.getHeight());
    }

    @Test
    public void testGetZindex() {
        System.out.println("getZindex");
        
        Widget wg1 = new Widget(99,88,77,66,1111);
        SafeWidget swg1 = new SafeWidget(wg1);
        
        assertEquals(swg1.getZindex(),wg1.getZindex());
    }

    @Test
    public void testGetUuid() {
        System.out.println("getUuid");        
        
        Widget wg1 = new Widget(99,88,77,66,1111);
        SafeWidget swg1 = new SafeWidget(wg1);
        
        assertTrue(swg1.getUuid().equals(wg1.getUuid()));        
        assertTrue(swg1.getUuid().toLowerCase().equals(wg1.getUuid()));
    }

    @Test
    public void testApplayUuid() {
        System.out.println("applayUuid");   
        
        String uuid = UUID.randomUUID().toString().toLowerCase();
        
        Widget wg1 = new Widget(99,88,77,66,1111);
        SafeWidget swg1 = new SafeWidget(wg1);
        
        swg1.applayUuid(uuid);
        
        assertTrue(swg1.getUuid().equals(uuid));   
    }

   @Test
    public void testApplayZindex() {
        System.out.println("applayZindex");
        
        Widget wg1 = new Widget(99,88,77,66,1111);
        SafeWidget swg1 = new SafeWidget(wg1);
        
        swg1.applayZindex(9);
        
        assertTrue(swg1.getZindex() == 9); 
    }

    @Test
    public void testApplay() throws InterruptedException {
        System.out.println("applay");
        
        Widget wg1 = new Widget(99,88,77,66,1111);
        Widget wg2 = new Widget(1,2,30,40, 22);
        SafeWidget swg1 = new SafeWidget(wg1);
        SafeWidget swg2 = new SafeWidget(wg2);
        
        ZonedDateTime zdt = swg1.getTimeModification();
        assertFalse(swg1.isChanged());
        TimeUnit.SECONDS.sleep(1);
        swg1.applay(swg2);
        assertTrue(swg1.isChanged());
        assertFalse(swg1.getTimeModification().equals(zdt));
        
        assertEquals(swg1.getX(),swg2.getX());
        assertEquals(swg1.getY(),swg2.getY());
        assertEquals(swg1.getWidth(),swg2.getWidth());
        assertEquals(swg1.getHeight(),swg2.getHeight());
        assertEquals(swg1.getZindex(),swg2.getZindex());
    }

    
    @Test
    public void testSerialize() throws JsonProcessingException {
        System.out.println("serialize");
        
        ObjectMapper objMapper = new Config().objectMapper();
        Widget wg1 = new Widget(99,88,77,66,1111);
        wg1.setUuid("191cc5c5-5387-4a41-a361-fa47feb41919");
        
        SafeWidget swg1 = new SafeWidget(wg1);
        
        String result = swg1.serialize(objMapper);
        assertFalse(result.isEmpty());
        assertTrue(result.contains("\"uuid\":\"191cc5c5-5387-4a41-a361-fa47feb41919\""));                
        assertTrue(result.contains("\"zindex\":1111"));                
        assertTrue(result.contains("\"x\":99"));                
        assertTrue(result.contains("\"y\":88"));            
        assertTrue(result.contains("\"width\":77"));            
        assertTrue(result.contains("\"height\":66"));            
        assertTrue(result.contains("\"changed\":false"));   
    }

    
    
}
