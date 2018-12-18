package rtb.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rtb.model.Widget;
import rtb.service.OperationResultWidget;
import rtb.service.OperationResultWidgetList;
import rtb.service.WidgetOperatorInMemory;

@RestController
@RequestMapping("/rtb/widget")
public class WidgetRestController 
{    
        private final WidgetOperatorInMemory service;
        
        @Autowired
        public WidgetRestController(WidgetOperatorInMemory service) 
        {
            this.service = service;
        }

        // *********************************************************************
        // get all of widgets
        @GetMapping(value="/",
                    produces=MediaType.APPLICATION_JSON_VALUE
        )
        public OperationResultWidgetList getAllWidgets () 
        {
            return this.service.getAll();                    
        }
        
        // *********************************************************************
        // get widget by uuid
        @GetMapping(value="/{uuid}",
                    produces=MediaType.APPLICATION_JSON_VALUE
        )
        public OperationResultWidget get (@PathVariable("uuid") String uuid) 
        {
            return this.service.get(uuid);
        }
        
        // *********************************************************************
        // get widgets count starting with excluded widget (with uuid_exclude) in acsenging z-order
        @GetMapping(value="/{uuid}",
                    params="count",
                    produces=MediaType.APPLICATION_JSON_VALUE
        )
        public OperationResultWidgetList getWidgetPage         
        (       @PathVariable("uuid") String uuid_exclude, 
                @RequestParam(value = "count",defaultValue="0") int count) 
        {

            //return widgetStorage.get(uuid_exclude, count);  
            return this.service.getWidgetPage(uuid_exclude, count); 
        }

        // *********************************************************************
        // get widgets inside rect of widget (from request body) in acsenging z-order
        @GetMapping(value="/", 
                    params="rect",
                    consumes=MediaType.APPLICATION_JSON_VALUE,
                    produces=MediaType.APPLICATION_JSON_VALUE
        )
        public OperationResultWidgetList getWidgetsInsideRect 
        (       @RequestBody Widget wg,
                @RequestParam(value = "rect", defaultValue="true")  boolean dummy)
        {
            return service.getWidgetsInsideRect(wg); 
        }
        
       // *********************************************************************
        // modify the addressed widget with new values
        @PatchMapping(value="/{uuid}",
                    consumes=MediaType.APPLICATION_JSON_VALUE,
                    produces=MediaType.APPLICATION_JSON_VALUE
        )         
        public OperationResultWidget modify(
                @PathVariable("uuid") String uuid, 
                @RequestBody Widget wg) 
        {            
           return this.service.modify(wg);
        }
        
        // *********************************************************************
        // delete widget by uuid
        @DeleteMapping(value="/{uuid}",
                    produces=MediaType.APPLICATION_JSON_VALUE
        )         
        public OperationResultWidget delete(@PathVariable("uuid") String uuid) 
        {
                if (null == uuid) 
                    uuid = "";
            return this.service.delete(uuid.toLowerCase());
        }

        // *********************************************************************
        // create new widget
        @PostMapping(value="/", 
                    consumes=MediaType.APPLICATION_JSON_VALUE,
                    produces=MediaType.APPLICATION_JSON_VALUE
        )         
        public OperationResultWidget create(@RequestBody Widget wg) 
        {
            return this.service.create(wg); 
        }
}
