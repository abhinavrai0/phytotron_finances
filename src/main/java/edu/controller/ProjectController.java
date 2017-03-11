package edu.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import edu.model.Project;
import edu.model.Client;
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
		System.out.println("================================");
		Project project=new Project();
		try {
			project = projectCrudRepo.findOne(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
		System.out.println("Project :"+project);
		return project;
	}

	@RequestMapping(method=RequestMethod.POST)
	public Project createProject(@RequestBody Project project) {
		System.out.println("--====Post Request=====--"+project);
		System.out.println("project id :"+project.getClient().getId());
		logger.info("Creating project : {}", project);
		try {
			projectCrudRepo.save(project);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return project;
//		return "creation successful: " + String.valueOf(client.getId());
	} 
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public Project updateProject(@PathVariable("id") Long id,@RequestBody Project updateProject) {
		/*Project project= new Project();
		project=projectCrudRepo.findOne(id);*/
		/*if(client.getAddress()!=updateProject.getAddress()){
			client.setAddress(updateProject.getAddress());
		}
		if(client.getDept_name()!=updateProject.getDept_name()){
			client.setDept_name(updateProject.getDept_name());
		}*/
		updateProject.setId(id);
        projectCrudRepo.save(updateProject);
		return updateProject;
	}
	
	@RequestMapping(value="/{id}/generatebill",method=RequestMethod.POST)
	public String  generateBill(@PathVariable("id") Long id,@RequestBody BillGenerate generateBill){
		Project p=getProject(id);
		Date d1=p.getLastBillDate();
		Date d2=generateBill.getDate();
		long diff = Math.abs(generateBill.getDate().getTime() - p.getLastBillDate().getTime());
		System.out.println(diff);
		long diffDays = diff / (24 * 60 * 60 * 1000);
		System.out.println(diffDays);
		System.out.println( p.getRate());
		System.out.println( p.getCarts());
//		System.out.println(diffDays);
		double bill= (double)p.getRate() * (double)diffDays;
		System.out.println(bill);
		bill *= p.getCarts();
		System.out.println(bill);
		p.setLastBillDate(d2);;
		p.setCurrentBill(bill);
		updateProject(id,p);
//		TimeUnit timeUnit = null;
//		System.out.println("timeunit :"+timeUnit.convert(diff,TimeUnit.DAYS));
//		Interval interval = new Interval(oldInstant, new Instant());
//		Days.daysBetween(d1, d2).getDays();
		return "done";
	}

	
}
