/*package edu.util;

import java.util.Map;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
public class CustomErrorMessage {
	
	private static final String PATH = "/error";

    @Value("${debug}")
    private boolean debug;

    @Autowired
    private ErrorAttributes errorAttributes;
    
    @RequestMapping(value = PATH)
    ErrorJson error(HttpServletRequest request, HttpServletResponse response) {
        // Appropriate HTTP response code (e.g. 404 or 500) is automatically set by Spring. 
        // Here we just define response body.
    	System.out.println("reached in error controller, error method");
        return new ErrorJson(response.getStatus(), getErrorAttributes(request, debug));
    }

    public String getErrorPath() {
        return PATH;
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        System.out.println("reached in error controller getErrorAttributes method");
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }
   
    

	private String errorMessage;

	public CustomError(String errorMessage){
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
*/