package br.com.fbm.frametest.requests.fipe;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;

/**
 * {@code FipeRequest} given implementations
 * to do request to the api https://parallelum.com.br/fipe/api/v1
 * for the All it's Entities
 *
 * @author Fernando Bino Machado
 */
public class FipeRequest {

	private String baseUriApi;
	
	/**
	 * @param baseUriApi the baseUriApi to set
	 */
	public void setBaseUriApi(String baseUriApi) {
		this.baseUriApi = baseUriApi;
	}
	
	public Response listBrands() {
		
		try {
			
			baseURI = baseUriApi;
			
			return given()
					.get();
			
		}catch(final Exception exception) {
			System.out.println(exception.getMessage());
			return null;
		}
		
	}
	
	public Response listModelsByBrandCode() {
		
		try {
			
			baseURI = baseUriApi;
			
			return given()
					.get();
			
		}catch(final Exception exception) {
			System.out.println(exception.getMessage());
			return null;
		}
		
	}
	
	public Response listYearsModelsByCodeModel(final String pUrl) {
		
		try {
			
			return given()
					.get(pUrl);
			
		}catch(final Exception exception) {
			System.out.println(exception.getMessage());
			return null;
		}
		
	}
	
}
