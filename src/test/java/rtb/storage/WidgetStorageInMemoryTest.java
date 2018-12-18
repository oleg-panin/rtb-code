/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtb.storage;

import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rtb.model.SafeWidget;
import rtb.model.Widget;
import rtb.service.EWidgetOperationStatus;
import rtb.service.OperationResultWidgetList;

/**
 *
 * @author oleg
 */
public class WidgetStorageInMemoryTest {
    
    ConcurrentSkipListMap <String, SafeWidget>  map;
    WidgetStorageInMemory storage;
    
    public WidgetStorageInMemoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        map = new ConcurrentSkipListMap <> ();
        storage = new WidgetStorageInMemory(map);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCreate() {
        System.out.println("create");        
        
        int idx_insert = 51;
        int idx_hobbit_1 = 50;
        int idx_hobbit_2 = 51;
        int idx_hobbit_3 = 52;
        
        Widget wg_insert = new Widget (10, 20, 110, 120, idx_insert);
        Widget wg_hobbit_1 = new Widget (10, 20, 110, 120, idx_hobbit_1);
        Widget wg_hobbit_2 = new Widget (10, 20, 110, 120, idx_hobbit_2);
        Widget wg_hobbit_3 = new Widget (10, 20, 110, 120, idx_hobbit_3);
        
        SafeWidget swg_insert = new SafeWidget (wg_insert);
        SafeWidget swg_hobbit_1 = new SafeWidget (wg_hobbit_1);
        SafeWidget swg_hobbit_2 = new SafeWidget (wg_hobbit_2);
        SafeWidget swg_hobbit_3 = new SafeWidget (wg_hobbit_3);
        
        String uuid_insert = wg_insert.getUuid();
        String uuid_hobbit_1 = wg_hobbit_1.getUuid();
        String uuid_hobbit_2 = wg_hobbit_2.getUuid();
        String uuid_hobbit_3 = wg_hobbit_3.getUuid();
        
        map.put(swg_hobbit_1.getUuid(), swg_hobbit_1);
        map.put(swg_hobbit_2.getUuid(), swg_hobbit_2);
        map.put(swg_hobbit_3.getUuid(), swg_hobbit_3);
        
            storage.create(swg_insert);
            
        assertTrue (wg_insert.getZindex()   == idx_insert);        
        assertTrue (wg_hobbit_1.getZindex() == idx_hobbit_1);
        assertTrue (wg_hobbit_2.getZindex() == idx_hobbit_2 + 1);
        assertTrue (wg_hobbit_3.getZindex() == idx_hobbit_3 + 1);
        
        assertTrue (map.size() == 4);
        assertFalse (map.containsKey(uuid_insert));
        assertTrue (map.containsKey(uuid_hobbit_1));
        assertTrue (map.containsKey(uuid_hobbit_2));
        assertTrue (map.containsKey(uuid_hobbit_3));
    }

    @Test
    public void testDelete() {
        System.out.println("delete");        
        
        int idx_hobbit_1 = 50;
        int idx_hobbit_2 = 51;
        int idx_hobbit_3 = 52;
        
        Widget wg_hobbit_1 = new Widget (10, 20, 110, 120, idx_hobbit_1);
        Widget wg_hobbit_2 = new Widget (10, 20, 110, 120, idx_hobbit_2);
        Widget wg_hobbit_3 = new Widget (10, 20, 110, 120, idx_hobbit_3);
        
        SafeWidget swg_hobbit_1 = new SafeWidget (wg_hobbit_1);
        SafeWidget swg_hobbit_2 = new SafeWidget (wg_hobbit_2);
        SafeWidget swg_hobbit_3 = new SafeWidget (wg_hobbit_3);
        
        String uuid_hobbit_1 = wg_hobbit_1.getUuid();
        String uuid_hobbit_2 = wg_hobbit_2.getUuid();
        String uuid_hobbit_3 = wg_hobbit_3.getUuid();
        
        map.put(swg_hobbit_1.getUuid(), swg_hobbit_1);
        map.put(swg_hobbit_2.getUuid(), swg_hobbit_2);
        map.put(swg_hobbit_3.getUuid(), swg_hobbit_3);
        
            storage.delete(uuid_hobbit_2);
            
        assertTrue (wg_hobbit_1.getZindex() == idx_hobbit_1);
        assertTrue (wg_hobbit_3.getZindex() == idx_hobbit_3);        
        
        assertTrue (map.size() == 2);
        assertTrue (map.containsKey(uuid_hobbit_1));
        assertTrue (map.containsKey(uuid_hobbit_3));
    }

