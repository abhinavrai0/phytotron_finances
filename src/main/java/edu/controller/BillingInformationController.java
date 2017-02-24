package edu.controller;
import edu.model.*;
import edu.service.*;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class BillingInformationController {
	@Autowired
	private BillInfoCRUD billCrudRepo;

	static final Logger logger = LogManager.getLogger(BillingInformationController.class.getName());

	@RequestMapping(value="/bill-info/",method=RequestMethod.GET)
	public List<BillInfo> getAllBills() {
		System.out.println("-------------------");

		List<BillInfo> billList =new ArrayList<BillInfo>();
		System.out.println("aaaaaaa"+billList);
		billList = (List<BillInfo>) billCrudRepo.findAll();
		System.out.println("a :"+billList);
		return billList;
	}
	@RequestMapping(value="/bill-info/{id}",method=RequestMethod.GET)
	public BillInfo getBill(@PathVariable("id") Long id) {
		BillInfo bill=new BillInfo();
		System.out.println(bill+"-----------"+id);
		try {
            bill = billCrudRepo.findOne(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
//            return e.getMessage();
        }
		System.out.println("bill :"+bill);
		/*if(bill == null){
			String errorMst = "no Bill information available for id " + id;
            logger.error(errorMst);
            return errorMst;
        } *//*else {
            return movie.getTitle() + " : " + movie.getYear();
		}*/
		bill.toString();

		return bill;
	}

	@RequestMapping(value="/bill-info/",method=RequestMethod.POST)
	public String createBill(@RequestBody BillInfo bill) {
		System.out.println("--====Post Request=====--");
		//need to add create if not found, if existing dont do anything functionality
		logger.info("Creating Bill : {}", bill);
		/* if (billCrudRepo.exists(bill.getId()) {
            logger.error("Unable to create. A User with name {} already exist", user.getName());
            return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " + 
            user.getName() + " already exist."),HttpStatus.CONFLICT);
        }*/
		try {
			billCrudRepo.save(bill);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return e.getMessage();
		}
		return "creation successful: " + String.valueOf(bill.getId());
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
