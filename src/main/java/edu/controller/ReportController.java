package edu.controller;

import edu.edu.report.BatchInvoiceGenerator;
import edu.model.Client;
import edu.service.ClientCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pinakin Abhyankar on 6/8/2017.
 */

@RestController
@RequestMapping("/invoiceReport")
public class ReportController {

    @Autowired
    private ClientCRUD clientCrudRepo;

    @RequestMapping(value = "/generateInvoice/", method= RequestMethod.GET)
    public void getAllRates() {
        System.out.println("Inside Invoice generating controller....");
        List<Client> clientList =new ArrayList<Client>();
        clientList = (List<Client>) clientCrudRepo.findAll();
        BatchInvoiceGenerator batchInvoiceGenerator = new BatchInvoiceGenerator();
        String template = "D:\\Pinakin\\Phytotron\\Jasper_Reports\\billing_invoice.jasper";
        String filePath = "D:\\Pinakin\\Phytotron\\Jasper_Reports\\billing_invoice.pdf";
        batchInvoiceGenerator.generateInvoice(clientList,template,filePath);
    }
}
