package dareboost.api.client.global;

/**
 * 
 * The RESPONSE POJO
 * 
 * @author Remi Damlencour
 */
public class APIResponse {
	public String message;
	public Integer status;
	
	public boolean inError () {
		return status >= 300; 
	}
}
