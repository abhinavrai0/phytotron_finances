package edu.controller;

import edu.model.Client;
import edu.model.Project;
import edu.service.ClientCRUD;
import edu.service.ProjectCRUD;
import edu.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pinak on 6/12/2017.
 */
@RestController
@RequestMapping("/batchInvoice")
public class BatchInvoiceController {
    @Autowired
    private ProjectCRUD projectCrudRepo;
    static final Logger logger = LogManager.getLogger(BatchInvoiceController.class.getName());

    @RequestMapping(value = "/getActiveClients/{quarterStartDate}/{quarterEndDate}/", method = RequestMethod.GET)
    public List<Project> getActiveClients(@PathVariable("quarterStartDate") String quarterStartDate, @PathVariable("quarterEndDate") String quarterEndDate){
        List<Project> projectList = new ArrayList<>();
        System.out.println("Inside getActiveClients Sections....");
        String datePattern = "yyyy-mm-dd";
        Date qStart = DateUtil.convertStringDateToUtilDate(quarterStartDate,datePattern);
        Date qEnd = DateUtil.convertStringDateToUtilDate(quarterEndDate,datePattern);
        projectList = projectCrudRepo.findActiveClientsForQuarter("Active", qStart, qEnd);
        return projectList;
    }
}
