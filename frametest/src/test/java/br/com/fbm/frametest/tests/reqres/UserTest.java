package br.com.fbm.frametest.tests.reqres;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.BeforeClass;
import org.junit.Ignore;

import br.com.fbm.frametest.bo.reqres.ResponseGetUserBO;
import br.com.fbm.frametest.bo.reqres.ResponseUsersBO;
import br.com.fbm.frametest.bo.reqres.UserBO;
import br.com.fbm.frametest.converters.reqres.UserConverter;
import br.com.fbm.frametest.requests.reqres.UserRequest;
import io.restassured.response.Response;

/**
 * {@code UserTest} given implementation
 * to do tests for the user entity for the
 * reqres.in api
 *
 * @author Fernando Bino Machado
 */
public class UserTest {

	private static UserBO newUser;
	private static UserBO savedUser;
	private static Response resp;
	private static UserRequest userRequest;
	
	@BeforeClass
	public static void setUp() {
		
		newUser = new UserBO();
		
		newUser.setName("fernando bino");
		newUser.setJob("software specialist");
		
		userRequest = new UserRequest();
		
		resp = userRequest.create(newUser);
		
		resp
			.then()
			.statusCode(201);

		savedUser = (UserBO) UserConverter.stringToObjBO(resp.getBody().asString(), UserBO.class);
		
	}
	
	@Test
	@Ignore
	public void simpleTest() {
		
		baseURI = "https://reqres.in/api/users?page=2";
		given()
			.get()
			.then()
				.statusCode(200)
				.body("data[0].id", equalTo(7))
				.log()
				.all();
		
	}

	@Test
	public void testCreateUser() {
		assertNotNull("Request Was Successfully", resp);
		assertTrue("An User Id Exists", savedUser.getId() > 0 );
		assertEquals("Same name user", newUser.getName(), savedUser.getName());
	}
	
	@Test
	public void testGetUserById() {
		
		final Response respGetUser = userRequest.getUser(savedUser.getId());
		
		if( respGetUser == null ) {
			fail("User was not found");
			return;
		}
		
		respGetUser
			.then()
			.statusCode(200);
		
		final ResponseGetUserBO respUsersBO = (ResponseGetUserBO) UserConverter
				.stringToObjBO(respGetUser.getBody().asString(), ResponseGetUserBO.class);

		assertNotNull("An user was found by received id", respUsersBO.getData() );
		
		if( respUsersBO.getData() == null ) {
			return;
		}
		
		assertNotNull("User Email was found", respUsersBO.getData().getEmail() );
		assertNotNull("User Avatar was found", respUsersBO.getData().getAvatar() );
		
	}
	
	@Test
	public void testeUserList() {
	
		final Response respListUsers = userRequest.listUsers();
		
		if( respListUsers == null ) {
			fail("Users was not found");
			return;
		}
		
		respListUsers
			.then()
			.statusCode(200);
			
		final ResponseUsersBO respUsersBO = (ResponseUsersBO) UserConverter
				.stringToObjBO(respListUsers.getBody().asString(), ResponseUsersBO.class);
	
		assertNotNull("Response was converted to the BO object", respUsersBO);
		
		assertTrue("Users registers was found", !respUsersBO.getData().isEmpty() );
		
	}
	
	
}
