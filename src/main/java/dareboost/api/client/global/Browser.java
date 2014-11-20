package dareboost.api.client.global;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * The browser POJO
 * 
 * @author Remi Damlencour
 */
@JsonInclude(value=Include.NON_EMPTY)
public class Browser {
	public String name;
	public String version;
}
