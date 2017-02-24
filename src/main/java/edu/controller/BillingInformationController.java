package edu.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class BillingInformationController {
	@RequestMapping(value="/bill-info/",method=RequestMethod.GET)
	public String getAllBills() {
		return "bill info Get";
	}
	@RequestMapping(value="/bill-info/{id}",method=RequestMethod.GET)
	public String getBill() {
		return "bill info Get particular bill";
	}
	@RequestMapping(value="/bill-info/",method=RequestMethod.POST)
	public String createBill() {
		return "bill info post";
	}
	@RequestMapping(value="/bill-info/{id}",method=RequestMethod.PUT)
	public String updateBill() {
		return "bill info update";
	}
	@RequestMapping(value="/bill-info/{id}",method=RequestMethod.DELETE)
	public String deleteBill() {
		return "Greetings from Spring Boot!";
	}

}
