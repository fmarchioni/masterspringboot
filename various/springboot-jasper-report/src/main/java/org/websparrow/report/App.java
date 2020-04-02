package org.websparrow.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.websparrow.report.service.EmployeeReportService;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public String generateReport(final EmployeeReportService employeeReportService) {

		String msg = employeeReportService.generateReport();

		System.out.println(msg);

		return msg;
	}
}
