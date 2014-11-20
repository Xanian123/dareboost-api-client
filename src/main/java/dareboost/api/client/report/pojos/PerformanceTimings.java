package dareboost.api.client.report.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * The performance timings described in a report POJO
 * 
 * @author Remi Damlencour
 */
@JsonInclude(value=Include.NON_EMPTY)
public class PerformanceTimings {
	public Long firstByte;
	public Long domInteractive;
	public Long pageFullyLoad;
}