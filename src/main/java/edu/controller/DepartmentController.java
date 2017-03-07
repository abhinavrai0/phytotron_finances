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

import edu.model.Department;
import edu.service.DepartmentCRUD;

@RequestMapping
public class DepartmentController {
	@Autowired
	private DepartmentCRUD DepartmentRepo;
	static final Logger logger = LogManager.getLogger(DepartmentController.class.getName());


	@RequestMapping(value="/Department/",method=RequestMethod.GET)
	public List<Department> getAllDepartments() {
		List<Department> DepartmentList =new ArrayList<Department>();
		DepartmentList = (List<Department>) DepartmentRepo.findAll();
		return DepartmentList;
	}
	@RequestMapping(value="/Department/{id}",method=RequestMethod.GET)
	public Department getDepartment(@PathVariable("id") Long id) {
		Department Department=new Department();
		try {
			Department = DepartmentRepo.findOne(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
		return Department;
	} 

	@RequestMapping(value="/Department/",method=RequestMethod.POST)
	public Department createDepartment(@RequestBody Department Department) {
		logger.info("Creating Bill : {}", Department);
		try {
			DepartmentRepo.save(Department);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return Department;
	}
}
