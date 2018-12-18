package rtb.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class SafeWidgetDeserializer extends StdDeserializer<SafeWidget> { 

    public SafeWidgetDeserializer() { 
        this(null); 
    } 

    public SafeWidgetDeserializer(Class<?> vc) { 
        super(vc); 
    }

    @Override
    public SafeWidget deserialize(JsonParser jp, DeserializationContext ctxt) 
    throws IOException, JsonProcessingException 
    {
        JsonNode node = jp.getCodec().readTree(jp);
        
        String uuid = node.get("uuid").asText();
        String zdt = node.get("timeModification").asText();
        
        int zindex = (Integer) ((IntNode) node.get("zindex")).numberValue();
        int x = (Integer) ((IntNode) node.get("x")).numberValue();
        int y = (Integer) ((IntNode) node.get("y")).numberValue();
        int width = (Integer) ((IntNode) node.get("width")).numberValue();
        int height = (Integer) ((IntNode) node.get("height")).numberValue();
        
        ZonedDateTime timeModification = ZonedDateTime.parse(zdt,DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        
        Widget widget = new Widget (x, y, width, height, zindex, uuid, timeModification);
        
        return new SafeWidget (widget);
    }
}