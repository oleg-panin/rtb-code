package rtb.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import rtb.model.SafeWidget;

public class OperationResultWidget
{
        protected SafeWidget widget;
        protected EWidgetOperationStatus status;
               
        @JsonCreator
        public OperationResultWidget (@JsonProperty("widget") SafeWidget widget, @JsonProperty("status") EWidgetOperationStatus status)
        {
            this.widget = widget;
            this.status = status;
        }
        
        public OperationResultWidget ()
        {}

        public EWidgetOperationStatus getStatus() {
            return status;
        }

        public void setStatus(EWidgetOperationStatus status) {
            this.status = status;
        }          
        
        public SafeWidget getWidget() {            
            return widget;            
        }

        public void setWidget(SafeWidget widget) {
            this.widget = widget;
        }
}
