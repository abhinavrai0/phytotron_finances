package edu.controller;

import edu.model.Invoice;
import edu.model.InvoiceReportView;
import edu.model.Project;
import edu.service.InvoiceCRUD;
import edu.service.InvoiceReportViewCRUD;
import edu.service.ProjectCRUD;
import edu.util.DateUtil;
import edu.util.JasperReportUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Autowired
    private InvoiceCRUD invoiceCRUDRepo;

    @Autowired
    private InvoiceReportViewCRUD invoiceReportViewCRUDRepo;

    @Value("${name}")
    String name;

    @Value("${invoiceJasperTemplate}")
    String invoiceTemplatePath = "D:\\Pinakin\\Phytotron\\Jasper_Reports\\billing_invoice.jasper";

    @Value("${invoiceDirectory}")
    String invoiceDirectory = "D:\\Pinakin\\Phytotron\\Jasper_Reports";

    static final Logger logger = LogManager.getLogger(BatchInvoiceController.class.getName());

    private JasperReportUtil reportUtil;

    @RequestMapping(value = "/getActiveClients/{quarterStartDate}/{quarterEndDate}", method = RequestMethod.GET)
    public List<Project> getActiveClients(@PathVariable("quarterStartDate") String quarterStartDate, @PathVariable("quarterEndDate") String quarterEndDate){
        List<Project> projectList = new ArrayList<>();
        System.out.println("Inside getActiveClients Sections....");
        String datePattern = "yyyy-MM-dd";
        Date qStart = DateUtil.convertStringDateToUtilDate(quarterStartDate,datePattern);
        Date qEnd = DateUtil.convertStringDateToUtilDate(quarterEndDate,datePattern);
        projectList = projectCrudRepo.findActiveClientsForQuarter("Active", qStart, qEnd);
        return projectList;
    }

    @RequestMapping(value = "/generateInvoice/{project_ids}", method = RequestMethod.GET)
    public List<Invoice> generateBatchInvoices(@PathVariable("project_ids") List<Long> projectIds){
        List<Invoice> invoiceList = new ArrayList<>();

        reportUtil = new JasperReportUtil();
        try {
            if (projectIds!=null) {
                for(Long projectId : projectIds){
                    try {
                        generateInvoice(invoiceList, projectId);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        continue;
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return invoiceList;
    }

    private void generateInvoice(List<Invoice> invoiceList, Long projectId) {
        boolean isInvoiceGenerated = true;
        Invoice currentInvoice = new Invoice();
        Long lastInvoiceId = 0L;
        try {
            System.out.println("Generating Invoice for : "+projectId);
            Project currentProject=projectCrudRepo.findOne(projectId);
            // Invoice lastGeneratedInvoice = invoiceCRUDRepo.findTopByProjectIdEqualsOrderByGeneration_dateDesc(projectId);
            Invoice lastGeneratedInvoice = invoiceCRUDRepo.findFirstByProjectIdOrderByGenerationDateDesc(projectId);
            Date lastBillDate = null;
            if(lastGeneratedInvoice == null || lastGeneratedInvoice.getGenerationDate()==null) {
                lastBillDate = currentProject.getStartDate();
            }
            else{
                //lastBillDate = lastGeneratedInvoice.getGenerationDate();
                lastBillDate = currentProject.getLastBillDate();
                lastInvoiceId = lastGeneratedInvoice.getId();
            }
            // generate bill as per end of day
            Date today = new Date();
            Date generateBillDate= DateUtil.getEndOfDay(today);
            if(lastBillDate==null || currentProject.getStartDate()==null){
                isInvoiceGenerated = false;
                //throw new IllegalArgumentException("There is no start or last invoice date");
            }
            if((lastBillDate!=null && lastBillDate.after(generateBillDate))){
                isInvoiceGenerated = false;
                //throw new IllegalArgumentException("Can not generate bill with older date than the last generated bill");
            }
            long diff = (long)Math.ceil((generateBillDate.getTime() - lastBillDate.getTime())/(60*60*24*1000.0));
            //		long diffDays=TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            //double bill= currentProject.getCurrentBill();
            Double bill= ((double)currentProject.getRateValue().getRate() * (double)diff * currentProject.getCarts());
            //Double prevBalance = 0.00;
            Double prevBalance = currentProject.getCurrentBill();
            currentProject.setLastBillDate(generateBillDate);

            bill = Math.round(bill * 100D) / 100D;
            //currentProject.setCurrentBill(bill);

            currentInvoice.setGenerationDate(today);
            currentInvoice.setCurrent_bill(bill);
            currentInvoice.setPrev_balance(prevBalance);
            currentInvoice.setInvoice_id(currentProject.getProject_id()+Long.toString(lastInvoiceId+1));
            currentInvoice.setProjectId(projectId);
            currentInvoice.setTotoal_due(bill+prevBalance);

            invoiceCRUDRepo.save(currentInvoice);

            String invoiceFilePath = invoiceDirectory+"\\"+currentInvoice.getInvoice_id()+".pdf";
                    /*try {
                        File emptyFile = new File(invoiceFilePath);
                        emptyFile.createNewFile();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }*/

            InvoiceReportView tempInvoiceReportView = invoiceReportViewCRUDRepo.findFirstByInvoiceId(currentInvoice.getInvoice_id());
            List<InvoiceReportView> invoiceReportViewList  = new ArrayList<>();
            invoiceReportViewList.add(tempInvoiceReportView);
            //List<InvoiceReportView> invoiceReportViewList  = invoiceReportViewCRUDRepo.findRecordByInvoiceId(currentInvoice.getInvoice_id());
            reportUtil.generateReport(invoiceReportViewList, invoiceTemplatePath, invoiceFilePath);
        } catch (Exception e) {
            isInvoiceGenerated = false;
            e.printStackTrace();
        }
        if(isInvoiceGenerated)
            invoiceList.add(currentInvoice);
    }

    @RequestMapping(value = "/testInvoiceReportView/{invoiceId}", method = RequestMethod.GET)
    public InvoiceReportView getInvoiceReportViewRecords(@PathVariable("invoiceId") String invoiceId){
        InvoiceReportView result = invoiceReportViewCRUDRepo.findFirstByInvoiceId(invoiceId);
        //List<InvoiceReportView> result = invoiceReportViewCRUDRepo.findRecordByInvoiceId(invoiceId);
        return result;
    }
    @RequestMapping(value = "/insertTestInvoice/{id}/{invoice_id}", method = RequestMethod.GET)
    public String insertInvoice(@PathVariable("id") Long id, @PathVariable("invoice_id") String invoice_id){

        Project project = projectCrudRepo.findOne(new Long(1));
        try{
            Invoice invoice = new Invoice(id,invoice_id,project);
            invoiceCRUDRepo.save(invoice);
        }
        catch (Exception e){
            e.printStackTrace();
            return "Failed : "+e.getMessage();
        }
        return "Success";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        String retMessage = "";
        retMessage = "My name is : "+name;
        return retMessage;
    }
}
