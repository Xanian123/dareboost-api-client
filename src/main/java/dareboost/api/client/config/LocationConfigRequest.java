package dareboost.api.client.config;

import dareboost.api.client.global.APIRequest;

/**
 * 
 * The location config REQUEST POJO
 * 
 * @author Remi Damlencour
 */
public class LocationConfigRequest extends APIRequest {
	public LocationConfigRequest(String token, String location) {
		super(token);
		this.location = location;
	}

	public String location;
}
