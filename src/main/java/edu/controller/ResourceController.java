package edu.controller;

import edu.model.ProjectResourceMapping;
import edu.model.Resource;
import edu.service.ProjectResourceMappingCRUD;
import edu.service.ResourceCRUD;
import edu.service.ProjectCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/addResource/{resource}", method = RequestMethod.GET)
    public Resource createResource(@PathVariable("resource") Resource resource){

        try {
            if(resource !=null)
                resourceCRUDRepo.save(resource);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resource;
    }

    @RequestMapping(value = "/updateResouce/{resource}", method = RequestMethod.GET)
    public Resource updateResource(@PathVariable("resource") Resource resource){

        try {
            if(resource !=null)
                resourceCRUDRepo.save(resource);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resource;
    }

    @RequestMapping("/getAllResources")
    public List<Resource> getAllResources(){
        List<Resource> resourceList = new ArrayList<>();
        try {
            resourceList = (List<Resource>) resourceCRUDRepo.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resourceList;
    }

    @RequestMapping("/getResourceByName")
    public Resource getResourceByName(String resourceName){
        Resource resource = new Resource();
        try {
            resource = resourceCRUDRepo.findFirstByResourceName(resourceName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resource;
    }

    @RequestMapping("/getResourceById")
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

    @RequestMapping(value = "/removeResource/{id}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/addProjectResourceMapping/{resource}", method = RequestMethod.GET)
    public ProjectResourceMapping createResource(@PathVariable("resource") ProjectResourceMapping resource){

        try {
            if(resource !=null)
                projectResourceMappingCRUDRepo.save(resource);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resource;
    }

    @RequestMapping(value = "/updateProjectResouceMapping/{resource}", method = RequestMethod.GET)
    public ProjectResourceMapping updateResource(@PathVariable("resource") ProjectResourceMapping resource){
        try {
            if(resource !=null)
                projectResourceMappingCRUDRepo.save(resource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resource;
    }

    @RequestMapping("/getAllProjectResourceMappings")
    public List<ProjectResourceMapping> getAllProjectReourcesMappings(){
        List<ProjectResourceMapping> resourceList = new ArrayList<>();
        try {
            resourceList = (List<ProjectResourceMapping>) projectResourceMappingCRUDRepo.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resourceList;
    }

    @RequestMapping("/getResourcesForProject")
    public List<ProjectResourceMapping> getResourceForProject(String projectId){
        List<ProjectResourceMapping> projectResourceList = new ArrayList<>();
        try {
            projectResourceList = projectResourceMappingCRUDRepo.findAllByProjectId(projectId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectResourceList;
    }

    @RequestMapping("/getProjectResourceMappingById")
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