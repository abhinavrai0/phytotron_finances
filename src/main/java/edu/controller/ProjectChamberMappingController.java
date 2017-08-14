package edu.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import edu.model.ProjectChamberMapping;
import edu.service.ProjectChamberMappingCRUD;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pinak on 8/11/2017.
 */
@RestController
@RequestMapping(value = "/projectChamberMapping")
public class ProjectChamberMappingController {
    @Autowired
    ProjectChamberMappingCRUD projectChamberMappingCRUDRepo;

    @RequestMapping(value = "/getAvailableChambers/{startDate}/{endDate}", method = RequestMethod.GET)
    public List<ProjectChamberMapping> getAvailableChambers(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate){
        List<ProjectChamberMapping> projectChamberMappingList = null;

        return projectChamberMappingList;
    }

    @RequestMapping(value = "/updateChambers/", method = RequestMethod.POST)
    public List<ProjectChamberMapping> updateChambers(@RequestBody List<ProjectChamberMapping> projectChamberMappingList){
            if (projectChamberMappingList!=null && !projectChamberMappingList.isEmpty()){
                for (ProjectChamberMapping projectChamberMapping:projectChamberMappingList){
                    try{
                        projectChamberMappingCRUDRepo.save(projectChamberMapping);
                        projectChamberMapping.setSaved(Boolean.TRUE);
                    } catch (Exception e) {
                        e.printStackTrace();
                        projectChamberMapping.setSaved(Boolean.FALSE);
                    }
                }
            }
        return projectChamberMappingList;
    }

    @RequestMapping(value = "/removeChambers/", method = RequestMethod.DELETE)
    public List<ProjectChamberMapping> removeChambers(@RequestBody List<ProjectChamberMapping> projectChamberMappingList){
        if (projectChamberMappingList!=null && !projectChamberMappingList.isEmpty()){
            for (ProjectChamberMapping projectChamberMapping:projectChamberMappingList){
                try{
                    projectChamberMapping.setStatus("INACTIVE");
                    projectChamberMappingCRUDRepo.save(projectChamberMapping);
                    projectChamberMapping.setSaved(Boolean.TRUE);
                } catch (Exception e) {
                    e.printStackTrace();
                    projectChamberMapping.setSaved(Boolean.FALSE);
                }
            }
        }
        return projectChamberMappingList;
    }
}
