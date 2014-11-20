package dareboost.api.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dareboost.api.client.analyse.LaunchAuditRequest;
import dareboost.api.client.global.Bandwidth;
import dareboost.api.client.global.BasicAuth;
import dareboost.api.client.global.Browser;
import dareboost.api.client.global.Screen;



/**
 * This class is used to bind a request that want to launch a performance audit.
 * So you can custom the request and use non-defaults parameters.
 * 
 * Use it in the {@link DareBoostAPIClient#launchAudit(LaunchAuditRequestBuilder)} method.
 * 
 * @author Remi Damlencour
 *
 */
public class LaunchAuditRequestBuilder {
	private final String token;
	private final String url;
	
	private String lang;
	private String location;
	private Browser browser;
	private Boolean isPrivate;
	private Boolean mobileAnalysis;
	private Bandwidth bandwidth;
	private Integer latency;
	private Screen screen;
	private BasicAuth basicAuth;
	private List<Map<String, String>> postData;
	private List<Map<String, String>> headers;
	
	/**
	 * Creates the builder : we use a {@link LaunchAuditRequest} with default values
	 * @param token
	 * @param url
	 */
	protected LaunchAuditRequestBuilder(String token, String url) {
		this.token = token;
		this.url = url;
		LaunchAuditRequest defaultRequest = new LaunchAuditRequest(token, url);
		lang = defaultRequest.lang;
		location = defaultRequest.location;
		browser = defaultRequest.browser;
		isPrivate = defaultRequest.isPrivate;
		mobileAnalysis = defaultRequest.mobileAnalysis;
		bandwidth = defaultRequest.bandwidth;
		latency = defaultRequest.latency;
		screen = defaultRequest.screen;
		basicAuth = defaultRequest.basicAuth;
		postData = defaultRequest.postData;
		headers = defaultRequest.headers;
	}
	
	/**
	 * set the location used for the audit
	 * 
	 * @param location
	 * @return the builder
	 */
	public LaunchAuditRequestBuilder setLocation(String location) {
		this.location = location;
		return this;
	}
	
	/**
	 * set the browser used for the audit
	 * 
	 * @param name
	 * @param version
	 * @return the builder
	 */
	public LaunchAuditRequestBuilder setBrowser(String name, String version) {
		this.browser.name = name;
		this.browser.version = version;
		return this;
	}
	
	/**
	 * set the height and the width of the screen used for the audit
	 * 
	 * @param height
	 * @param width
	 * @return the builder
	 */
	public LaunchAuditRequestBuilder setScreenDimension(Integer height, Integer width) {
		this.screen.height = height;
		this.screen.width = width;
		return this;
	}
	
	/**
	 * set the upstream used during the audit
	 * 
	 * @param upstream
	 * @return the builder
	 */
	public LaunchAuditRequestBuilder setUpstream(Integer upstream) {
		this.bandwidth.upstream = upstream;
		return this;
	}
	
	/**
	 * set the downstream used during the audit
	 * 
	 * @param downstream
	 * @return the builder
	 */
	public LaunchAuditRequestBuilder setDownstream(Integer downstream) {
		this.bandwidth.downstream = downstream;
		return this;
	}
	
	/**
	 * set the new network latency
	 * 
	 * @param latency
	 * @return the builder
	 */
	public LaunchAuditRequestBuilder setLatency(Integer latency) {
		this.latency = latency;
		return this;
	}
	
	/**
	 * set a basic authenticate
	 * 
	 * @param user
	 * @param password
	 * @return the builder
	 */
	public LaunchAuditRequestBuilder setBasicAuth(String user, String password) {
		this.basicAuth.user = user;
		this.basicAuth.password = password;
		return this;
	}
	
	/**
	 * add a data to the POST data
	 * 
	 * @param key
	 * @param value
	 * @return the builder
	 */
	public LaunchAuditRequestBuilder addPostData(final String key, final String value) {
		this.postData.add(new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put("key", key);
				put("value", value);
			}
		});
		return this;
	}

	/**
	 * add a HTTP header (replace the default header if the header is already used)
	 * 
	 * @param key
	 * @param value
	 * @return the builder
	 */
	public LaunchAuditRequestBuilder addHeader(final String name, final String value) {
		this.headers.add(new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put("name", name);
				put("value", value);
			}
		});
		return this;
	}
	
	/**
	 * Build the {@link LaunchAuditRequest} from the builder
	 * @return the wanted {@link LaunchAuditRequest}
	 */
	public LaunchAuditRequest build() {
		return new LaunchAuditRequest(token, url, lang, isPrivate, location, browser, mobileAnalysis, bandwidth, latency, screen, basicAuth, postData, headers);
	}
}
