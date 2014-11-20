package dareboost.api.client.config;

import java.util.List;

import dareboost.api.client.global.APIResponse;

/**
 * 
 * The location config RESPONSE POJO
 * 
 * @author Remi Damlencour
 */
public class LocationConfigResponse extends APIResponse {
	public String location;
	public List<Browser> browsers;
}
