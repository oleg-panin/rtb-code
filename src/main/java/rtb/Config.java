package rtb;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import rtb.service.WidgetOperatorInMemory;
import rtb.storage.WidgetStorageInMemory;

@Configuration
public class Config {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        
        return Jackson2ObjectMapperBuilder
            .json()
            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .featuresToDisable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID)
            .modulesToInstall(new JavaTimeModule())
            .build();
    }
    
    @Bean
    public WidgetStorageInMemory widgetOperator() {
        
        return new WidgetStorageInMemory ();
    }
    
    @Bean
    public WidgetOperatorInMemory widgetOperatorInMemory() {
        
        return new WidgetOperatorInMemory ();
    }
    
}
