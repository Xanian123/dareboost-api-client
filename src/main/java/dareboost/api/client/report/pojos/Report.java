package dareboost.api.client.report.pojos;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * The performance report POJO
 * 
 * @author Remi Damlencour
 */
@JsonInclude(value=Include.NON_EMPTY)
public class Report {
	public String publicReportUrl;
	public Long date;
	public String url;
	public String lang;
	public Config config;
	public Summary summary;
	public List<Category> categories = new ArrayList<>();
	public List<Tip> tips = new ArrayList<>();
	public List<Techno> technos = new ArrayList<>();
	public W3C w3cValidators = new W3C();
	public PerformanceTimings performanceTimings;
	
	public enum Missing{
		RULES,
		W3C_HTML,
		W3C_CSS,
		PERFORMANCE_TIMINGS
	}
}
