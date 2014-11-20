package dareboost.api.client;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import dareboost.api.client.DareBoostAPIClient;
import dareboost.api.client.analyse.LaunchAuditResponse;
import dareboost.api.client.config.ConfigResponse;
import dareboost.api.client.config.Location;
import dareboost.api.client.config.LocationConfigResponse;
import dareboost.api.client.report.ReportResponse;
import dareboost.api.client.tracking.MonitoringLastReportResponse;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * Test the DareBoost API client calls. 
 * 
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * !!! Caution : these tests are launching some analysis. 		  !!!
 * !!! So they will consume some of your credits if you run them. !!!
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 
 * @author Remi Damlencour
 *
 */
public class DareBoostAPIClientTest {

	private static String LOCATION;
	private static DareBoostAPIClient CLIENT = new DareBoostAPIClient();
	
	/**
	 * Init the test with the location to use
	 */
	@BeforeClass
	public static void initTest(){
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		LOCATION = prop.getProperty("api.easytest.config.location");
	}
	
	/**
	 * Please, do not forget to close the API HTTP client :)
	 */
	@AfterClass
	public static void closeClient() {
		try {
			CLIENT.close();
		} catch (IOException e) {
			e.printStackTrace();
			fail("can't close the API HTTP client: " + e.getMessage());
		}
	}
	
	/**
	 * Get available configuration
	 * It will give you available locations and browsers associated to them
	 */
	@Test
	public void testGlobalConfig() {
		try {
			ConfigResponse configResponse = CLIENT.getConfig();
			assertFalse(configResponse.inError());
			assertFalse(configResponse.config.isEmpty());
		} catch (IOException e) {
			e.printStackTrace();
			fail("get an exception : " + e.getMessage());
		}
	}
	
	/** 
	 * Get the available browsers for the given location
	 */
	@Test
	public void testLocationConfig() {
		try {
			LocationConfigResponse configResponse = CLIENT.getConfig(LOCATION);
			assertFalse(configResponse.inError());
			assertTrue(configResponse.location.equals(LOCATION));
			assertTrue(configResponse.browsers.size() > 0);
		} catch (IOException e) {
			e.printStackTrace();
			fail("get an exception : " + e.getMessage());
		}
	}
	
	/**
	 * Request the available configuration 
	 * and launch the analysis of the web page 'http://dareboost.com/' with the location new-york
	 */
	@Test
	public void textConfigThenLaunchAudit(){
		try{
			ConfigResponse configResponse = CLIENT.getConfig();
			assertFalse(configResponse.inError());
			assertFalse(configResponse.config.isEmpty());
			Location locationToUse = null;
			for(Location location : configResponse.config){
				if(location.location.toLowerCase().contains("new-york")){
					locationToUse = location;
					break;
				}
			}
			if(locationToUse == null){
				fail("Wanted location not found");
			}
			
			LaunchAuditResponse launchResponse = CLIENT.launchAudit(
					CLIENT.newLaunchAuditRequestBuilder("http://dareboost.com/").setLocation(locationToUse.location));
			assertFalse(launchResponse.inError());
		} catch (IOException e) {
			e.printStackTrace();
			fail("get an exception : " + e.getMessage());
		}
	}
	
	/**
	 * Launch the analysis of the web page 'http://dareboost.com/' on the location define on the config file
	 * !! Be careful, this consume 1 credit !! 
	 */
	@Test
	public void testCustomLaunchAudit() {
		try {	
			LaunchAuditResponse nycLaunchResponse = CLIENT.launchAudit(
				CLIENT.newLaunchAuditRequestBuilder("http://dareboost.com/").setLocation(LOCATION)
			);
			assertTrue(nycLaunchResponse.status == 200);
		} catch (IOException e) {
			e.printStackTrace();
			fail("get an exception : " + e.getMessage());
		}
	}
	
	/**
	 * Launch the analysis of the web page 'http://dareboost.com/' with default configuration
	 * Get the reportId from the response and ask the API for the report
	 * !! Be careful, this consume 1 credit !!
	 */
	@Test
	public void testDefaultLaunchAuditAndGetReport() throws InterruptedException {
		try {
			// Launch the analysis
			LaunchAuditResponse launchResponse = CLIENT.launchAudit("http://dareboost.com/");
			assertTrue(launchResponse.status == 200);
			
			// get the report id to retrieve the report
			ReportResponse reportResponse = CLIENT.getReport(launchResponse.reportId);
			assertTrue(reportResponse.status == 202);
			
			// While the analysis is in progress, we wait
			while(reportResponse.status == 202) {
				reportResponse = CLIENT.getReport(launchResponse.reportId);
				Thread.sleep(2000);
			}
			
			// The report is arrived
			assertTrue(reportResponse.status == 200 || reportResponse.status == 206);
			System.out.println(reportResponse.report.publicReportUrl);
		} catch (IOException e) {
			e.printStackTrace();
			fail("get an exception : " + e.getMessage());
		}
	}
	
	/**
	 * Get the last report of the monitoring define by his id
	 */
	@Test
	public void testMonitoringLastReport() {
		try {
			// this ID matches a monitoring that is disabled now. The returned data is always the same.
			MonitoringLastReportResponse lastReportResponse = CLIENT.getLastReport("0");
			assertTrue(lastReportResponse.status == 400);
			assertTrue(lastReportResponse.inError());
		} catch (IOException e) {
			e.printStackTrace();
			fail("get an exception : " + e.getMessage());
		}
	}
}
