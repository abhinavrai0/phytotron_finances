package edu.edu.report;

import edu.model.Client;
import edu.service.ClientCRUD;
import edu.util.JasperReportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pinakin Abhyankar on 6/7/2017.
 */
public class BatchInvoiceGenerator {

    public void generateInvoice(List<Client> clientList, String templatePath, String filePath){
        JasperReportUtil jasperReportUtil = new JasperReportUtil();
        jasperReportUtil.generateReport(clientList,templatePath,filePath);
    }
}
