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

import edu.model.Chamber;
import edu.service.ChamberCRUD;
@RestController
public class ChamberController {
	@Autowired
	private ChamberCRUD chamberRepo;
	static final Logger logger = LogManager.getLogger(ChamberController.class.getName());


	@RequestMapping(value="/chamber/",method=RequestMethod.GET)
	public List<Chamber> getAllChambers() {
		List<Chamber> chamberList =new ArrayList<Chamber>();
		chamberList = (List<Chamber>) chamberRepo.findAll();
		return chamberList;
	} 
	@RequestMapping(value="/chamber/{id}",method=RequestMethod.GET)
	public Chamber getChamber(@PathVariable("id") Long id) {
		Chamber chamber=new Chamber();
		try {
			chamber = chamberRepo.findOne(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
		return chamber;
	}

	@RequestMapping(value="/chamber/",method=RequestMethod.POST)
	public Chamber createChamber(@RequestBody Chamber chamber) {
		logger.info("Creating Bill : {}", chamber);
		try {
			chamberRepo.save(chamber);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return chamber;
	}
}
