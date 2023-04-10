package br.com.fbm.frametest.requests.fakestore;

import static io.restassured.RestAssured.*;

import br.com.fbm.frametest.bo.fakestore.ProductBO;
import br.com.fbm.frametest.converters.fakestore.FakeStoreConverter;
import io.restassured.response.Response;

/**
 * {@code FakeStoreRequest} given implementations
 * to do request to the api https://fakestoreapi.com/docs
 * for the All it's Entities
 *
 * @author Fernando Bino Machado
 */
public class FakeStoreRequest {

	private String baseUriApi;
	
	/**
	 * @param baseUriApi the baseUriApi to set
	 */
	public void setBaseUriApi(String baseUriApi) {
		this.baseUriApi = baseUriApi;
	}
	
	public Response listProducts() {
		
		try {
			
			baseURI = baseUriApi;
			
			return given()
					.get();
			
		}catch(final Exception exception) {
			System.out.println(exception.getMessage());
			return null;
		}
		
	}
	
	public Response getProductById(final int pId) {
		
		try {
			
			baseURI = baseUriApi;
			
			return given()
					.get("/" + String.valueOf(pId));
			
		}catch(final Exception exception) {
			System.out.println(exception.getMessage());
			return null;
		}
		
	}
	
	public Response saveProduct(final ProductBO pProductBO) {
		
		try {
			
			baseURI = baseUriApi;
			
			return given()
					.body(FakeStoreConverter.objBoToString(pProductBO))
					.when()
					.post();
			
		}catch(final Exception exception) {
			System.out.println(exception.getMessage());
			return null;
		}
		
	}
	
	public ProductBO saveProductAndReturnBO(final ProductBO pProductBO) {
		
		try {
			
			baseURI = baseUriApi;
			
			final Response responseSave = given()
					.body(FakeStoreConverter.objBoToString(pProductBO))
					.when()
					.post();
			
			final ProductBO savedProductBO = (ProductBO) FakeStoreConverter
					.stringToObjBO(responseSave.getBody().asString(), ProductBO.class);
			
			return savedProductBO;
			
		}catch(final Exception exception) {
			System.out.println(exception.getMessage());
			return null;
		}
		
	}
	
	
}
