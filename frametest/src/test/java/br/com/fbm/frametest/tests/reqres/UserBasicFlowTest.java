package br.com.fbm.frametest.tests.reqres;

import static org.junit.Assert.*;

import io.restassured.response.Response;

import br.com.fbm.frametest.abstracts.CrudTestAbstract;

import br.com.fbm.frametest.bo.reqres.UserBO;
import br.com.fbm.frametest.bo.reqres.ResponseGetUserBO;
import br.com.fbm.frametest.bo.reqres.ResponseUsersBO;

import br.com.fbm.frametest.converters.reqres.UserConverter;
import br.com.fbm.frametest.requests.reqres.UserRequest;

/**
 * {@code UserTest} given implementation
 * to do tests for the user entity for the
 * reqres.in api
 *
 * @author Fernando Bino Machado
 */
public class UserBasicFlowTest 
		extends CrudTestAbstract {

	private static UserBO newUser;
	private static UserBO savedUser;
	private static Response resp;
	private static UserRequest userRequest;

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.fbm.frametest.abstracts.BasicCrudTestAbstract#setUpBefore()
	*/
	@Override
	public void setUpBefore() {
		userRequest = new UserRequest();
		userRequest.setBaseUriApi("https://reqres.in");
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.fbm.frametest.abstracts.FrameTestAbstract#executeTearDown()
	*/
	@Override
	public void executeTearDown() {
		// NA	
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.fbm.frametest.abstracts.CrudTestAbstract#createRegister()
	*/
	@Override
	public void createRegister() {
		
		//prepare a UserBO
		newUser = new UserBO();
		
		newUser.setName("fernando bino machado");
		newUser.setJob("try became a software specialist");
		
		//send request to create register
		resp = userRequest.create(newUser);
		
		resp
			.then()
			.statusCode(201);

		//convert response in an UserBO to next tests
		savedUser = (UserBO) UserConverter.stringToObjBO(resp.getBody().asString(), UserBO.class);
		
		//aply some tests
		assertNotNull("Request Was Successfully", resp);
		assertTrue("An User Id Exists", savedUser.getId() > 0 );
		//assertEquals("Same name user", newUser.getName(), savedUser.getName());
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.fbm.frametest.abstracts.CrudTestAbstract#readCreatedRegister()
	*/
	@Override
	public void readCreatedRegister() {
		
		//send request to find user by new user id recently created
		final Response respGetUser = userRequest.getUser(savedUser.getId());
		
		if( respGetUser == null ) {
			fail("User was not found");
			return;
		}
		
		respGetUser
			.then()
			.statusCode(200);
		
		//convert response to next tests
		final ResponseGetUserBO respUsersBO = (ResponseGetUserBO) UserConverter
				.stringToObjBO(respGetUser.getBody().asString(), ResponseGetUserBO.class);

		assertNotNull("An user was found by received id", respUsersBO.getData() );
		
		if( respUsersBO.getData() == null ) {
			return;
		}
		
		assertNotNull("User Email was found", respUsersBO.getData().getEmail() );
		assertNotNull("User Avatar was found", respUsersBO.getData().getAvatar() );
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.fbm.frametest.abstracts.CrudTestAbstract#readRegisters()
	*/
	@Override
	public void readRegisters() {
		
		//send request to get a list users
		final Response respListUsers = userRequest.listUsers();
		
		if( respListUsers == null ) {
			fail("Users was not found");
			return;
		}
		
		respListUsers
			.then()
			.statusCode(200);
			
		//convert response to next tests
		final ResponseUsersBO respUsersBO = (ResponseUsersBO) UserConverter
				.stringToObjBO(respListUsers.getBody().asString(), ResponseUsersBO.class);
	
		assertNotNull("Response was converted to the BO object", respUsersBO);
		
		assertTrue("Users registers was found", !respUsersBO.getData().isEmpty() );
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.fbm.frametest.abstracts.CrudTestAbstract#updateCreatedRegister()
	*/
	@Override
	public void updateCreatedRegister() {
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.fbm.frametest.abstracts.CrudTestAbstract#deleteRegister()
	*/
	@Override
	public void deleteRegister() {
		
	}
	
	
}
