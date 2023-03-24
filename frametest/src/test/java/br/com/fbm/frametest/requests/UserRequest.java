package br.com.fbm.frametest.requests;

import static io.restassured.RestAssured.*;

import br.com.fbm.frametest.bo.UserBO;
import br.com.fbm.frametest.converters.UserConverter;
import io.restassured.response.Response;

/**
 * {@code UserRequest} given implementations
 * to do request to the api reqres.in
 * for the User entity
 *
 * @author Fernando Bino Machado
 */
public class UserRequest {

	public Response create(final UserBO pUserBO) {
	
		try {
		
			baseURI = "https://reqres.in/api";
			
			return given()
					.body(UserConverter.objBoToString(pUserBO))
					.when()
					.post("/users");
			
		}catch(final Exception exception) {
			System.out.println(exception.getMessage());
			return null;
		}
		
	}
	
	public Response getUser(final int pId) {
		
		try {
			
			baseURI = "https://reqres.in/api/users/" + String.valueOf(pId);
			
			return given()
					.get();
			
		}catch(final Exception exception) {
			System.out.println(exception.getMessage());
			return null;
		}
		
	}
	
	public Response listUsers() {
		
		try {
		
			baseURI = "https://reqres.in/api/users?page=2";
			
			return given()
					.get();
			
		}catch(final Exception exception) {
			System.out.println(exception.getMessage());
			return null;
		}
		
	}
	
	
}
