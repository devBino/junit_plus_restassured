package br.com.fbm.frametest.requests.reqres;

import static io.restassured.RestAssured.*;

import br.com.fbm.frametest.bo.reqres.UserBO;
import br.com.fbm.frametest.converters.json.GenericJsonConverter;
import io.restassured.response.Response;

/**
 * {@code UserRequest} given implementations
 * to do request to the api reqres.in
 * for the User entity
 *
 * @author Fernando Bino Machado
 */
public class UserRequest {
	
	private String baseUriApi;
	
	/**
	 * @param baseUriApi the baseUriApi to set
	 */
	public void setBaseUriApi(String baseUriApi) {
		this.baseUriApi = baseUriApi;
	}

	public Response create(final UserBO pUserBO) {
	
		try {
		
			baseURI = baseUriApi;
			
			return given()
					.body(GenericJsonConverter.objBoToString(pUserBO))
					.when()
					.post("/api/users");
			
		}catch(final Exception exception) {
			System.out.println(exception.getMessage());
			return null;
		}
		
	}
	
	public Response update(final UserBO pUserBO) {
		
		try {
			
			baseURI = baseUriApi;
			
			return given()
					.body(GenericJsonConverter.objBoToString(pUserBO))
					.when()
					.put("/api/users/" + String.valueOf( pUserBO.getId() ) );
			
		}catch(final Exception exception) {
			System.out.println(exception.getMessage());
			return null;
		}
		
	}
	
	public Response getUser(final int pId) {
		
		try {
			
			baseURI = baseUriApi;
			
			return given()
					.get( "/api/users/" + String.valueOf(pId) );
			
		}catch(final Exception exception) {
			System.out.println(exception.getMessage());
			return null;
		}
		
	}
	
	public Response listUsers() {
		
		try {
		
			baseURI = baseUriApi;
			
			return given()
					.get("/api/users");
			
		}catch(final Exception exception) {
			System.out.println(exception.getMessage());
			return null;
		}
		
	}
	
	public Response delete(final int pId) {
		
		try {
			
			return when()
					.delete("/api/users/" + String.valueOf(pId));
			
		}catch(final Exception exception) {
			System.out.println(exception.getMessage());
			return null;
		}
		
	}
	
	
}
