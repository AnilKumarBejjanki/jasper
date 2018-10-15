package com.jasper.chart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;

public class JasperReportFill {
	
   @SuppressWarnings("unchecked")
   public static void main(String[] args) {
	   
	   
      String sourceFileName = "C://Development_machine/JasperReports/practise/report2.jrxml";
      String destinationFileName = "C://Development_machine/JasperReports/practise/report2.jasper";
      String printFileName = null;
      
      
      DataBeanList DataBeanList = new DataBeanList();
      ArrayList<DataBean> dataList = DataBeanList.getDataBeanList();
      JRBeanCollectionDataSource beanColDataSource =
         new JRBeanCollectionDataSource(dataList);

      Map parameters = new HashMap();
      try {
    	 JasperCompileManager.compileReportToFile(sourceFileName,destinationFileName);
    	 
         printFileName = JasperFillManager.fillReportToFile(destinationFileName,
            parameters, beanColDataSource);
         if (printFileName != null) {
            /**
             * 1- export to PDF
             */
            JasperExportManager.exportReportToPdfFile(printFileName,
               "C://Development_machine/JasperReports/practise/report2.pdf");

            /**
             * 2- export to HTML
             */
            JasperExportManager.exportReportToHtmlFile(printFileName,
               "C://Development_machine/JasperReports/practise/report2.html");

            /**
             * 3- export to Excel sheet	
             */
            JRXlsExporter exporter = new JRXlsExporter();

            exporter.setParameter(JRExporterParameter.INPUT_FILE_NAME,
               printFileName);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
               "C://Development_machine/JasperReports/practise/report2.xls");

            exporter.exportReport();
         }
      } catch (JRException e) {
         e.printStackTrace();
      }
   }
}
