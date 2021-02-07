package com.mastertheintegration.camel;


import javax.jws.WebService;
import org.apache.camel.example.reportincident.InputReportIncident;
import org.apache.camel.example.reportincident.OutputReportIncident;
import org.apache.camel.example.reportincident.ReportIncidentEndpoint;

@WebService(targetNamespace = "http://reportincident.example.camel.apache.org", name = "ReportIncidentEndpoint")
public class ReportIncidentEndpointService implements ReportIncidentEndpoint {

	public OutputReportIncident reportIncident(InputReportIncident in) {

		System.out.println("\n\n\nInvoked real web service: id=" + in.getIncidentId()
				+ " by " + in.getGivenName() + " " + in.getFamilyName() + "\n\n\n");

		OutputReportIncident out = new OutputReportIncident();
		out.setCode("OK;" + in.getIncidentId());
		return out;
	}



}