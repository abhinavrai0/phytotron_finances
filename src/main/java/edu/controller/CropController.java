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

import edu.model.Crop;
import edu.service.CropCRUD;

@RequestMapping
public class CropController {
	@Autowired
	private CropCRUD CropRepo;
	static final Logger logger = LogManager.getLogger(BillingInformationController.class.getName());


	@RequestMapping(value="/Crop/",method=RequestMethod.GET)
	public List<Crop> getAllCrops() {
		List<Crop> CropList =new ArrayList<Crop>();
		CropList = (List<Crop>) CropRepo.findAll();
		return CropList;
	}
	@RequestMapping(value="/Crop/{id}",method=RequestMethod.GET)
	public Crop getCrop(@PathVariable("id") Long id) {
		Crop Crop=new Crop();
		try {
			Crop = CropRepo.findOne(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
		return Crop;
	}

	@RequestMapping(value="/Crop/",method=RequestMethod.POST)
	public Crop createCrop(@RequestBody Crop Crop) {
		logger.info("Creating Bill : {}", Crop);
		try {
			CropRepo.save(Crop);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return Crop;
	}
}
