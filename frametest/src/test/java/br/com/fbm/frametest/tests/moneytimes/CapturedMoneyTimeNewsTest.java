package br.com.fbm.frametest.tests.moneytimes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import br.com.fbm.frametest.bo.jsouptests.NewsBO;
import br.com.fbm.frametest.cache.MoneyTimesCache;
import br.com.fbm.frametest.constants.MoneyTimesConstants;
import br.com.fbm.frametest.requests.webscraping.MoneyTimesVehicleRequest;

/**
 * {@code CapturedMoneyTimeNewsTest} appy test 
 * to check if all money time news links 
 * can be captured
 *
 * @author Fernando Bino Machado
 */
@RunWith(Parameterized.class)
public class CapturedMoneyTimeNewsTest {

	private String linkNews;
	
	public CapturedMoneyTimeNewsTest(final String pLinkNews) {
		linkNews = pLinkNews;
	}

	@Parameters
	public static List<Object[]> data(){
		
		final List<Object[]> listLinks = new ArrayList<>();
		
		final String[] arrLinksCache = MoneyTimesCache.getCache().getInfo(MoneyTimesConstants.LINKS_FEEED).toString().split("\\n"); 
		
		List.of( arrLinksCache )
			.stream()
			.forEach(link -> {
				listLinks.add(new Object[] {link});
			});
		
		return listLinks;
		
	}
	
	@Test
	public void testCaptures() {
		
		//send NewsBO to finalyze capture
		final MoneyTimesVehicleRequest request = new MoneyTimesVehicleRequest();
		
		final NewsBO newsBO = new NewsBO();
		
		newsBO.setLink(linkNews);
		
		request.finalyzeCaptureNews(newsBO);

		//logs
		System.out.println(newsBO.getTitle());
		System.out.println(newsBO.getContents());
		System.out.println("--------------------------------------------------------------");
		
		//check if capture was finished
		assertTrue("News Title was captured.", 
				newsBO.getTitle() != null && !newsBO.getTitle().isEmpty());
		
		assertTrue("News Content was captured.", 
				newsBO.getContents() != null && !newsBO.getContents().isEmpty());
		
	}
	
}
