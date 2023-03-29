package br.com.fbm.frametest.tests.viacep;

import static org.junit.Assert.*;
import static io.restassured.RestAssured.*;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import org.junit.BeforeClass;

import br.com.fbm.frametest.bo.viacep.CepBO;
import br.com.fbm.frametest.converters.GenericConverter;
import br.com.fbm.frametest.exception.RegistersNotReturnedException;
import br.com.fbm.frametest.iface.TestListCategory;
import br.com.fbm.frametest.iface.TestRegistersNotReturnedExceptionCategory;
import br.com.fbm.frametest.requests.viacep.CepRequest;
import io.restassured.response.Response;


/**
 * {@code TestCep} given implementation
 * to do consumer tests to the via cep api
 *
 * @author Fernando Bino Machado
 */
public class TestCep {

	private static CepBO cepBO;
	private static Response resp;
	
	@BeforeClass
	public static void setUp() {
		
		final CepRequest cepRequest = new CepRequest();
		
		resp = cepRequest.getResponseCep("83603200");
		
		cepBO = (CepBO) GenericConverter
				.stringToObjBO(resp.getBody().asString(), CepBO.class);
		
	}
	
	@Test
	@Category(TestListCategory.class)
	public void testGetFakeCep() {
		
		Response resp = get("https://viacep.com.br/ws/01001000/json/");
		
		System.out.println(resp.getStatusCode());
		System.out.println(resp.getTime());
		System.out.println(resp.getBody().asString());
		System.out.println(resp.getStatusLine());
		System.out.println(resp.getHeader("content-type"));
		
		assertEquals("Status Code 200", resp.getStatusCode(), 200);
		
	}
	
	@Test(expected = RegistersNotReturnedException.class)
	@Category(TestRegistersNotReturnedExceptionCategory.class)
	public void testFakeCepNotReturned() throws RegistersNotReturnedException {
		
		Response resp = get("https://viacep.com.br/ws/83601341/json/");
		
		final CepBO localCepBO = (CepBO) GenericConverter
				.stringToObjBO(resp.getBody().asString(), CepBO.class);
		
		if( localCepBO.getCep() == null ) {
			throw new RegistersNotReturnedException("A Cep was not returned from Api Via Cep.");
		}
		
	}
	
	@Test
	public void requestStatusCodeSuccess() {
		assertTrue("Status Code 200", resp.getStatusCode() == 200);
	}
	
	@Test
	public void testAddressWasFound() {
		assertTrue("Cep Encontrado", cepBO.getCep() != null);
	}
	
	
}
