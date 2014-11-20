package dareboost.api.client.global;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * The basic auth POJO
 * 
 * @author Remi Damlencour
 */

@JsonInclude(value=Include.NON_EMPTY)
public class BasicAuth {
	public String user;
	public String password;
}
