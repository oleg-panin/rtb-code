package rtb.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

@JsonRootName(value = "widget")
public final class Widget
{       
	private String uuid = "";
        private int zindex = -1;
        
        private int x = -1;
        private int y = -1;
        private int width = -1;
        private int height = -1;
        
        private boolean changed = false;
        
        private ZonedDateTime timeModification = null;        
        //*****************************************************	
        public Widget  (int x, int y, int width, int height, int zindex)
        {
                this.uuid = UUID.randomUUID().toString().toLowerCase();
                
                this.zindex = zindex; 
                this.timeModification = ZonedDateTime.now();

                this.x = x;
                this.y = y;
                this.height = height;
                this.width = width;
        }
        
        public Widget  (int x, int y, int width, int height,
                int zindex,
                String uuid,
                ZonedDateTime timeModification)
        {
                this.uuid = uuid.toLowerCase();
                
                this.zindex = zindex; 
                this.timeModification = timeModification;

                this.x = x;
                this.y = y;
                this.height = height;
                this.width = width;
        }
        
        public Widget  () {  
        }
        //*****************************************************	

        public int getX() {
            return x;
        }
        public void setX(int x) {
            this.x = x;
        }
        public int getY() {
            return y;
        }
        public void setY(int y) {
            this.y = y;
        }
        public int getWidth() {
            return width;
        }
        public void setWidth(int width) {
            this.width = width;
        }
        public int getHeight() {
            return height;
        }
        public void setHeight(int height) {
            this.height = height;
        }
        public ZonedDateTime getTimeModification() {
		return timeModification;
	}
	public void setTimeModification(ZonedDateTime timeModification) {
		this.timeModification = timeModification;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid.toLowerCase();
	}
	public int getZindex() {
		return zindex;
	}
	public void setZindex(int zindex) {
		this.zindex = zindex;
	}
        public boolean isChanged() {
            return changed;
        }
        public void setChanged(boolean changed) {
            this.changed = changed;
        }
        //*****************************************************

        public int calcRightX () {
                return x + width;
        }
        public int calcBottomY () {
                return y + height;
        }
        // return true if wg is contaned inside of this Widget
        public boolean contains (Widget wg) 
        {
                if (x > wg.x || y > wg.y)
                    return false;

                int foreign_bottom = wg.calcBottomY ();
                int my_bottom = calcBottomY ();
                if (my_bottom < foreign_bottom)
                    return false;

                int foreign_right = wg.calcRightX ();
                int my_right = calcRightX ();
                if (my_right < foreign_right)
                    return false;

                return true;
        }
        //*****************************************************
        @Override
        public int hashCode() {
            int hash = 5;
             hash = 37 * hash + this.zindex;
            hash = 37 * hash + this.x;
            hash = 37 * hash + this.y;
            hash = 37 * hash + this.width;
            hash = 37 * hash + this.height;
            hash = 37 * hash + (this.changed ? 1 : 0);
            hash = 37 * hash + Objects.hashCode(this.timeModification);
            return hash;
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
            final Widget other = (Widget) obj;
            
            if (this.changed != other.changed) {
                return false;
            }  
            
            if (this.zindex != other.zindex) {
                return false;
            }
            if (this.x != other.x) {
                return false;
            }
            if (this.y != other.y) {
                return false;
            }
            if (this.width != other.width) {
                return false;
            }
            if (this.height != other.height) {
                return false;
            }
                      
            return true;
        }
        
}
