package br.com.fbm.frametest.tests.moneytimes;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.BeforeClass;

import br.com.fbm.frametest.cache.MoneyTimesCache;
import br.com.fbm.frametest.constants.MoneyTimesConstants;
import br.com.fbm.frametest.requests.webscraping.MoneyTimesVehicleRequest;

/**
 * {@code MoneyTimesWebScrapLinksFeedTest} given implementation
 * to test web scrapping in the money times vehicle.
 *
 * @author Fernando Bino Machado
 */
public class MoneyTimesWebScrapLinksFeedTest {
	
	private static MoneyTimesVehicleRequest request;
	
	@BeforeClass
	public static void setUp() {
		request = new MoneyTimesVehicleRequest();
	}
	
	@Test
	public void testGetLinksFeed() {
		
		//send request to retrieve news link in the money times feed
		request.getLinksFeed("https://www.moneytimes.com.br/ultimas-noticias/");
		
		//test if links was stored at the cache
		if( MoneyTimesCache.getCache().getInfo(MoneyTimesConstants.LINKS_FEEED) == null ) {
			fail("No links was captured from money times vehicle.");
			return;
		}
		
		//test if contents link was stored at the cache
		assertTrue("Links was captured and populated in cache", 
				MoneyTimesCache.getCache().getInfo(MoneyTimesConstants.LINKS_FEEED).toString().length() > 0);
		
		//call the parameterized test to apply test
		//for each new captured
		runnerTestLinkNews();
		
	}
	
	public void runnerTestLinkNews() {

		JUnitCore runner = new JUnitCore();
		
		runner.addListener( new TextListener(System.out) );
		runner.run(CapturedMoneyTimeNewsTest.class);
		
	}
	
	
}
