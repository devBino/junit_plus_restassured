package br.com.fbm.frametest.tests.fakestore;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

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

	
}
