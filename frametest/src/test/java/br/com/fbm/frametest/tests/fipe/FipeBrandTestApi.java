package br.com.fbm.frametest.tests.fipe;

import static org.junit.Assert.*;

import java.util.List;

import io.restassured.response.Response;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.BeforeClass;

import br.com.fbm.frametest.requests.fipe.FipeRequest;
import br.com.fbm.frametest.bo.fipe.BrandBO;
import br.com.fbm.frametest.converters.fipe.FipeConverter;
import br.com.fbm.frametest.iface.TestListCategory;

/**
 * {@code FipeTestApi} given implementation
 * to do tests for the user entity for the
 * reqres.in api
 *
 * @author Fernando Bino Machado
 */
public class FipeBrandTestApi {

	private static FipeRequest fipeRequest;
	
	@BeforeClass
	public static void setUp() {
		fipeRequest = new FipeRequest();
		fipeRequest.setBaseUriApi("https://parallelum.com.br/fipe/api/v1/carros/marcas");
	}
	
	@Test
	@Category(TestListCategory.class)
	public void testListMarcas() {
		
		//send request to retrieve a list brands
		final Response respListBrands = fipeRequest.listBrands();
		
		if(respListBrands == null) {
			fail("A expected list brands was not retrieved.");
			return;
		}
		
		respListBrands
			.then()
			.statusCode(200);
		
		//Convert response to next tests
		final List<BrandBO> listBrandsBO = FipeConverter
				.stringToListBrandsBO(respListBrands.getBody().asString() );
		
		assertTrue("A List brands was returned", !listBrandsBO.isEmpty());
		
		
	}
	
}
