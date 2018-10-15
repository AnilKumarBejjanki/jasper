package com.jasper.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.Person;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

public class JasperReportFill {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {

		// C://Development_machine/JasperReports/practise
		String sourceFileName = "C://Development_machine/JasperReports/practise/person_template.jrxml";
		String destinationFileName = "C://Development_machine/JasperReports/practise/person_template.jasper";
		String printFileName = null;

		// DataBeanList DataBeanList = new DataBeanList();
		List<Person> dataList = new ArrayList<Person>();
		Person person = new Person();
		person.setId("1");
		person.setName("Anil");
		Person person1 = new Person();
		person1.setId("2");
		person1.setName("Kumar");
		dataList.add(person);
		dataList.add(person1);

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);

		Map parameters = new HashMap();
		try {
			JasperCompileManager.compileReportToFile(sourceFileName, destinationFileName);

			//printFileName = JasperFillManager.fillReportToFile(destinationFileName, parameters, beanColDataSource);

			JasperPrint xlsPrint = JasperFillManager.fillReport(destinationFileName, parameters, beanColDataSource);
			if (xlsPrint != null) {
				/**
				 * 1- export to PDF
				 */
				/*
				 * JasperExportManager.exportReportToPdfFile(printFileName,
				 * "C://Development_machine/JasperReports/practise/person_report.pdf");
				 */

				/**
				 * 2- export to HTML
				 */
				/*
				 * JasperExportManager.exportReportToHtmlFile(printFileName,
				 * "C://Development_machine/JasperReports/practise/person_report.html");
				 */

				/**
				 * 3- export to Excel sheet
				 */
				/*
				 * JRXlsExporter exporter = new JRXlsExporter();
				 * 
				 * exporter.setParameter(JRExporterParameter.INPUT_FILE_NAME, printFileName);
				 */

				/*
				 * exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
				 * Boolean.TRUE);
				 * exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
				 * Boolean.TRUE);
				 * exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
				 * Boolean.FALSE); exporter.setParameter(JRXlsExporterParameter.
				 * IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
				 */
				/*
				 * exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
				 * "C://Development_machine/JasperReports/practise/person_report.xls");
				 * 
				 * exporter.exportReport();
				 */

				/*
				 * JRXlsExporter xlsExporter = new JRXlsExporter();
				 * 
				 * xlsExporter.setExporterInput(new SimpleExporterInput(xlsPrint));
				 * xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(
				 * "C://Development_machine/JasperReports/practise/person_report.xls"));
				 * SimpleXlsReportConfiguration xlsReportConfiguration = new
				 * SimpleXlsReportConfiguration(); SimpleXlsExporterConfiguration
				 * xlsExporterConfiguration = new SimpleXlsExporterConfiguration();
				 * xlsReportConfiguration.setOnePagePerSheet(false);
				 * xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
				 * xlsReportConfiguration.setDetectCellType(true);
				 * xlsReportConfiguration.setWhitePageBackground(false);
				 * xlsExporter.setConfiguration(xlsReportConfiguration);
				 * 
				 * xlsExporter.exportReport();
				 */

				// JasperPrint print = JasperManager.fillReport(input, new HashMap(),
				// jasperReports);
				/*
				 * ByteArrayOutputStream output = new ByteArrayOutputStream(); OutputStream
				 * outputfile= new FileOutputStream(new
				 * File("C://Development_machine/JasperReports/practise/person_report.xls"));
				 * 
				 * // coding For Excel: JRXlsExporter exporterXLS = new JRXlsExporter();
				 * exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, printFileName);
				 * exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, outputfile);
				 * exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
				 * Boolean.FALSE);
				 * exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
				 * Boolean.TRUE);
				 * exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
				 * Boolean.FALSE); exporterXLS.setParameter(JRXlsExporterParameter.
				 * IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
				 * exporterXLS.exportReport(); outputfile.write(output.toByteArray());
				 */

				OutputStream ouputStream = new FileOutputStream(
						new File("C://Development_machine/JasperReports/practise/person_report.xls"));
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

				JRXlsxExporter exporterXLS = new JRXlsxExporter();
				exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, xlsPrint);
                exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
                exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
				exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
				exporterXLS.exportReport();
				ouputStream.write(byteArrayOutputStream.toByteArray());
				ouputStream.flush();
				ouputStream.close();
			}
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
