package rtb.storage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import rtb.model.SafeWidget;
import rtb.service.EWidgetOperationStatus;
import rtb.service.OperationResultWidget;
import rtb.service.OperationResultWidgetList;

public class WidgetStorageInMemory 
{   
    public class WidgetZComparator implements Comparator<SafeWidget> 
    {
            @Override
            public int compare(SafeWidget w1, SafeWidget w2) 
            {
               return w1.getZindex() - w2.getZindex();
            }
    }
             
    protected ConcurrentSkipListMap <String, SafeWidget> map = null;    
    protected final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    protected final ReentrantReadWriteLock.ReadLock rLock = lock.readLock();
    protected final ReentrantReadWriteLock.WriteLock wLock = lock.writeLock();
    
    protected WidgetStorageInMemory (ConcurrentSkipListMap <String, SafeWidget> map)
    {
        this.map = map;
    }
    
    public WidgetStorageInMemory ()
    {
        map = new ConcurrentSkipListMap <> ();
    }
    //**************************************************************************
    
    public OperationResultWidget create(SafeWidget swg) {
        
        swg.applayUuid(UUID.randomUUID().toString().toLowerCase()); 
        try {
                wLock.lock();
            
                int idx, newIdx = swg.getZindex();                 
                for (SafeWidget val : map.values()) 
                {
                    idx = val.getZindex();
                    if (newIdx <= idx)
                        val.applayZindex (idx + 1);
                }
                
                map.put(swg.getUuid(), swg);
                return new OperationResultWidget (swg, EWidgetOperationStatus.CREATED);
        }     
        finally {
            wLock.unlock();
        }           
    }
    
    public OperationResultWidget delete(String uuid) {
        
        SafeWidget found = map.remove (uuid);        
        if (found == null)
            return new OperationResultWidget (found, EWidgetOperationStatus.NOT_FOUND);
        
        return new OperationResultWidget (found, EWidgetOperationStatus.DELETED);
    }

    public OperationResultWidget modify(SafeWidget swg) {
        
        SafeWidget found = map.get (swg.getUuid());
        if (found == null)
            return new OperationResultWidget (swg, EWidgetOperationStatus.NOT_FOUND);
        
        if (found.getZindex() == swg.getZindex()) {
            found.applayGeo(swg);
            return new OperationResultWidget (swg, EWidgetOperationStatus.MODIFIED);
        }        
        
        try {
                wLock.lock();
            
                int idx, newIdx = swg.getZindex();                 
                for (SafeWidget val : map.values()) {
                    idx = val.getZindex();
                    if (newIdx <= idx)
                        val.applayZindex (idx + 1);
                }
                
                found.applay(swg);
                return new OperationResultWidget (swg, EWidgetOperationStatus.MODIFIED);
        }       
        finally {
            wLock.unlock();
        }
    }

    public OperationResultWidgetList getWidgetsInsideRect(SafeWidget outline) {
        
        List<SafeWidget> lst = new LinkedList<> ();  
            
        TreeSet <SafeWidget> set = new TreeSet<> (new WidgetZComparator());
        set.addAll(map.values());  
            
        Iterator<SafeWidget> itr = set.iterator();
        while (itr.hasNext()) {
            SafeWidget next = itr.next();
            if (outline.contains(next))
                lst.add (next); 
        }
        return new OperationResultWidgetList (lst, EWidgetOperationStatus.OK); 
    }

    public OperationResultWidget get(String uuid) 
    {        
         SafeWidget found = map.get (uuid);        
        if (found == null)
            return new OperationResultWidget (found, EWidgetOperationStatus.NOT_FOUND);
        
        return new OperationResultWidget (found, EWidgetOperationStatus.OK);
    }

    public OperationResultWidgetList getWidgetPage(String uuid_exclude, int count) 
    {        
        List<SafeWidget> lst = new ArrayList<> (count);            
        SafeWidget found = map.get(uuid_exclude.toLowerCase());            
        if (found == null)
            return new OperationResultWidgetList (lst, EWidgetOperationStatus.NOT_FOUND);

        TreeSet <SafeWidget> set = new TreeSet<> (new WidgetZComparator());
        set.addAll(map.values());  
            
        Iterator<SafeWidget> itr = set.iterator();
        while (itr.hasNext()) {
            SafeWidget next = itr.next();
            if (next.getZindex() > found.getZindex())
            {
                lst.add (next);  
                if (lst.size() >= count)
                    break;
            }   
        }
        return new OperationResultWidgetList (lst, EWidgetOperationStatus.OK); 
    }

    public OperationResultWidgetList getAll() {
        
        List<SafeWidget> lst = new ArrayList<> ();
        TreeSet <SafeWidget> set = new TreeSet<> (new WidgetZComparator());
        set.addAll(map.values());  
            
        Iterator<SafeWidget> itr = set.iterator();
        while (itr.hasNext()) {
            SafeWidget next = itr.next();
            lst.add (next); 
        }
        return new OperationResultWidgetList (lst, EWidgetOperationStatus.OK);
    }
}
