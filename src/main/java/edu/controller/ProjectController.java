package edu.controller;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
//import org.joda.time.*;
//import org.joda.time.Interval;
import com.sun.org.apache.xpath.internal.operations.Bool;
import edu.model.ProjectChamberMapping;
import edu.model.ProjectResourceMapping;
import edu.service.ProjectChamberMappingCRUD;
import edu.service.ProjectResourceMappingCRUD;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.model.Project;
import edu.exception.CustomException;
import edu.exception.DataNotFoundException;
import edu.exception.NotAllowedException;
import edu.service.ProjectCRUD;


/**
 * 
 * @author ankit
 * This controller will control CRUD operation for Project
 * Delete functionality is not provided and can be added here in future if required
 * This controller also have following functionality:
 * Generate Bill
 * Get project list by status
 * End project > Status changes from active to Payment pending, only payment can be made in this state, can't update any other thing
 * Finish project > Status changes from Payment pending to Completed, Can't update anything in project.
 */

@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectCRUD projectCrudRepo;

	@Autowired
	private ProjectResourceMappingCRUD projectResourceMappingCRUDRepo;

	private ProjectChamberMappingCRUD projectChamberMappingCRUDRepo;

	static final Logger logger = LogManager.getLogger(ProjectController.class.getName());

	// Get project list(Active only)
	@RequestMapping(method=RequestMethod.GET)
	public List<Project> getAllProjects() {
		List<Project> projectList =new ArrayList<Project>();
		try {
			projectList = (List<Project>) projectCrudRepo.findByProjectStatus("Active");
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CustomException();
		}
		return projectList;
	}

	// Get project list as per status
	@RequestMapping(value="/status/{status}",method=RequestMethod.GET)
	public List<Project> getSelectedProjects(@PathVariable("status") String status) {
		List<Project> projectList =new ArrayList<Project>();
		try {
			if(status.equals("All")){
				projectList = (List<Project>) projectCrudRepo.findAll();
			}
			else{
				projectList = (List<Project>) projectCrudRepo.findByProjectStatus(status);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return projectList;
	}


	// Fetch project by id
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Project getProject(@PathVariable("id") Long id) {
		Project project=new Project();
		try {
			project = projectCrudRepo.findOne(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if(project==null) throw new DataNotFoundException();
		return project;
	}

	//Create a new Project
	//Title and id are mandatory fields
	//Set initially bill and bill paid to 0
	//Set Project status as active upon creation of project
	//Set last bill generated(last invoice) date same as start date
	@RequestMapping(method=RequestMethod.POST)
	public Project createProject(@RequestBody Project project) throws Exception {
		logger.info("Creating project : {}", project);
		if(project.getProject_Title() == null || project.getProject_Title().isEmpty() 
				|| project.getProject_id()==null || project.getProject_id().isEmpty()){
			throw new IllegalArgumentException("Missing a mandatory field");
		}
		try {
			project.setProjectStatus("Active");
			project.setLastBillDate(project.getStartDate());
			project.setCurrentBill(0d);
			project.setBillPaidTotal(0d);
			projectCrudRepo.save(project);
			/****
			 * *** Logic for adding resources to project on create project page.
			 * This part is commented since we decided to use check box button
			 * instead of actually adding resources while creating project.
			 * ******************/
            List<ProjectResourceMapping> projectResourceMappingList = project.getProjectResourceMappingList();
			if(projectResourceMappingList!=null && !projectResourceMappingList.isEmpty()){
				for (ProjectResourceMapping projectResourceMapping : projectResourceMappingList){
					projectResourceMapping.setProjectId(project.getProject_id());
					projectResourceMapping.setInvoiced(Boolean.FALSE);

					projectResourceMappingCRUDRepo.save(projectResourceMapping);
				}
				project.setAdditionalResourcesAdded(Boolean.TRUE);
			}

			//Saving Chambers.
			List<ProjectChamberMapping> projectChamberMappingList = project.getProjectChamberMappingList();
			if(projectChamberMappingList!=null && !projectChamberMappingList.isEmpty()){
				for (ProjectChamberMapping projectChamberMapping : projectChamberMappingList){
					projectChamberMapping.setStatus("ACTIVE");
					projectChamberMappingCRUDRepo.save(projectChamberMapping);
				}
			}


		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new CustomException();
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
		return calendar.getTime();
	}

	// Update project
	// If project status is completed, no updates are allowed
	// If start date and last bill date are same(means no bill has ever been generated), on updating of start date, update last bill date too 
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public Project updateProject(@PathVariable("id") Long id,@RequestBody Project updateProject) throws Exception {
		Project currentProject=getProject(id);
		updateProject.setId(id);
		List<ProjectResourceMapping> projectResourceMappingList = updateProject.getProjectResourceMappingList();
		if(updateProject!=null && projectResourceMappingList!=null && !projectResourceMappingList.isEmpty()){
			for(ProjectResourceMapping projectResourceMapping: projectResourceMappingList){
				if(projectResourceMapping!=null)
					projectResourceMappingCRUDRepo.save(projectResourceMapping);
			}
		}
		else
			updateProject.setAdditionalResourcesAdded(Boolean.FALSE);

		if((currentProject.getStartDate() == null && currentProject.getLastBillDate() ==null) || currentProject.getStartDate().equals(currentProject.getLastBillDate())){
			updateProject.setLastBillDate(updateProject.getStartDate());
		}
		if(!updateProject.getProjectStatus().equals("Completed")){
			try{
				projectCrudRepo.save(updateProject);
			}
			catch(Exception e){
				logger.error(e.getMessage());
				throw new CustomException();
			}
		}
		else{
			throw new NotAllowedException();
		}
		return updateProject;
	}

	// Generate bill
	// If start date or last generated bill is not present throw error
	// If given generate bill date is before last generate bill date, throw exception. Not allowed
	@RequestMapping(value="/{id}/generatebill",method=RequestMethod.POST)
	public double  generateBill(@PathVariable("id") Long id,@RequestBody Date generateBill) throws Exception{ //Calculate Invoice
		Project currentProject=getProject(id);
		Date lastBillDate=currentProject.getLastBillDate();
		// generate bill as per end of day
		Date generateBillDate=getEndOfDay(generateBill);
		if(lastBillDate==null || currentProject.getStartDate()==null){
			throw new IllegalArgumentException("There is no start or last invoice date");
		}
		if((lastBillDate!=null && lastBillDate.after(generateBillDate))){
			throw new IllegalArgumentException("Can not generate bill with older date than the last generated bill");
		}
		long diff = (long)Math.ceil((generateBillDate.getTime() - lastBillDate.getTime())/(60*60*24*1000.0));
		//		long diffDays=TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		double bill= currentProject.getCurrentBill();
		bill= bill + ((double)currentProject.getRateValue().getRate() * (double)diff * currentProject.getCarts());
		currentProject.setLastBillDate(generateBillDate);
		bill = Math.round(bill * 100D) / 100D;
		currentProject.setCurrentBill(bill);
		try {
			updateProject(id,currentProject);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException();
		}
		return currentProject.getCurrentBill();
	}

	// Project in transaction pending state, only waiting for payment
	@RequestMapping(value="/{id}/endProject",method=RequestMethod.GET)
	public Project endProject(@PathVariable("id") Long id) throws Exception{
		Project currentProject=getProject(id);
		try {
			generateBill(id, currentProject.getEndDate());
			currentProject.setProjectStatus("Payment Pending");
			updateProject(id, currentProject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DateTimeException("Invalid date please check again");
		}
		return currentProject;
	}

	//Project is completed and nothing can be updated now
	@RequestMapping(value="/{id}/finishProject",method=RequestMethod.GET)
	public Project finishProject(@PathVariable("id") Long id){
		Project currentProject=getProject(id);
		currentProject.setProjectStatus("Completed");
		projectCrudRepo.save(currentProject);
		return currentProject;
	}


}
