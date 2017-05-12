package edu.controller;

import java.text.DateFormat;
import java.text.ParseException;
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
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
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
		System.out.println(project.getClient().getId()+"--====Post Request=====-- start date"+project.getStartDate() + " end date :"+ project.getEndDate());
		System.out.println(project.getClient().getId()+"--====Post Request=====-- acc"+project.getAcc_number() + " name :"+ project.getProject_Title());
		logger.info("Creating project : {}", project);
		try {
			/*System.out.println("project.getStartDate() : "+project.getStartDate());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(project.getStartDate());
			System.out.println("calendar : "+calendar);
//			Calendar calendar = new Calendar();
			calendar.set(calendar.MILLISECOND, 999);
			System.out.println("calendar later : "+calendar);
			Date dd=calendar.getTime();
			System.out.println("dd date : "+dd);*/
			System.out.println("getEndOfDay : "+getEndOfDay(project.getStartDate()));
			project.setLastBillDate(project.getStartDate());
			project.setCurrentBill(0d);
			project.setBillPaidTotal(0d);
			projectCrudRepo.save(project);
		} catch (Exception e) {
			System.out.println("error :"+e);
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return project;
	} 

	private Date getEndOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		calendar.set(year, month, day, 23, 59, 59);
		//	    calendar.set(calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public Project updateProject(@PathVariable("id") Long id,@RequestBody Project updateProject) {
		System.out.println("iiiiiiiiiiiddddddd :"+id);
		System.out.println("updateProject : : "+updateProject);
		System.out.println(id+"===title===="+updateProject.getProject_Title()+"prj id : "+updateProject.getProject_id()+"Carts : "+updateProject.getCarts());
		updateProject.setId(id);
		projectCrudRepo.save(updateProject);
		return updateProject;
	}

	@RequestMapping(value="/{id}/generatebill",method=RequestMethod.POST)
	public double  generateBill(@PathVariable("id") Long id,@RequestBody BillGenerate generateBill){ //Calculate Invoice
		Project currentProject=getProject(id);
		Date lastBillDate=currentProject.getLastBillDate();
		// generate bill as per end of day
		Date generateBillDate=getEndOfDay(generateBill.getDate());
		System.out.println("lastBillDate ::::"+lastBillDate);
		System.out.println("generateBillDate ::::"+generateBillDate);
		if(lastBillDate.after(generateBillDate)){
			return currentProject.getCurrentBill();
		}
		if(lastBillDate==null){
			lastBillDate=currentProject.getStartDate();
		}
		/*System.out.println("Joda ::::"+Days.daysBetween(new DateTime(lastBillDate), new DateTime(generateBillDate)).getDays());
		System.out.println("Joda hours ::"+Hours.hoursBetween(new DateTime(lastBillDate), new DateTime(generateBillDate)).getHours());
		System.out.println("Joda minutes ::"+Minutes.minutesBetween(new DateTime(lastBillDate), new DateTime(generateBillDate)).getMinutes());
		System.out.println("Math.ceil2 :"+ Math.ceil((generateBillDate.getTime() - lastBillDate.getTime())/(60*60*24*1000.0)));*/
		long diff = (long)Math.ceil((generateBillDate.getTime() - lastBillDate.getTime())/(60*60*24*1000.0));
		System.out.println("generateBillDate.getTime() :"+generateBillDate.getTime());
		System.out.println("lastBillDate.getTime() :"+lastBillDate.getTime());
		long diffDays=TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		/*if(lastBillDate.equals(currentProject.getStartDate())){
			diffDays++;
		}*/
		System.out.println("generateBillDate :"+generateBillDate+", lastBillDate:"+lastBillDate+", diff:"+diff+", diffDays:"+diffDays);
		//		System.out.println("diffDays :"+diffDays+ "dd :"+ diff / (24 * 60 * 60 * 1000));
		double bill= currentProject.getCurrentBill();
		bill= bill + ((double)currentProject.getRateValue().getRate() * (double)diff * currentProject.getCarts());
		currentProject.setLastBillDate(generateBillDate);
		currentProject.setCurrentBill(bill);
		System.out.println("bill :"+bill+", generateBillDate:"+generateBillDate);
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
