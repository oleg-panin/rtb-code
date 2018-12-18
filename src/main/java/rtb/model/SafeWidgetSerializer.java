package rtb.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class SafeWidgetSerializer extends StdSerializer<SafeWidget> {
     
    public SafeWidgetSerializer() {
        this(null);
    }
   
    public SafeWidgetSerializer (Class<SafeWidget> t) {
        super(t);
    }
 
   
    @Override
    public void serialize(SafeWidget value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        
        jgen.writeStartObject();
        
        jgen.writeStringField("uuid", value.getUuid());
        
        jgen.writeNumberField("x", value.getX());
        jgen.writeNumberField("y", value.getY());
        jgen.writeNumberField("width", value.getWidth());
        jgen.writeNumberField("height", value.getHeight());
        jgen.writeNumberField("zindex", value.getZindex());
        
        ZonedDateTime zdt = value.getTimeModification();
        jgen.writeStringField("timeModification", zdt.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        
        jgen.writeEndObject();
    }
}