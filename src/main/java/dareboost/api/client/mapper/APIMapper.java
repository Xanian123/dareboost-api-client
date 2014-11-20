package dareboost.api.client.mapper;

import java.io.IOException;

import dareboost.api.client.analyse.LaunchAuditRequest;
import dareboost.api.client.analyse.LaunchAuditResponse;
import dareboost.api.client.config.ConfigRequest;
import dareboost.api.client.config.ConfigResponse;
import dareboost.api.client.config.LocationConfigRequest;
import dareboost.api.client.config.LocationConfigResponse;
import dareboost.api.client.report.ReportRequest;
import dareboost.api.client.report.ReportResponse;
import dareboost.api.client.tracking.MonitoringLastReportRequest;
import dareboost.api.client.tracking.MonitoringLastReportResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class allows to write a json String from a request POJO,
 * and to read a json String to convert it in responses POJO 
 * 
 * @author Remi Damlencour
 */
public class APIMapper {
	private static ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * Read a {@link LaunchAuditRequest} POJO and convert it into a json String
	 * @param request the POJO to convert
	 * @return the json String
	 * @throws JsonProcessingException 
	 */
	public static String write(LaunchAuditRequest request) throws JsonProcessingException {
		return mapper.writeValueAsString(request);
	}
	
	/**
	 * Read a json String and convert it into a {@link LaunchAuditResponse} POJO 
	 * 
	 * @param json the json String to read
	 * @return the {@link LaunchAuditResponse} POJO associated
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public static LaunchAuditResponse readLaunchAuditResponse(String json) throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(json, LaunchAuditResponse.class);
	}
	
	/**
	 * Read a {@link ConfigRequest} POJO and convert it into a json String
	 * @param request the POJO to convert
	 * @return the json String
	 * @throws JsonProcessingException 
	 */
	public static String write(ConfigRequest request) throws JsonProcessingException {
		return mapper.writeValueAsString(request);
	}
	
	/**
	 * Read a json String and convert it into a {@link ConfigResponse} POJO 
	 * 
	 * @param json the json String to read
	 * @return the {@link ConfigResponse} POJO associated
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public static ConfigResponse readConfigResponse(String json) throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(json, ConfigResponse.class);
	}
	
	/**
	 * Read a {@link LocationConfigRequest} POJO and convert it into a json String
	 * @param request the POJO to convert
	 * @return the json String
	 * @throws JsonProcessingException 
	 */
	public static String write(LocationConfigRequest request) throws JsonProcessingException {
		return mapper.writeValueAsString(request);
	}
	
	/**
	 * Read a json String and convert it into a {@link LocationConfigResponse} POJO 
	 * 
	 * @param json the json String to read
	 * @return the {@link LocationConfigResponse} POJO associated
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public static LocationConfigResponse readLocationConfigResponse(String json) throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(json, LocationConfigResponse.class);
	}
	
	/**
	 * Read a {@link ReportRequest} POJO and convert it into a json String
	 * @param request the POJO to convert
	 * @return the json String
	 * @throws JsonProcessingException 
	 */
	public static String write(ReportRequest request) throws JsonProcessingException {
		return mapper.writeValueAsString(request);
	}
	
	/**
	 * Read a json String and convert it into a {@link ReportResponse} POJO 
	 * 
	 * @param json the json String to read
	 * @return the {@link ReportResponse} POJO associated
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public static ReportResponse readReportResponse(String json) throws JsonParseException, JsonMappingException, IOException  {
		return mapper.readValue(json, ReportResponse.class);
	}
	
	/**
	 * Read a {@link MonitoringLastReportRequest} POJO and convert it into a json String
	 * @param request the POJO to convert
	 * @return the json String
	 * @throws JsonProcessingException 
	 */
	public static String write(MonitoringLastReportRequest request) throws JsonProcessingException {
		return mapper.writeValueAsString(request);
	}
	
	/**
	 * Read a json String and convert it into a {@link MonitoringLastReportRequest} POJO 
	 * 
	 * @param json the json String to read
	 * @return the {@link MonitoringLastReportRequest} POJO associated
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public static MonitoringLastReportResponse readMonitoringLastReportResponse(String json) throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(json, MonitoringLastReportResponse.class);
	}
}
