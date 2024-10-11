package jsp2.examples.simpletag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class RepeatSimpleTag extends SimpleTagSupport {
    private int num;
     

    public void doTag() throws JspException, IOException {
    	getJspContext().setAttribute("num", String.valueOf(num));
    	for (int i=0; i<num; i++) {
            getJspContext().setAttribute("count", 
            		"<U>"+String.valueOf( i + 1 )+"</U>" );
            getJspBody().invoke(null);
        }
    }

    public void setNum(int num) {
    	this.num = num;
    }
}