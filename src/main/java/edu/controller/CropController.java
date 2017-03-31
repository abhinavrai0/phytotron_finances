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

import edu.model.Crop;
import edu.service.CropCRUD;

@RestController
@RequestMapping("/crop")
public class CropController {
	@Autowired
	private CropCRUD cropCrudRepo;
	static final Logger logger = LogManager.getLogger(CropController.class.getName());


	@RequestMapping(method=RequestMethod.GET)
	public List<Crop> getAllCrops() {
		List<Crop> CropList =new ArrayList<Crop>();
		CropList = (List<Crop>) cropCrudRepo.findAll();
		return CropList;
	} 
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Crop getCrop(@PathVariable("id") Long id) {
		Crop crop=new Crop();
		try {
			crop = cropCrudRepo.findOne(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
		return crop;
	}

	@RequestMapping(method=RequestMethod.POST)
	public Crop createCrop(@RequestBody Crop crop) {
		logger.info("Creating Bill : {}", crop);
		try {
			cropCrudRepo.save(crop);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return crop;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public Crop updateRate(@PathVariable("id") Long id,@RequestBody Crop updateCrop) {
		updateCrop.setId(id);
		cropCrudRepo.save(updateCrop);
		return updateCrop;
	}
}
