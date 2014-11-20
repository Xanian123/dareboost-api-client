package dareboost.api.client.report;

import java.util.ArrayList;
import java.util.List;

import dareboost.api.client.global.APIResponse;
import dareboost.api.client.report.pojos.Report;
import dareboost.api.client.report.pojos.Report.Missing;

/**
 * 
 * The report RESPONSE POJO
 * 
 * @author Remi Damlencour
 */
public class ReportResponse extends APIResponse {
	public List<Missing> missing = new ArrayList<>();
	public Report report;
}
