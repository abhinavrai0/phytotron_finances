package edu.controller;

import java.util.ArrayList;
import java.util.List;

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
import edu.service.ProjectCRUD;

@RestController
public class ProjectController {

	@Autowired
	private ProjectCRUD projectCrudRepo;

	static final Logger logger = LogManager.getLogger(ProjectController.class.getName());

	@RequestMapping(value="/project/",method=RequestMethod.GET)
	public List<Project> getAllProjects() {
		System.out.println("-------------------------");
		List<Project> projectList =new ArrayList<Project>();
		System.out.println("aaaaaaa"+projectList);
		try {
			projectList = (List<Project>) projectCrudRepo.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
            System.out.println("Error :: " +e.getMessage());
        }
		
		System.out.println("a :"+projectList);
		return projectList;
	}
	@RequestMapping(value="/project/{id}",method=RequestMethod.GET)
	public Project getClient(@PathVariable("id") Long id) {
		System.out.println("================================");
		Project project=new Project();
		try {
			project = projectCrudRepo.findOne(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
//            return e.getMessage();
        }
		System.out.println("Project :"+project);
		return project;
	}

	@RequestMapping(value="/project/",method=RequestMethod.POST)
	public Project createProject(@RequestBody Project project) {
		System.out.println("--====Post Request=====--"+project);
		System.out.println("project id :"+project.getClient().getId());
//		project.setClient();
		logger.info("Creating project : {}", project);
		
		/* if (clientCrudRepo.exists(client.getId()) {
            logger.error("Unable to create. A User with name {} already exist", user.getName());
            return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " + 
            user.getName() + " already exist."),HttpStatus.CONFLICT);
        }*/
		try {
			projectCrudRepo.save(project);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return project;
//		return "creation successful: " + String.valueOf(client.getId());
	} 
	
	@RequestMapping(value="/project/{id}",method=RequestMethod.PUT)
	public Project updateProject(@PathVariable("id") Long id,@RequestBody Project updateProject) {
		Project project= new Project();
		project=projectCrudRepo.findOne(id);
		/*if(client.getAddress()!=updateProject.getAddress()){
			client.setAddress(updateProject.getAddress());
		}
		if(client.getDept_name()!=updateProject.getDept_name()){
			client.setDept_name(updateProject.getDept_name());
		}*/
        projectCrudRepo.save(project);
		return project;
	}

	
}
