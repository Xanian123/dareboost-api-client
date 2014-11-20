package dareboost.api.client.tracking;

import dareboost.api.client.global.APIRequest;

/**
 * 
 * The last monitoring report REQUEST POJO
 * 
 * @author Remi Damlencour
 */
public class MonitoringLastReportRequest extends APIRequest {
	public MonitoringLastReportRequest(String token, String monitoringId) {
		super(token);
		this.monitoringId = monitoringId;
	}

	public String monitoringId;

}
