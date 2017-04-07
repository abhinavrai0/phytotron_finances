package edu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.model.Payment;
import edu.service.PaymentCRUD;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	private PaymentCRUD paymentCrudRepo;

	@RequestMapping(method=RequestMethod.GET)
	public List<Payment> getAllPayments() {
		List<Payment> paymentList =new ArrayList<Payment>();
		paymentList = (List<Payment>) paymentCrudRepo.findAll();
		return paymentList;
	} 
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Payment getPayment(@PathVariable("id") Long id) {
		Payment payment=new Payment();
		try {
			payment = paymentCrudRepo.findOne(id);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		return payment;
	}

	@RequestMapping(method=RequestMethod.POST)
	public Payment makePayment(@RequestBody Payment payment) {
		try {
			paymentCrudRepo.save(payment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return payment;
	}

}
