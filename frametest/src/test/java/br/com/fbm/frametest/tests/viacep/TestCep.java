package br.com.fbm.frametest.tests.viacep;

import static org.junit.Assert.*;
import static io.restassured.RestAssured.*;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import org.junit.BeforeClass;

import br.com.fbm.frametest.bo.viacep.CepBO;
import br.com.fbm.frametest.converters.GenericConverter;
import br.com.fbm.frametest.iface.TestListCategory;
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
		
		resp
			.then()
			.log()
			.all();
		
		cepBO = (CepBO) GenericConverter
				.stringToObjBO(resp.getBody().asString(), CepBO.class);
		
	}
	
	@Test
	@Category(TestListCategory.class)
	public void simpleTestGetFakeCep() {
		
		Response resp = get("https://viacep.com.br/ws/01001000/json/");
		
		System.out.println(resp.getStatusCode());
		System.out.println(resp.getTime());
		System.out.println(resp.getBody().asString());
		System.out.println(resp.getStatusLine());
		System.out.println(resp.getHeader("content-type"));
		
		assertEquals("Status Code 200", resp.getStatusCode(), 200);
		
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
