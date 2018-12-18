package rtb.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import rtb.model.SafeWidget;


public class OperationResultWidgetList
{
        protected List<SafeWidget> widgetLst = null;
        protected EWidgetOperationStatus status;
               
        @JsonCreator
        public OperationResultWidgetList (@JsonProperty("widgetLst") List<SafeWidget> widgetLst, @JsonProperty("status") EWidgetOperationStatus status)
        {
            this.status = status;
            this.widgetLst = widgetLst;
        }

        public EWidgetOperationStatus getStatus() {
            return status;
        }

        public void setStatus(EWidgetOperationStatus status) {
            this.status = status;
        }          
        
        public List<SafeWidget> getWidgetLst() {            
            return widgetLst;            
        }

        public void setWidgetLst(List<SafeWidget> widgetLst) {
            this.widgetLst = widgetLst;
        }
}
