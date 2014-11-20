package dareboost.api.client.config;

import java.util.ArrayList;
import java.util.List;

import dareboost.api.client.global.APIResponse;

/**
 * 
 * The config RESPONSE POJO
 * 
 * @author Remi Damlencour
 */
public class ConfigResponse extends APIResponse {
	public List<Location> config = new ArrayList<>();
}
