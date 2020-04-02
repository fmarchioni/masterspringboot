package org.websparrow.report.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.websparrow.report.dto.Employee;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class EmployeeReportService {

	private List<Employee> empList = Arrays.asList(
			new Employee(1, "Sandeep", "Data Matrix", "Front-end Developer", 20000),
			new Employee(2, "Prince", "Genpact", "Consultant", 40000),
			new Employee(3, "Gaurav", "Silver Touch ", "Sr. Java Engineer", 47000),
			new Employee(4, "Abhinav", "Akal Info Sys", "CTO", 700000));

	public String generateReport() {
		try {

			String reportPath = System.getProperty("user.dir");

			// Compile the Jasper report from .jrxml to .japser
			JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "/employee-rpt.jrxml");

			// Get your data source
			JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(empList);

			// Add parameters
			Map<String, Object> parameters = new HashMap<>();

			parameters.put("createdBy", "Websparrow.org");

			// Fill the report
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
					jrBeanCollectionDataSource);

			// Export the report to a PDF file
			JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "/Emp-Rpt.pdf");

			System.out.println("Done");

			return "Report successfully generated @path= " + reportPath;

		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
