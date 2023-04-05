package br.com.fbm.frametest.tests.geonames;

import static org.junit.Assert.*;
import io.restassured.response.Response;

import org.junit.Test;
import org.junit.BeforeClass;

import br.com.fbm.frametest.requests.geonames.GeoNamesRequest;

/**
 * {@code GeoNamesTestApis} given implementation
 * to do tests for geonames api requests.
 *
 * @author Fernando Bino Machado
 */
public class GeoNamesTestApis {
	
	private static GeoNamesRequest geoNamesRequest;

	@BeforeClass
	public static void setUp() {
		geoNamesRequest = new GeoNamesRequest();
		geoNamesRequest.setBaseUriApi("http://api.geonames.org/postalCodeSearch?postalcode=9011&maxRows=10&username=demo");
	}
	
	@Test
	public void testListCodes() {

		final Response respListCodes = geoNamesRequest.listCodes();
		
		if( respListCodes == null ) {
			fail("No code was returned.");
		}
		
		System.out.println();
		
	}
	
}
