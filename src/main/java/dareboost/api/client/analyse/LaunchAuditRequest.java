package dareboost.api.client.analyse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dareboost.api.client.global.APIRequest;
import dareboost.api.client.global.Bandwidth;
import dareboost.api.client.global.BasicAuth;
import dareboost.api.client.global.Browser;
import dareboost.api.client.global.Screen;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * 
 * The launch audit REQUEST POJO
 * 
 * @author Remi Damlencour
 */
@JsonInclude(value=Include.NON_EMPTY)
public class LaunchAuditRequest extends APIRequest {
	public String url;
	
	public String lang;
	public Boolean isPrivate;
	public String location;
	public Boolean mobileAnalysis;
	public Integer latency;
	
	public Browser browser = new Browser();
	public Bandwidth bandwidth = new Bandwidth();
	public Screen screen = new Screen();
	public BasicAuth basicAuth = new BasicAuth();

	public List<Map<String, String>> postData = new ArrayList<>();
	public List<Map<String, String>> headers = new ArrayList<>();
	

	public LaunchAuditRequest(String token, String url, String lang, Boolean isPrivate, String location, Browser browser,
			Boolean mobileAnalysis, Bandwidth bandwidth, Integer latency, Screen screen,
			BasicAuth basicAuth, List<Map<String, String>> postData, List<Map<String, String>> headers) {
		super(token);
		this.url = url;
		this.location = location;
		this.browser = browser;
		this.mobileAnalysis = mobileAnalysis;
		this.bandwidth = bandwidth;
		this.latency = latency;
		this.screen = screen;
		this.basicAuth = basicAuth;
		this.postData = postData;
		this.headers = headers;
	}

	public LaunchAuditRequest(String token, String url) {
		super(token);
		this.url = url;
	}
}