    @Test
    public void testModify() {
        System.out.println("modify");
        
        int idx_modify = 53;
        int idx_hobbit_1 = 50;
        int idx_hobbit_2 = 51;
        int idx_hobbit_3 = 52;
        
        Widget wg_modify = new Widget (111, 222, 333, 444, idx_modify);
        Widget wg_hobbit_1 = new Widget (10, 20, 110, 120, idx_hobbit_1);
        Widget wg_hobbit_2 = new Widget (10, 20, 110, 120, idx_hobbit_2);
        Widget wg_hobbit_3 = new Widget (10, 20, 110, 120, idx_hobbit_3);
        
        String uuid_modify = wg_modify.getUuid();
        String uuid_hobbit_1 = wg_hobbit_1.getUuid();
        String uuid_hobbit_2 = wg_hobbit_2.getUuid();
        String uuid_hobbit_3 = wg_hobbit_3.getUuid();
        
        SafeWidget swg_modify = new SafeWidget (wg_modify);
        SafeWidget swg_hobbit_1 = new SafeWidget (wg_hobbit_1);
        SafeWidget swg_hobbit_2 = new SafeWidget (wg_hobbit_2);
        SafeWidget swg_hobbit_3 = new SafeWidget (wg_hobbit_3);
        
        map.put(swg_hobbit_1.getUuid(), swg_hobbit_1);
        map.put(swg_hobbit_2.getUuid(), swg_hobbit_2);
        map.put(swg_hobbit_3.getUuid(), swg_hobbit_3);
        
        wg_modify.setUuid(uuid_hobbit_2);
        
            storage.modify(swg_modify);      
        
        assertTrue (wg_hobbit_1.getZindex() == idx_hobbit_1);
        assertTrue (wg_hobbit_2.getZindex() == idx_modify);
        assertTrue (wg_hobbit_3.getZindex() == idx_hobbit_3);
        
        assertTrue (map.size() == 3);
        assertTrue (map.containsKey(uuid_hobbit_1));
        assertFalse (map.containsKey(uuid_modify));
        assertTrue (map.containsKey(uuid_hobbit_2));
        assertTrue (map.containsKey(uuid_hobbit_3));
    }

    @Test
    public void testGetWidgetsInsideRect() {
        System.out.println("getWidgetsInsideRect");

            Widget wg_container = new Widget (0, 0, 150, 200, 0);
            Widget wg1 = new Widget(50, 50, 100, 100, 1);
            Widget wg2 = new Widget(50, 100, 100, 100, 2);
            Widget wg3 = new Widget(100, 100, 100, 100, 3);
            
            SafeWidget swg_container = new SafeWidget (wg_container);
            SafeWidget swg1 = new SafeWidget (wg1);
            SafeWidget swg2 = new SafeWidget (wg2);
            SafeWidget swg3 = new SafeWidget (wg3);
            
            map.put(swg1.getUuid(), swg1);
            map.put(swg2.getUuid(), swg2);
            map.put(swg3.getUuid(), swg3);
            
                OperationResultWidgetList oper = storage. getWidgetsInsideRect(swg_container);    
            
            assertEquals(EWidgetOperationStatus.OK, oper.getStatus());
            
            List<SafeWidget> lst = oper.getWidgetLst();
                      
            assertNotNull(lst);
            assertEquals(lst.size(), 2);
            
            SafeWidget 
            wFound = map.get(lst.get(0).getUuid());
            assertNotNull (wFound);
            assertTrue (wFound.equals(swg1));
            
            wFound = map.get(lst.get(1).getUuid());
            assertNotNull (wFound);
            assertTrue (wFound.equals(swg2));
    }    
}
