package dareboost.api.client.report;

import dareboost.api.client.global.APIRequest;

/**
 * 
 * The report REQUEST POJO
 * 
 * @author Remi Damlencour
 */
public class ReportRequest extends APIRequest {
	public ReportRequest(String token, String id) {
		super(token);
		this.reportId = id;
	}

	public String reportId;
}
