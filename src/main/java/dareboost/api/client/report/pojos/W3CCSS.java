package dareboost.api.client.report.pojos;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * The W3C CSS results described in a report POJO
 * 
 * @author Remi Damlencour
 */
@JsonInclude(value=Include.NON_EMPTY)
public class W3CCSS {
	public int errorsCount;
	public int warningsCount;
	public String status;
	
	public List<Warning4Uri>   warnings = new ArrayList<>();
	public List<Error4Uri> errors = new ArrayList<>();
	public W3CCSS() {}
	@JsonInclude(value=Include.NON_EMPTY)
	public static class Error4Uri{
		public String url;
		public List<Content> errors = new ArrayList<>();
		
		@JsonInclude(value=Include.NON_EMPTY)
		public static class Content extends Warning4Uri.BaseContent{
			public String type;
		}
	}

	@JsonInclude(value=Include.NON_EMPTY)
	public static class Warning4Uri{
		public String url;
		public List<BaseContent> warnings = new ArrayList<>();

		@JsonInclude(value=Include.NON_EMPTY)
		public static class BaseContent{
			public String source;
			public String line;
			public String message;
		}
	}
}