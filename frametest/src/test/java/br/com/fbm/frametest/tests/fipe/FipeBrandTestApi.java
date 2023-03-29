package br.com.fbm.frametest.tests.fipe;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import io.restassured.response.Response;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.BeforeClass;

import br.com.fbm.frametest.requests.fipe.FipeRequest;
import br.com.fbm.frametest.bo.fipe.BrandBO;
import br.com.fbm.frametest.converters.fipe.FipeConverter;
import br.com.fbm.frametest.exception.RegistersNotReturnedException;
import br.com.fbm.frametest.iface.TestListCategory;
import br.com.fbm.frametest.iface.TestRegistersNotReturnedExceptionCategory;

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
	public void testListBrands() {
		
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
	
	@Test(expected = RegistersNotReturnedException.class)
	@Category(TestRegistersNotReturnedExceptionCategory.class)
	public void testBrandsNotReturned() throws RegistersNotReturnedException {
		
		//send request to retrieve a list brands
		final Response respListBrands = fipeRequest.listBrands();
		
		if(respListBrands == null) {
			throw new RegistersNotReturnedException("A expected list brands was not retrieved.");
		}
		
		//Convert response to next tests
		final List<BrandBO> listBrandsBO = FipeConverter
				.stringToListBrandsBO(respListBrands.getBody().asString() );
		
		//try find brandBO code "123123" into the list returned
		//we know this code not exists, so the expeted exception will run
		final Optional<BrandBO> brandBO = listBrandsBO
				.stream()
				.filter(b -> b.getCode().equals("123123"))
				.findFirst();
		
		if( !brandBO.isPresent() ) {
			throw new RegistersNotReturnedException("A expected list brands was not retrieved.");			
		}
		
	}
	
}
