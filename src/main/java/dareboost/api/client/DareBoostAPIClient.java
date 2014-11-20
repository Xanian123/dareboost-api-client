package dareboost.api.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import dareboost.api.client.analyse.LaunchAuditRequest;
import dareboost.api.client.analyse.LaunchAuditResponse;
import dareboost.api.client.config.ConfigRequest;
import dareboost.api.client.config.ConfigResponse;
import dareboost.api.client.config.LocationConfigRequest;
import dareboost.api.client.config.LocationConfigResponse;
import dareboost.api.client.global.APIResponse;
import dareboost.api.client.mapper.APIMapper;
import dareboost.api.client.report.ReportRequest;
import dareboost.api.client.report.ReportResponse;
import dareboost.api.client.tracking.MonitoringLastReportRequest;
import dareboost.api.client.tracking.MonitoringLastReportResponse;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;




/**
 * This class allows to manipulate the DareBoost API.
 * You will receive an {@link APIResponse}, an object mapping the HTTP response for the requested operation.
 * 
 * Please, use the close method when you do not use the client anymore.
 * 
 * @author Remi Damlencour
 *
 */
public class DareBoostAPIClient {
	private static final String API_URL;
	private static final String LAUNCH_AUDIT_PATH = "analysis/launch";
	private static final String REPORT_PATH = "analysis/report";
	private static final String LAST_REPORT_PATH = "monitoring/last-report";
	private static final String CONFIG_PATH = "config";
	private static final String TOKEN;

	private final CloseableHttpClient httpclient = HttpClients.createDefault();
	
	static {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		API_URL = prop.getProperty("api.url");
		TOKEN = prop.getProperty("api.token");
	}
	
	/**
	 * Close the HTTP client associated to thie {@link DareBoostAPIClient}.
	 * Please, use it when you do not use the client anymore.
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		httpclient.close();
	}
	
	/**
	 * Launch a performance report with the DaareBoost API default parameters
	 * @param url
	 * @return
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws IOException 
	 */
	public LaunchAuditResponse launchAudit(String url) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		// generate default audit request
		return launchAudit(new LaunchAuditRequest(TOKEN, url));
	}
	
	/**
	 * Launch a performance report using a {@link LaunchAuditRequestBuilder}
	 * object to forge a request with non-defaults parameters
	 * 
	 * @param request a personalized request. Use {@link #newLaunchAuditRequestBuilder(String)}
	 * to retrieve a {@link LaunchAuditRequestBuilder} object.
	 * 
	 * @return a {@link LaunchAuditResponse} object, mapping the API response
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws IOException 
	 */
	public LaunchAuditResponse launchAudit(LaunchAuditRequestBuilder request) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException  {
		// generate audit request from the builder
		return launchAudit(request.build());
	}
	
	private LaunchAuditResponse launchAudit(LaunchAuditRequest request) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException  {
		return APIMapper.readLaunchAuditResponse(stringRequestToStringResponse(LAUNCH_AUDIT_PATH, APIMapper.write(request)));
	}
	
	
	/**
	 * Retrieve the performance report for a specific audit.
	 * 
	 * @param monioringId the audit to retrieve
	 * @return a {@link ReportResponse} object, mapping the API response
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public ReportResponse getReport(String id) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		return APIMapper.readReportResponse(stringRequestToStringResponse(REPORT_PATH, APIMapper.write(new ReportRequest(TOKEN, id))));
	}
	
	/**
	 * Retrieve the last report associated to the monitoring.
	 * 
	 * @param monioringId the monitoring to check
	 * @return a {@link ReportResponse} object, mapping the API response
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public MonitoringLastReportResponse getLastReport(String monioringId) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		return APIMapper.readMonitoringLastReportResponse(stringRequestToStringResponse(LAST_REPORT_PATH, APIMapper.write(new MonitoringLastReportRequest(TOKEN, monioringId))));
	}

	/**
	 * Retrieve all the locations and browsers that you can use with the DareBoost API.
	 * 
	 * @return a {@link ConfigResponse} object mapping the API response
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws IOException 
	 */
	public ConfigResponse getConfig() throws JsonParseException, JsonMappingException, JsonProcessingException, IOException  {
		return APIMapper.readConfigResponse(stringRequestToStringResponse(CONFIG_PATH, APIMapper.write(new ConfigRequest(TOKEN))));
	}
	
	/**
	 * Retrieve all the browsers that you can use for a specific location.
	 * 
	 * @return a {@link LocationConfigResponse} object mapping the API response
	 * @throws JsonProcessingException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws IOException 
	 */
	public LocationConfigResponse getConfig(String location) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException  {
		return APIMapper.readLocationConfigResponse(stringRequestToStringResponse(CONFIG_PATH, APIMapper.write(new LocationConfigRequest(TOKEN, location))));
	}
	
	/**
	 * @return the token used by the {@link DareBoostAPIClient} to authenticate you
	 */
	public String getToken() {
		return TOKEN;
	}

	/**
	 * You want to launch an audit with custom parameters (location, bandwidth, screen...) ? 
	 * Use this method to retrieve a {@link LaunchAuditRequest} object, and use it in 
	 * the {@link DareBoostAPIClient#launchAudit(LaunchAuditRequestBuilder)} method.
	 * 
	 * @param url the url to audit
	 * @return a {@link LaunchAuditRequest} object, mapping the request.
	 */
	public LaunchAuditRequestBuilder newLaunchAuditRequestBuilder(String url) {
		return new LaunchAuditRequestBuilder(TOKEN, url);
	}
	
	private String stringRequestToStringResponse(String path, String requestData) throws IOException {
		// init and execute post method
		HttpPost httpPost = new HttpPost(API_URL + path);
		StringEntity entity = new StringEntity(requestData);
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		CloseableHttpResponse response = httpclient.execute(httpPost);
		return EntityUtils.toString(response.getEntity());
	}
}
