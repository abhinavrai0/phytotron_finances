package edu.util;

import edu.model.Client;
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
    public int generateReport(List<Client> clientList, String templateFilePath, String reportPath){
        JRBeanCollectionDataSource beanCollectionDataSource = null;
        try{
            
            beanCollectionDataSource = new JRBeanCollectionDataSource(clientList,false);
            JasperPrint jPrint = JasperFillManager.fillReport(templateFilePath, new HashMap<String, Object>(), beanCollectionDataSource);
            JasperExportManager.exportReportToPdfFile(jPrint,reportPath);

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }
}
