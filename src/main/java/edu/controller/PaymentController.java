package edu.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.model.Payment;
import edu.model.Project;
import edu.service.PaymentCRUD;
import edu.service.ProjectCRUD;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	private PaymentCRUD paymentCrudRepo;
	/*@Autowired
	private ProjectCRUD projectCrudRepo;*/
	@Autowired 
	private ProjectController pc;

	@RequestMapping(method=RequestMethod.GET)
	public List<Payment> getAllPayments() {
		List<Payment> paymentList =new ArrayList<Payment>();
		paymentList = (List<Payment>) paymentCrudRepo.findAll();
		return paymentList;
	} 

	/*@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Payment getPayment(@PathVariable("id") Long id) {
		Payment payment=new Payment();
		try {
			payment = paymentCrudRepo.findOne(id);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		return payment;
	}*/

	//project id
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public List<Payment> getPayments(@PathVariable("id") Long id) {
		List<Payment> paymentsByClient = paymentCrudRepo.findByProjectId(id);
		return paymentsByClient;
	}

	@RequestMapping(method=RequestMethod.POST)
	public Payment makePayment(@RequestBody Payment payment) {
		System.out.println(payment.getAmount());
		System.out.println(payment.getPaidDate());
		try {
			paymentCrudRepo.save(payment);
		} catch (Exception e) {
			System.out.println("payment failed !!!");
			e.printStackTrace();
		}
		return payment;
	}

	//project id
	@RequestMapping(value="/{id}/paybill",method=RequestMethod.POST)
	public String  payBill(@PathVariable("id") Long id,@RequestBody Double paybill){
		//		ProjectController pc=new ProjectController();
		Project currentProject=pc.getProject(id);
		//		Project currentProject= projectCrudRepo.findOne(id);;
		System.out.println(currentProject.getProject_Title()+"000000000000"+currentProject.getCarts());
		Payment pay=new Payment();
//		pay.setClient(currentProject.getClient());
//		pay.setProject(currentProject);
		pay.setProjectId(currentProject.getId());
		pay.setAmount(paybill);
		makePayment(pay);
		double billPaidTotal=currentProject.getBillPaidTotal()+paybill;
		double remainingCurrentBill=currentProject.getCurrentBill()-paybill;
		currentProject.setBillPaidTotal(billPaidTotal);
		currentProject.setCurrentBill(remainingCurrentBill);
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		currentProject.setLastBillDate(Calendar.getInstance().getTime());
		System.out.println(id+"-----------id, currentProject------------"+currentProject);
		System.out.println(id+"-----------id, stuff----title--------"+currentProject.getProject_Title()+"prj id : "+currentProject.getProject_id()+"Carts : "+currentProject.getCarts());
		currentProject=pc.updateProject(id,currentProject);
		System.out.println(billPaidTotal+","+remainingCurrentBill);
		String s= "{\"billPaidTotal\":"+billPaidTotal +",\"remainingCurrentBill\":"+remainingCurrentBill+",\"lastBillPaidDate\":\""+dateFormat.format(currentProject.getLastBillDate())+"\"}";
		System.out.println(s);
		return s;
	}
}
