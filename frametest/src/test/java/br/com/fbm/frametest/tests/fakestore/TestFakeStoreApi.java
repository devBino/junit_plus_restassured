package br.com.fbm.frametest.tests.fakestore;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.fbm.frametest.bo.fakestore.ProductBO;
import br.com.fbm.frametest.converters.fakestore.FakeStoreConverter;
import br.com.fbm.frametest.requests.fakestore.FakeStoreRequest;
import io.restassured.response.Response;

/**
 * {@code TestFakeStoreApi} provide tests to the
 * Fake Store Api.
 *
 * @author Fernando Bino Machado
 */
public class TestFakeStoreApi {

	private static FakeStoreRequest request;
	
	@BeforeClass
	public static void setUp() {
		
		request = new FakeStoreRequest();
		request.setBaseUriApi("https://fakestoreapi.com/products");
		
	}
	
	@Test
	public void testListProducts() {

		//send request to retrieve a registered list products
		final Response respListProducts = request.listProducts();
		
		if(respListProducts == null) {
			fail("No Fake Products was returned.");
			return;
		}
		
		//convert response to next tests
		final List<ProductBO> listProducts = FakeStoreConverter.stringToListProductsBO(
				respListProducts.getBody().asString());
		
		if( listProducts == null ) {
			fail("No Products was found.");
			return;
		}
		
		assertTrue("A List Products was retrieved.", 
				!listProducts.isEmpty());
		
	}
	
	@Test
	public void testGetProductById() {
		
		//send request to get response of the product
		final Response respProduct = request.getProductById(11);
		
		if(respProduct == null) {
			fail("No Product was found.");
			return;
		}
		
		//convert response to ProductBO to next tests
		final ProductBO productBO = (ProductBO) FakeStoreConverter
				.stringToObjBO(respProduct.getBody().asString(), ProductBO.class);
		
		if(productBO == null) {
			fail("No Product was converted.");
			return;
		}
		
		//apply all tests
		assertNotNull("Product Id Was Retrieved", productBO.getId());
		assertNotNull("Product Title Was Retrieved", productBO.getTitle());
		assertNotNull("Product Description Was Retrieved", productBO.getDescription());
		assertNotNull("Product Category Was Retrieved", productBO.getCategory());
		assertNotNull("Product Image Was Retrieved", productBO.getImage());
		
	}
	
	@Test
	public void testSaveProduct() {
		
		//create a fake product
		final ProductBO productBO = new ProductBO();
		
		productBO.setTitle("Xbox Series X");
		productBO.setPrice(new BigDecimal("5559.99"));
		productBO.setDescription("Xbox Series X");
		productBO.setImage("https://m.media-amazon.com/images/I/61eYoSqkHnL._AC_SL1200_.jpg");
		productBO.setCategory("Games");
		
		//send request to save a new product
		final Response respSaveProduct = request.saveProduct(productBO);
		
		if(respSaveProduct == null) {
			fail("No Product was saved.");
			return;
		}
		
		//try convert response in a new ProductBO to next tests
		final ProductBO savedProductBO = (ProductBO) FakeStoreConverter
				.stringToObjBO(respSaveProduct.getBody().asString(), ProductBO.class);

		if( savedProductBO == null ) {
			fail("No product was saved.");
			return;
		}
		
		//apply some tests to check if new product was saved
		assertEquals("Product Title was saved.", productBO.getTitle(), savedProductBO.getTitle());
		
		assertTrue("Product Price was saved.", 
				productBO.getPrice().compareTo(savedProductBO.getPrice()) == 0 );
		
		assertTrue("Product Description was saved.", 
				productBO.getDescription().equals(savedProductBO.getDescription()));
		
	}
	
	@Test
	public void testMockSaveProduct() {
		
		//create a fake product
		final ProductBO productBO = new ProductBO();
		
		productBO.setTitle("Xbox Series X");
		productBO.setPrice(new BigDecimal("5559.99"));
		productBO.setDescription("Xbox Series X");
		productBO.setImage("https://m.media-amazon.com/images/I/61eYoSqkHnL._AC_SL1200_.jpg");
		productBO.setCategory("Games");
		
		//create a fake expected result
		final ProductBO productBOExpected = new ProductBO();
		
		productBOExpected.setId(10);
		productBOExpected.setTitle( productBO.getTitle() );
		productBOExpected.setPrice( productBO.getPrice() );
		productBOExpected.setDescription( productBO.getDescription() );
		productBOExpected.setImage( productBO.getImage() );
		productBOExpected.setCategory( productBO.getCategory() );
		
		//create a Mock to simulate request to create new Product
		final FakeStoreRequest fakeStoreRequest = Mockito.mock(FakeStoreRequest.class);
		
		//traine this mock behavior
		Mockito
			.when( fakeStoreRequest.saveProductAndReturnBO(productBO) )
			.thenReturn( productBOExpected );
		
		//test mock
		final ProductBO mockTestProductBO = fakeStoreRequest.saveProductAndReturnBO(productBO);
		
		//verifify it the mock method was called correct way
		Mockito
			.verify(fakeStoreRequest, Mockito.times(1))
			.saveProductAndReturnBO(productBO);
		
		//apply some tests to check if new product was saved using mock simulated test
		assertTrue("Testing mock Product with returned id 10.", 
				mockTestProductBO.getId() == 10);
		
		assertEquals("Product Title was saved.", productBOExpected.getTitle(), mockTestProductBO.getTitle());
		
		assertTrue("Product Price was saved.", 
				productBOExpected.getPrice().compareTo(mockTestProductBO.getPrice()) == 0 );
		
		assertTrue("Product Description was saved.", 
				productBOExpected.getDescription().equals(mockTestProductBO.getDescription()));
		
	}

	
}
