package edu.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
//import org.joda.time.*;
//import org.joda.time.Interval;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.model.Project;
import edu.model.Client;
import edu.model.Payment;
import edu.model.BillGenerate;
import edu.service.ProjectCRUD;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectCRUD projectCrudRepo;

	static final Logger logger = LogManager.getLogger(ProjectController.class.getName());

	@RequestMapping(method=RequestMethod.GET)
	public List<Project> getAllProjects() {
		System.out.println("-------------------------");
		List<Project> projectList =new ArrayList<Project>();
		System.out.println("Project Controller :"+projectList);
		try {
			projectList = (List<Project>) projectCrudRepo.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
            System.out.println("Error :: " +e.getMessage());
        }
		
		System.out.println("a :"+projectList);
		return projectList;
	}
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Project getProject(@PathVariable("id") Long id) {
		Project project=new Project();
		try {
			project = projectCrudRepo.findOne(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
		System.out.println("Project :"+project.toString());
		return project;
	}

	@RequestMapping(method=RequestMethod.POST)
	public Project createProject(@RequestBody Project project) {
		System.out.println("post request ::::::::::::");
		System.out.println("start :"+project.getStartDate());
		System.out.println("end   :"+project.getEndDate());
//		System.out.println("--====Post Request=====-- start date"+project.getStartDate() + " end date :"+ project.getEndDate());
		System.out.println("project id :"+project.getClient().getId());
		logger.info("Creating project : {}", project);
		try {
			project.setLastBillDate(project.getStartDate());
			project.setCurrentBill(0d);
			project.setBillPaidTotal(0d);
			projectCrudRepo.save(project);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return project;
	} 
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public Project updateProject(@PathVariable("id") Long id,@RequestBody Project updateProject) {
		System.out.println("iiiiiiiiiiiddddddd :"+id);
		System.out.println("updateProject छ : : "+updateProject);
		System.out.println(id+"===title===="+updateProject.getProject_Title()+"prj id : "+updateProject.getProject_id()+"Carts : "+updateProject.getCarts());
		updateProject.setId(id);
        projectCrudRepo.save(updateProject);
		return updateProject;
	}
	
	@RequestMapping(value="/{id}/generatebill",method=RequestMethod.POST)
	public double  generateBill(@PathVariable("id") Long id,@RequestBody BillGenerate generateBill){ //Calculate Invoice
		Project currentProject=getProject(id);
		Date lastBillDate=currentProject.getLastBillDate();
		Date generateBillDate=generateBill.getDate();
		if(lastBillDate==null){
			lastBillDate=currentProject.getStartDate();
		}
		long diff = generateBillDate.getTime() - lastBillDate.getTime();
		long diffDays=TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		if(lastBillDate.equals(currentProject.getStartDate())){
			diffDays++;
		}
//		System.out.println("diffDays :"+diffDays+ "dd :"+ diff / (24 * 60 * 60 * 1000));
		double bill= currentProject.getCurrentBill();
		bill= bill + ((double)currentProject.getRateValue().getRate() * (double)diffDays * currentProject.getCarts());
		currentProject.setLastBillDate(generateBillDate);
		currentProject.setCurrentBill(bill);
		updateProject(id,currentProject);
		return currentProject.getCurrentBill();
	}
	
	/*@RequestMapping(value="/{id}/paybill",method=RequestMethod.POST)
	public String  payBill(@PathVariable("id") Long id,@RequestBody Double paybill){
		Project currentProject=getProject(id);
		Payment pay=new Payment();
		pay.setClient(currentProject.getClient());
		pay.setProject(currentProject);
		pay.setAmount(paybill);
		double billPaidTotal=currentProject.getBillPaidTotal()+paybill;
		double remainingCurrentBill=currentProject.getCurrentBill()-paybill;
		currentProject.setBillPaidTotal(billPaidTotal);
		currentProject.setCurrentBill(remainingCurrentBill);
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		currentProject.setLastBillPaidDate(Calendar.getInstance().getTime());
		System.out.println(id+"project page-----------id, currentProject------------"+currentProject);
		System.out.println(id+"project page-----------id, stuff----title--------"+currentProject.getProject_Title()+"prj id : "+currentProject.getProject_id()+"Carts : "+currentProject.getCarts());
		currentProject=updateProject(id,currentProject);
		System.out.println(billPaidTotal+","+remainingCurrentBill);
		String s= "{\"billPaidTotal\":"+billPaidTotal +",\"remainingCurrentBill\":"+remainingCurrentBill+",\"lastBillPaidDate\":\""+dateFormat.format(currentProject.getLastBillPaidDate())+"\"}";
		System.out.println(s);
		return s;
	}
	*/
}
