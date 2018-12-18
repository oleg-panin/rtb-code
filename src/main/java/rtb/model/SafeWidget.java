package rtb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.ZonedDateTime;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@JsonSerialize(using = SafeWidgetSerializer.class)
@JsonDeserialize(using = SafeWidgetDeserializer.class)
public class SafeWidget 
{
        protected Widget wg = new Widget ();

        @JsonIgnore protected final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        @JsonIgnore protected final ReentrantReadWriteLock.ReadLock rLock = lock.readLock();
        @JsonIgnore protected final ReentrantReadWriteLock.WriteLock wLock = lock.writeLock();

        public SafeWidget (Widget wg) {  
            this.wg = wg;
        }
        //*****************************************************	
        
        private void change() {             
                this.wg.setChanged(true);
                this.wg.setTimeModification(ZonedDateTime.now());
        }
    
         public int getX() {
             try {
                rLock.lock();
                return this.wg.getX();
            }
            finally {
                rLock.unlock();}
        }
        public int getY() {
            try {
                rLock.lock();
                return this.wg.getY();
            }
            finally {
                rLock.unlock();}
        }
        public int getWidth() {
            try {
                rLock.lock();
                return this.wg.getWidth();
            }
            finally {
                rLock.unlock();}
        }
        public int getHeight() {
            try {
                rLock.lock();
                return this.wg.getHeight();
            }
            finally {
                rLock.unlock();}
        }
        public int getZindex() {
            try {
                rLock.lock();
                return this.wg.getZindex();
            }
            finally {
                rLock.unlock();}
        }        
        public String getUuid() {
                return this.wg.getUuid();
        }        
        public boolean isChanged() {
            try {
                rLock.lock();
                return this.wg.isChanged();
            }
            finally {
                rLock.unlock();}
        }
        public ZonedDateTime getTimeModification() {
            try {
                rLock.lock();
                return this.wg.getTimeModification();
            }
            finally {
                rLock.unlock();}
	}
        //*****************************************************	
        public void applayUuid (String uuid) {
            
             try {
                wLock.lock();
                
		this.wg.setUuid(uuid);
                change();
            }
            finally {
                wLock.unlock();}
	}
        public void applayZindex (int idx) {
            
             try {
                wLock.lock();
                
		this.wg.setZindex(idx);
                change();
            }
            finally {
                wLock.unlock();}
	}

        public void applayGeo (SafeWidget swg) {
            
             try {
                wLock.lock();
                
		this.wg.setX(swg.getX());
                this.wg.setY(swg.getY());
                this.wg.setWidth(swg.getWidth());
                this.wg.setHeight(swg.getHeight());
                change();
            }
            finally {
                wLock.unlock();}
	}
        public void applay (SafeWidget swg) {
            
             try {
                wLock.lock();
                
		this.wg.setX(swg.getX());
                this.wg.setY(swg.getY());
                this.wg.setWidth(swg.getWidth());
                this.wg.setHeight(swg.getHeight());
                this.wg.setZindex(swg.getZindex());
                change();
            }
            finally {
                wLock.unlock();}
	}
        //*****************************************************

        @Override
        public int hashCode() {            
            return wg.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final SafeWidget other = (SafeWidget) obj;
            
            if (this.getZindex() != other.getZindex()) {
                return false;
            }
            if (this.getX() != other.getX()) {
                return false;
            }
             if (this.getY() != other.getY()) {
                return false;
            }
            if (this.getWidth() != other.getWidth()) {
                return false;
            }
            if (this.getHeight() != other.getHeight()) {
                return false;
            }
                      
            return true;
        }      
        //*****************************************************
        public String serialize (ObjectMapper objMapper) throws JsonProcessingException {
            String str = objMapper.writeValueAsString(wg);
            return str;
        }

        public boolean contains(SafeWidget swg) {
            return this.wg.contains(swg.wg);
        }
}
