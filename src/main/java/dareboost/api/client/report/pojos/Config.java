package dareboost.api.client.report.pojos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dareboost.api.client.config.Browser;
import dareboost.api.client.global.Bandwidth;
import dareboost.api.client.global.BasicAuth;
import dareboost.api.client.global.Screen;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * The config described in a report POJO
 * 
 * @author Remi Damlencour
 */
@JsonInclude(value=Include.NON_EMPTY)
public class Config {
	public String location;
	public Browser browser;
	public boolean isMobile;
	public Bandwidth bandwidth;
	public Integer latency;
	public boolean isPrivate;
	public Screen screen;
	public BasicAuth basicAuth;
	public List<Map<String, String>> postData = new ArrayList<>();
	public List<Map<String, String>> header = new ArrayList<>();	
}
