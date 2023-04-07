package br.com.fbm.frametest.tests.nytimes;

import static org.junit.Assert.*;

import io.restassured.response.Response;

import org.junit.Test;

import br.com.fbm.frametest.bo.nytimes.RssBO;
import br.com.fbm.frametest.converters.xmls.GenericXmlConverter;
import br.com.fbm.frametest.requests.nytimes.NyTimesRequest;

import org.junit.BeforeClass;

/**
 * {@code NyTimesRequest} given implementation
 * to do tests for nytimes api requests.
 *
 * @author Fernando Bino Machado
 */
public class NyTimesTestWs {
	
	private static NyTimesRequest nyTimesRequest;

	@BeforeClass
	public static void setUp() {
		nyTimesRequest = new NyTimesRequest();
		nyTimesRequest.setBaseUriApi("https://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml");
	}
	
	@Test
	public void testListNews() {

		final Response respListNews = nyTimesRequest.getXmlChannel();
		
		if( respListNews == null ) {
			fail("No code was returned.");
			return;
		}
		
		final RssBO rssBO = (RssBO) GenericXmlConverter
				.stringToObjBO(respListNews.getBody().asString(), RssBO.class);
		
		if( rssBO == null ) {
			fail("No Java Object was converted.");
			return;
		}
		
		assertTrue("Channel Tag was converted.", 
				rssBO.getChannelBO() != null);
		
		if( rssBO.getChannelBO() == null ) {
			return;
		}
		
		assertTrue("Channel Title was retrieved", 
				(rssBO.getChannelBO().getnTitle() != null
				&& !rssBO.getChannelBO().getnTitle().isEmpty()));
		
		assertTrue("Channel Link was retrieved", 
				(rssBO.getChannelBO().getnLink() != null
				&& !rssBO.getChannelBO().getnLink().isEmpty()));
		
		assertTrue("A List Items was retrieved", (
				!rssBO.getChannelBO().getListItems().isEmpty()));
		
	}
	
}
