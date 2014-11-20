package dareboost.api.client.report.pojos;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * 
 * The W3C Markup results described in a report POJO
 * 
 * @author Remi Damlencour
 */
@JsonInclude(value=Include.NON_EMPTY)
public class W3CMarkup {
	public int errorsCount;
	public int warningsCount;
	public String status;
	
	public String charset;
	public String doctype;
	public List<Issue> warnings = new ArrayList<>();
	public List<Issue> errors = new ArrayList<>();

	@JsonInclude(value=Include.NON_EMPTY)
	public static class Issue{
		public String col;
		public String line;
		public String source;
		public String message;
		public String details;
	}
}