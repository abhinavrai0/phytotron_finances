package edu.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import edu.model.Chamber;
import edu.model.ProjectChamberMapping;
import edu.service.ChamberCRUD;
import edu.service.ProjectChamberMappingCRUD;
import edu.util.DateUtil;
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

    @Autowired
    ChamberCRUD chamberCRUDRepo;

    /*@RequestMapping(value = "/getAvailableChambers/{startDate}/{endDate}", method = RequestMethod.GET)
    public List<ProjectChamberMapping> getAvailableChambers(@PathVariable("startDate") String startDateString, @PathVariable("endDate") String endDateString){
        List<ProjectChamberMapping> projectChamberMappingList = null;

        Date startDate = DateUtil.convertStringDateToSQLDate(startDateString);
        Date endDate = DateUtil.convertStringDateToSQLDate(endDateString);

        List<ProjectChamberMapping> resultList = projectChamberMappingCRUDRepo.findDistinctByChamberNameAndStatusEqualsAndAllocationDateBetweenAndDeAllocationDateBetween(
                "ACTIVE", startDate, endDate, startDate, endDate);

        if(resultList!=null && !resultList.isEmpty()){
            projectChamberMappingList = new ArrayList<>(resultList.size());
            for(ProjectChamberMapping projectChamberMapping : resultList){
                ProjectChamberMapping temp = projectChamberMappingCRUDRepo.findTopByChamberIdAAndStatusEqualsOrderByAllocatedCartsDesc(projectChamberMapping.getChamberName(),"ACTIVE");
                if(temp!=null){
                    projectChamberMappingList.add(temp);
                }
            }
        }
        return projectChamberMappingList;
    }*/

    @RequestMapping(value = "/listChambersForProject/{projectId}", method = RequestMethod.GET)
    public List<ProjectChamberMapping> listAllChambersForProject(@PathVariable("projectId") String projectId){
        List<ProjectChamberMapping> projectChamberMappingList = new ArrayList<>();
        projectChamberMappingList = projectChamberMappingCRUDRepo.findAllByProjectIdAndStatusEquals(projectId,"ACTIVE");
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

    public ProjectChamberMappingCRUD getProjectChamberMappingCRUDRepo() {
        return projectChamberMappingCRUDRepo;
    }

    public void setProjectChamberMappingCRUDRepo(ProjectChamberMappingCRUD projectChamberMappingCRUDRepo) {
        this.projectChamberMappingCRUDRepo = projectChamberMappingCRUDRepo;
    }

    public ChamberCRUD getChamberCRUDRepo() {
        return chamberCRUDRepo;
    }

    public void setChamberCRUDRepo(ChamberCRUD chamberCRUDRepo) {
        this.chamberCRUDRepo = chamberCRUDRepo;
    }
}
