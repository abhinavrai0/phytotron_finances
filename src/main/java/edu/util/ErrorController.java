package edu.util;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ErrorController {

   @Autowired
   private ErrorService errorService;
	     
   @RequestMapping(value="/errors",method=RequestMethod.GET)
   public String renderErrorPage(final Model model,final HttpServletRequest request){
	   
	   System.out.println("reached to error page");
	   //Get the Http error code.
	   final int error_code=getHttpStatusCode(request);
	   
	   //Generates Error message for corresponding Http Error Code.
	   final String error_message=errorService.generateErrorMessage(error_code);
	   
	   model.addAttribute("errorMsg",error_message);
	   return "error";
   }  
   private int getHttpStatusCode(final HttpServletRequest request){
	   System.out.println("reached to error page getHttpStatusCode method");
	   return (int) request.getAttribute("javax.servlet.error.status_code");
   }
}