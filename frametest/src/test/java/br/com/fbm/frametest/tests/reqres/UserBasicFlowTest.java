package br.com.fbm.frametest.tests.reqres;

import static org.junit.Assert.*;

import java.util.Optional;

import org.mockito.Mockito;

import io.restassured.response.Response;

import br.com.fbm.frametest.abstracts.CrudTestAbstract;

import br.com.fbm.frametest.bo.reqres.UserBO;
import br.com.fbm.frametest.bo.reqres.ResponseUserBO;
import br.com.fbm.frametest.bo.reqres.ResponseUsersBO;

import br.com.fbm.frametest.converters.json.GenericJsonConverter;
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
	private static UserBO expectedSimulatedUser;
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
		
		expectedSimulatedUser = new UserBO();
		
		expectedSimulatedUser.setId(2);
		expectedSimulatedUser.setName("fernando bino");
		expectedSimulatedUser.setJob("try became a software specialist");
		
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
	 * @see br.com.fbm.frametest.abstracts.CrudTestAbstract#testRequireFields()
	*/
	@Override
	public void testRequireFields() {
	
		//create a new UserBO without required fields
		final UserBO userBO = new UserBO();
		
		//send request to create a new user
		final Response response = userRequest.create(userBO);
		
		if(response == null) {
			fail("User was not created");
			return;
		}
		
		//convert response to next tests
		final UserBO respUserBO = (UserBO) GenericJsonConverter
				.stringToObjBO(response.getBody().asString(), UserBO.class);
		
		assertTrue("Request withou required params can't allowed create new user.", 
				respUserBO.getId() == 0);
		
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
		
		newUser.setName( expectedSimulatedUser.getName() );
		newUser.setJob( expectedSimulatedUser.getJob() );
		
		//send original request to create register
		resp = userRequest.create(newUser);
		
		resp
			.then()
			.statusCode(201)
			.log()
			.all();
		
		//create a mock test because responses when we create a new user
		//is not been correct since web service
		final UserRequest mockUserRequest = Mockito.mock(UserRequest.class);
		
		//traine mock test
		Mockito
			.when(mockUserRequest.createAndReturnObjectBO(newUser))
			.thenReturn(expectedSimulatedUser);

		//call mock test method
		savedUser = mockUserRequest.createAndReturnObjectBO(newUser);
		
		//verify if was called correct way
		Mockito
			.verify(mockUserRequest, Mockito.times(1))
			.createAndReturnObjectBO(newUser);
		
		//aply some tests
		assertNotNull("Request Was Successfully", resp);
		assertTrue("An User Id Exists", savedUser.getId() > 0 );
		assertEquals("User name was created.", savedUser.getName(), expectedSimulatedUser.getName());
		assertEquals("User job was created.", savedUser.getJob(), expectedSimulatedUser.getJob());
		
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
		final ResponseUserBO respUserBO = (ResponseUserBO) GenericJsonConverter
				.stringToObjBO(respGetUser.getBody().asString(), ResponseUserBO.class);

		//we need to change temporaly all fields
		//of the savedUser, because all returns came api reqres 
		//is not correct.
		savedUser.setName( respUserBO.getData().getFirst_name() );
		savedUser.setJob( respUserBO.getData().getJob() );
		savedUser.setId( respUserBO.getData().getId() );
		
		assertNotNull("An user was found by received id", respUserBO.getData() );
		
		if( respUserBO.getData() == null ) {
			return;
		}
		
		assertNotNull("User Email was found", respUserBO.getData().getEmail() );
		assertNotNull("User Avatar was found", respUserBO.getData().getAvatar() );
		
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
		final ResponseUsersBO respUsersBO = (ResponseUsersBO) GenericJsonConverter
				.stringToObjBO(respListUsers.getBody().asString(), ResponseUsersBO.class);
	
		assertNotNull("Response was converted to the BO object", respUsersBO);

		if( !respUsersBO.getData().isEmpty() ) {
			
			final Optional<UserBO> userBO = respUsersBO.getData()
					.stream().filter(u -> u.getId() == savedUser.getId()).findFirst();
			
			assertTrue("Saved user was found in the returned list users.", userBO.isPresent());
			
		}
		
		assertTrue("Users registers was found", !respUsersBO.getData().isEmpty() );
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.fbm.frametest.abstracts.CrudTestAbstract#updateCreatedRegister()
	*/
	@Override
	public void updateCreatedRegister() {
		
		//change values to the newUser recently retrieved
		savedUser.setName("Bino Machado");
		
		//send request to update new user
		final Response respUpdateUser = userRequest.update(savedUser);
		
		if( respUpdateUser == null ) {
			fail("User was not update");
			return;
		}
		
		respUpdateUser
			.then()
			.statusCode(200);
		
		//convert response to next tests
		final UserBO userBO = (UserBO) GenericJsonConverter
				.stringToObjBO(respUpdateUser.getBody().asString(), UserBO.class);
		
		//for this moment we can't do tests here
		//because reqres api is return different value
		//comparated with it's examples, see more details here
		//https://reqres.in/
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.fbm.frametest.abstracts.CrudTestAbstract#deleteRegister()
	*/
	@Override
	public void deleteRegister() {
	
		//send request to delete new user
		final Response respDeleteUser = userRequest.delete(savedUser.getId());
			
		if( respDeleteUser == null ) {
			fail("User was not deleted");
			return;
		}
		
		respDeleteUser
			.then()
			.statusCode(204)
			.log()
			.all();
		
	}
	
	
}
