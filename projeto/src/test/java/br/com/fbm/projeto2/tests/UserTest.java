/*
 * COPYRIGHT NTT DATA 2023 - ALL RIGHTS RESERVED.
 * 
 * This software is only to be used for the purpose for which it has been
 * provided. No part of it is to be reproduced, disassembled, transmitted,
 * stored in a retrieval system nor translated in any human or computer
 * language in any way or for any other purposes whatsoever without the prior
 * written consent of NTT DATA.
 */
package br.com.fbm.projeto2.tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.BeforeClass;
import org.junit.Ignore;

import br.com.fbm.projeto2.bo.ResponseGetUserBO;
import br.com.fbm.projeto2.bo.ResponseUsersBO;
import br.com.fbm.projeto2.bo.UserBO;
import br.com.fbm.projeto2.converters.UserConverter;
import br.com.fbm.projeto2.requests.UserRequest;
import io.restassured.response.Response;

/**
 * {@code UserTest} given implementation
 * to do tests for the user entity for the
 * reqres.in api
 *
 * @author NTT DATA - fmachado
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
	public void requestCreateUser() {
		assertNotNull("Request Was Successfully", resp);
	}
	
	@Test
	public void userHasCreated() {
		assertTrue("An User Id Exists", savedUser.getId() > 0 );
	}
	
	@Test
	public void nameNewUserWasFound() {
		assertEquals("Same name user", newUser.getName(), savedUser.getName());
	}
	
	@Test
	public void getUserByIdHasRecord() {
		
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
	public void listUsersHasRecords() {
	
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
