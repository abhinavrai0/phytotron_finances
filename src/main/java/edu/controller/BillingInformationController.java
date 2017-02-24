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
		List<BillInfo> billList =new ArrayList<BillInfo>();
		System.out.println("aaaaaaa"+billList);
		billList = (List<BillInfo>) billCrudRepo.findAll();
		System.out.println("a :"+billList);
		return billList;
	}
	@RequestMapping(value="/bill-info/{id}",method=RequestMethod.GET)
	public BillInfo getBill(@PathVariable("id") Long id) {
		BillInfo bill=new BillInfo();
		try {
            bill = billCrudRepo.findOne(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
//            return e.getMessage();
        }
		System.out.println("bill :"+bill);
		return bill;
	}

	@RequestMapping(value="/bill-info/",method=RequestMethod.POST)
	public BillInfo createBill(@RequestBody BillInfo bill) {
		System.out.println("--====Post Request=====--");
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
		}
		return bill;
//		return "creation successful: " + String.valueOf(bill.getId());
	}
	@RequestMapping(value="/bill-info/{id}",method=RequestMethod.PUT)
	public BillInfo updateBill(@PathVariable("id") Long id,@RequestBody BillInfo updateBill) {
		BillInfo bill= new BillInfo();
		bill=billCrudRepo.findOne(id);
		if(bill.getAcc_number()!=updateBill.getAcc_number()){
			bill.setAcc_number(updateBill.getAcc_number());
		}
		if(bill.getAddress()!=updateBill.getAddress()){
			bill.setAddress(updateBill.getAddress());
		}
		if(bill.getBill_rate()!=updateBill.getBill_rate()){
			bill.setBill_rate(updateBill.getBill_rate());
		}
		if(bill.getDept_name()!=updateBill.getDept_name()){
			bill.setDept_name(updateBill.getDept_name());
		}
		if(bill.getProject_name()!=updateBill.getProject_name()){
			bill.setProject_name(updateBill.getProject_name());
		}
		if(bill.getProject_user_email()!=updateBill.getProject_user_email()){
			bill.setProject_user_email(updateBill.getProject_user_email());
		}
		if(bill.getProject_user_name()!=updateBill.getProject_user_name()){
			bill.setProject_user_name(updateBill.getProject_user_name());
		}
        billCrudRepo.save(bill);
		return bill;
	}
	@RequestMapping(value="/bill-info/{id}",method=RequestMethod.DELETE)
	public String deleteBill(@PathVariable("id") Long id) {
		return "Greetings from Spring Boot!";
	}
}
