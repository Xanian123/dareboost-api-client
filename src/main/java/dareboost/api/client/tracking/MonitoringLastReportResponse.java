package dareboost.api.client.tracking;

import java.util.ArrayList;
import java.util.List;

import dareboost.api.client.report.ReportResponse;

/**
 * 
 * The last monitoring report RESPONSE POJO
 * 
 * @author Remi Damlencour
 */
public class MonitoringLastReportResponse extends ReportResponse {
	public Long lastExecution;
	public List<Alert> alerts = new ArrayList<>();
}
