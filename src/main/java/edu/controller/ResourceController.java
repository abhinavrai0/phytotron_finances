package edu.controller;

import edu.model.ProjectResourceMapping;
import edu.model.Resource;
import edu.service.ProjectResourceMappingCRUD;
import edu.service.ResourceCRUD;
import edu.service.ProjectCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Pinakin Abhyankar on 7/19/2017.
 */

@RestController
@RequestMapping("/resourceController")
public class ResourceController {

    @Autowired
    private ResourceCRUD resourceCRUDRepo;

    @Autowired
    private ProjectCRUD projectCRUDRepo;

    @Autowired
    private ProjectResourceMappingCRUD projectResourceMappingCRUDRepo;

    @RequestMapping(value = "/addResource", method = RequestMethod.POST)
    public Resource createResource(@RequestBody Resource resource){

        try {
            if(resource !=null)
                resourceCRUDRepo.save(resource);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resource;
    }

    @RequestMapping(value = "/updateResouce/{id}", method = RequestMethod.PUT)
    public Resource updateResource(@PathVariable("id") Long id, @RequestBody Resource resource){

        try {
            if(resource !=null && resourceCRUDRepo.exists(id)){
                resource.setId(id);
                resourceCRUDRepo.save(resource);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resource;
    }

    @RequestMapping(value = "/getAllResources", method = RequestMethod.GET)
    public List<Resource> getAllResources(){
        List<Resource> resourceList = new ArrayList<>();
        try {
            resourceList = (List<Resource>) resourceCRUDRepo.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resourceList;
    }

    @RequestMapping(value = "/getResourceByName", method = RequestMethod.GET)
    public Resource getResourceByName(String resourceName){
        Resource resource = new Resource();
        try {
            resource = resourceCRUDRepo.findFirstByResourceName(resourceName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resource;
    }

    @RequestMapping(value = "/getResourceById", method = RequestMethod.GET)
    Resource getResourceById(Long id){
        Resource resource = new Resource();
        try {
            if(resourceCRUDRepo.exists(id))
                resource = resourceCRUDRepo.findOne(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resource;
    }

    @RequestMapping(value = "/removeResource/{id}", method = RequestMethod.DELETE)
    public Boolean removeResource(@PathVariable("id") Long id){
        try {
            if(resourceCRUDRepo.exists(id))
                resourceCRUDRepo.delete(id);
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @RequestMapping(value = "/addProjectResourceMapping/", method = RequestMethod.POST)
    public ProjectResourceMapping createResource(@RequestBody ProjectResourceMapping resource){

        try {
            if(resource !=null)
                projectResourceMappingCRUDRepo.save(resource);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resource;
    }

    @RequestMapping(value = "/updateProjectResouceMapping/{id}", method = RequestMethod.PUT)
    public ProjectResourceMapping updateResource(@PathVariable("id") Long id,@RequestBody ProjectResourceMapping resource){
        try {
            if(resource !=null && projectResourceMappingCRUDRepo.exists(id)){
                resource.setId(id);
                projectResourceMappingCRUDRepo.save(resource);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resource;
    }

    @RequestMapping(value = "/getAllProjectResourceMappings", method = RequestMethod.GET)
    public List<ProjectResourceMapping> getAllProjectReourcesMappings(){
        List<ProjectResourceMapping> resourceList = new ArrayList<>();
        try {
            resourceList = (List<ProjectResourceMapping>) projectResourceMappingCRUDRepo.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resourceList;
    }

    @RequestMapping(value = "/getResourcesForProject", method = RequestMethod.GET)
    public List<ProjectResourceMapping> getResourceForProject(String projectId){
        List<ProjectResourceMapping> projectResourceList = new ArrayList<>();
        try {
            projectResourceList = projectResourceMappingCRUDRepo.findAllByProjectId(projectId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectResourceList;
    }

    @RequestMapping(value = "/getProjectResourceMappingById", method = RequestMethod.GET)
    ProjectResourceMapping getProjectResourceMappingById(Long id){
        ProjectResourceMapping projectResourceMapping = new ProjectResourceMapping();
        try {
            if(projectResourceMappingCRUDRepo.exists(id))
                projectResourceMapping = projectResourceMappingCRUDRepo.findOne(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectResourceMapping;
    }

    @RequestMapping(value = "/removeProjectResourceMapping/{id}", method = RequestMethod.GET)
    public Boolean removeProjectResourceMapping(@PathVariable("id") Long id){
        try {
            if(projectResourceMappingCRUDRepo.exists(id))
                projectResourceMappingCRUDRepo.delete(id);
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public ResourceCRUD getResourceCRUDRepo() {
        return resourceCRUDRepo;
    }

    public void setResourceCRUDRepo(ResourceCRUD resourceCRUDRepo) {
        this.resourceCRUDRepo = resourceCRUDRepo;
    }

    public ProjectCRUD getProjectCRUDRepo() {
        return projectCRUDRepo;
    }

    public void setProjectCRUDRepo(ProjectCRUD projectCRUDRepo) {
        this.projectCRUDRepo = projectCRUDRepo;
    }

    public ProjectResourceMappingCRUD getProjectResourceMappingCRUDRepo() {
        return projectResourceMappingCRUDRepo;
    }

    public void setProjectResourceMappingCRUDRepo(ProjectResourceMappingCRUD projectResourceMappingCRUDRepo) {
        this.projectResourceMappingCRUDRepo = projectResourceMappingCRUDRepo;
    }
}