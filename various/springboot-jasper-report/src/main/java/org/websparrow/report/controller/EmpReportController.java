package org.websparrow.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.websparrow.report.service.EmployeeReportService;

@RestController
@RequestMapping("/employee")
public class EmpReportController {

	@Autowired
	private EmployeeReportService employeeReportService;

	@GetMapping("/report")
	public String empReport() {

		return employeeReportService.generateReport();
	}
}
