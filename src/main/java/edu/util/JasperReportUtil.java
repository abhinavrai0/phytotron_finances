package edu.util;

import edu.model.Client;
import edu.model.InvoiceReportView;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Pinakin Abhyankar on 6/7/2017.
 */
public class JasperReportUtil {
    //This method is used to generate PDF report out of Jasper template.
    public int generateReport(List<InvoiceReportView> invoiceReportViewRecords, String templateFilePath, String reportFilePath){
        JRBeanCollectionDataSource beanCollectionDataSource = null;
        try{

            beanCollectionDataSource = new JRBeanCollectionDataSource(invoiceReportViewRecords,true);
            JasperPrint jPrint = JasperFillManager.fillReport(templateFilePath, new HashMap<String, Object>(), beanCollectionDataSource);
            JasperExportManager.exportReportToPdfFile(jPrint,reportFilePath);

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }
}
