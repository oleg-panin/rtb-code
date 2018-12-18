/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtb.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import rtb.model.Widget;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import rtb.model.SafeWidget;
import rtb.service.EWidgetOperationStatus;
import rtb.service.OperationResultWidget;
import rtb.service.OperationResultWidgetList;

@RunWith(SpringRunner.class)
//@WebMvcTest(WidgetRestController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WidgetRestControllerTest {
    
    @Autowired
    private MockMvc mvc;   
    
    @Autowired
    private ObjectMapper objectMapper; 
        
    public WidgetRestControllerTest() {
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

    protected String getUuidAfterCreateFromResponse (MvcResult res) throws IOException
    {
        String resp = res.getResponse().getContentAsString(); 
        OperationResultWidget orw = objectMapper.readValue (resp, OperationResultWidget.class);        
        SafeWidget swg_new = orw.getWidget();    
        
        String uuid = swg_new.getUuid();
        return uuid;
    }
    
    @Test
    public void testCreateWidget() throws JsonProcessingException, Exception {
        System.out.println("testCreateWidget");
        
        Widget wg = new Widget (10, 20, 110, 120, 0);
        SafeWidget swg = new SafeWidget(wg);

        String jStr = objectMapper.writeValueAsString(wg);        
        String url = "/rtb/widget/";

        MvcResult res = mvc.perform
        (
            post(url)
           .contentType(MediaType.APPLICATION_JSON_UTF8)
           .content(jStr)
        )
        .andExpect(status().isOk())
        .andReturn();

        String responseStr = res.getResponse().getContentAsString();        
        OperationResultWidget orw = objectMapper.readValue (responseStr, OperationResultWidget.class);

        assertNotNull(orw);                
        
        SafeWidget swg_new = orw.getWidget();        
        assertTrue(swg_new.equals(swg));
        
        EWidgetOperationStatus status = orw.getStatus(); 
        assertTrue (EWidgetOperationStatus.CREATED == status);
    }

    @Test
    public void testGetAllWidgets() throws JsonProcessingException, Exception {
        System.out.println("getAllWidgets");
        
        Widget wg = new Widget (10, 20, 110, 120, 0);
        SafeWidget swg = new SafeWidget(wg);

        String jStr = objectMapper.writeValueAsString(wg);        
        String url = "/rtb/widget/";

        MvcResult  
        res = mvc.perform
        (
            post(url)
           .contentType(MediaType.APPLICATION_JSON_UTF8)
           .content(jStr)
        )
        .andExpect(status().isOk())
        .andReturn();
        
        String uuid1 = getUuidAfterCreateFromResponse (res);
        
        wg = new Widget (12, 22, 112, 122, 2);
        jStr = objectMapper.writeValueAsString(wg);  
        
        res = mvc.perform
        (
            post(url)
           .contentType(MediaType.APPLICATION_JSON_UTF8)
           .content(jStr)
        )
        .andExpect(status().isOk())
        .andReturn();
        String uuid2 = getUuidAfterCreateFromResponse (res);
        
        wg = new Widget (11, 21, 111, 121, 1);
        jStr = objectMapper.writeValueAsString(wg);  
        
        res = mvc.perform
        (
            post(url)
           .contentType(MediaType.APPLICATION_JSON_UTF8)
           .content(jStr)
        )
        .andExpect(status().isOk())
        .andReturn();
        String uuid3 = getUuidAfterCreateFromResponse (res);
        
        res = mvc.perform
        (
            get(url)
           .contentType(MediaType.APPLICATION_JSON_UTF8)
           .content("")
        )
        .andExpect(status().isOk())
        .andReturn();
        
        String responseStr = res.getResponse().getContentAsString();        
        OperationResultWidgetList orw = objectMapper.readValue (responseStr, OperationResultWidgetList.class);

        assertNotNull(orw);                
        
        EWidgetOperationStatus status = orw.getStatus(); 
        assertTrue (EWidgetOperationStatus.OK == status);
        
        List<SafeWidget> lst = orw.getWidgetLst(); 
        assertNotNull (lst);
        
        HashMap<String, SafeWidget> hmap = new HashMap<>();
        for (SafeWidget sw: lst)
        {
            hmap.put(sw.getUuid(), sw);
        }
        
        assertTrue(hmap.containsKey(uuid1));
        assertTrue(hmap.containsKey(uuid2));
        assertTrue(hmap.containsKey(uuid3));
    }
    
}
