package edu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.model.Rate;
import edu.service.RateCRUD;


/**
 * 
 * @author ankit
 *
 * This controller will control CRUD operations for different type of project rates
 * Delete functionality is not provided and can be added here in future if required
 */
@RestController
@RequestMapping("/rate")
public class RateController {
	@Autowired
	private RateCRUD rateCrudRepo;
	
	// Get list for rates
	@RequestMapping(method=RequestMethod.GET)
	public List<Rate> getAllRates() {
		List<Rate> rateList =new ArrayList<Rate>();
		rateList = (List<Rate>) rateCrudRepo.findAll();
		return rateList;
	} 
	
	// fetch a specific rate
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Rate getRate(@PathVariable("id") Long id) {
		Rate rate=new Rate();
		try {
			rate = rateCrudRepo.findOne(id);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		return rate;
	}

	@RequestMapping(method=RequestMethod.POST)
	public Rate createRate(@RequestBody Rate rate) {
		try {
			rateCrudRepo.save(rate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rate;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public Rate updateRate(@PathVariable("id") Long id,@RequestBody Rate updateRate) {
		updateRate.setId(id);
		rateCrudRepo.save(updateRate);
		return updateRate;
	}
}
