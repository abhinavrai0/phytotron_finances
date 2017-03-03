/*package edu.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import edu.model.*;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
    @RequestMapping(value="/loginSubmit",method=RequestMethod.POST)
//	public User login(@RequestParam("email") String email,@RequestParam("pwd") String pwd ){
	public String login(@RequestBody User user){
    	System.out.println("----------");
    	System.out.println(user);
    	System.out.println("Email:"+user.getEmail()+","+user.getPwd());
		System.out.println("==== in LoginApp ====");
		return "reached";
}
}*/