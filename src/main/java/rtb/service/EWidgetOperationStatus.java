package rtb.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EWidgetOperationStatus 
{
    OK             (10, "OK"),        
    NOT_EXIST      (20, "Not exists"),    
    NOT_FOUND      (30, "Not found"),    
    INVALID_PARAMS (40, "Invalid params"),
    UNKNOWN_ERROR  (50, "Unknown Error"),
    CREATED        (60, "Widget Created"),
    DELETED        (70, "Widget Deleted"),
    MODIFIED       (80, "Widget Modified"),;
  
    protected int code;
    protected String description;
       
    EWidgetOperationStatus (int code, String description) 
    {this.code = code; this.description = description;}

    public int getCode ()
    {
        return code;
    }

    public void setCode (int code)
    {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @JsonCreator
    static EWidgetOperationStatus findValue (@JsonProperty("code") int code, @JsonProperty("description") String description)
    {
        return Arrays.stream(
                EWidgetOperationStatus.values())
                .filter(v -> v.code == code && v.description.equals(description)).findFirst().get();
    }
}