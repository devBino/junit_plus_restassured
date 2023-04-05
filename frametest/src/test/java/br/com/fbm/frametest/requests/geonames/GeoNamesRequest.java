package br.com.fbm.frametest.requests.geonames;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;

/**
 * {@code GeoNamesRequest} given implementations
 * to do request to the api http://www.geonames.org/export/web-services.html
 * for the All it's Entities
 *
 * @author Fernando Bino Machado
 */
public class GeoNamesRequest {

	private String baseUriApi;
	
	/**
	 * @param baseUriApi the baseUriApi to set
	 */
	public void setBaseUriApi(String baseUriApi) {
		this.baseUriApi = baseUriApi;
	}
	
	public Response listCodes() {
		
		try {
			
			baseURI = baseUriApi;
			
			return given()
					.get();
			
		}catch(final Exception exception) {
			System.out.println(exception.getMessage());
			return null;
		}
		
	}
	
	
}
