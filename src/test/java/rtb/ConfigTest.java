package rtb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConfigTest {
    
    public ConfigTest() {
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
    public void testObjectMapper() {
        System.out.println("objectMapper");
        
        ObjectMapper mapper = new Config().objectMapper();        
        assertNotNull(mapper);
        
        int flags = mapper.getSerializationConfig().getSerializationFeatures();
        assertFalse (SerializationFeature.WRITE_DATES_AS_TIMESTAMPS.enabledIn(flags));
        assertFalse (SerializationFeature.WRITE_DATES_WITH_ZONE_ID.enabledIn(flags));        
    }    
}
