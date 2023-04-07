package br.com.fbm.frametest.requests.nytimes;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;

/**
 * {@code NyTimesRequest} given implementations
 * to do request to the api https://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml
 * for the All it's Entities
 *
 * @author Fernando Bino Machado
 */
public class NyTimesRequest {

	private String baseUriApi;
	
	/**
	 * @param baseUriApi the baseUriApi to set
	 */
	public void setBaseUriApi(String baseUriApi) {
		this.baseUriApi = baseUriApi;
	}
	
	public Response getXmlChannel() {
		
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
