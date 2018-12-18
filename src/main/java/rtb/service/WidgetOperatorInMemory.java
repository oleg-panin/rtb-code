package rtb.service;

import org.springframework.beans.factory.annotation.Autowired;
import rtb.model.SafeWidget;
import rtb.model.Widget;
import rtb.storage.WidgetStorageInMemory;


public class WidgetOperatorInMemory
{
    @Autowired 
    private WidgetStorageInMemory storage;  
    
    public WidgetOperatorInMemory () {
        
    }

    public OperationResultWidget create(Widget wg)
    {        
        SafeWidget swg = new SafeWidget (wg);
        OperationResultWidget ret = storage.create (swg);         
        return ret;
    }

    public OperationResultWidget delete(String uuid) 
    {
        OperationResultWidget ret = storage.delete (uuid);       
        return ret;
    }

    public OperationResultWidget modify(Widget wg) 
    {
        SafeWidget swg = new SafeWidget (wg);
        OperationResultWidget ret = storage.modify (swg);            
        return ret;
    }

    public OperationResultWidgetList getWidgetsInsideRect(Widget wg) 
    {
        SafeWidget swg = new SafeWidget (wg);
        OperationResultWidgetList ret = storage.getWidgetsInsideRect (swg);         
        return ret;
    }

    public OperationResultWidgetList getWidgetPage(String uuid_exclude, int count) 
    {
        OperationResultWidgetList ret = storage.getWidgetPage (uuid_exclude, count);         
        return ret;
    }

    public OperationResultWidget get(String uuid) 
    {
        OperationResultWidget ret = storage.get (uuid);        
        return ret;
    }

    public OperationResultWidgetList getAll() 
    {
        OperationResultWidgetList ret = storage.getAll ();          
        return ret;
    }
}
