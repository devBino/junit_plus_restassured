package br.com.fbm.frametest.requests.webscraping;

import java.util.stream.Collectors;

import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;

import br.com.fbm.frametest.bo.jsouptests.NewsBO;
import br.com.fbm.frametest.cache.MoneyTimesCache;
import br.com.fbm.frametest.constants.MoneyTimesConstants;

/**
 * {@code MoneyTimesVehicleRequest} provide implementations
 * to make requests and retrieve news from money times 
 * web site.
 *
 * @author Fernando Bino Machado
 */
public class MoneyTimesVehicleRequest {

	public void getLinksFeed(final String pUrl) {
		
		final GenericRequest request = new GenericRequest(pUrl);
		
		request.setTimeOut(60000);
		
		try {
		
			final Response response = request.getResponse();
			
			final Document document = response.parse();
			
			final String linksFeed = document.selectXpath("//h2[@class=\"news-item__title\"]/a")
					.stream()
					.map(e -> e.attr("href"))
					.collect(Collectors.joining("\n"));
			
			MoneyTimesCache.getCache().addInfo(MoneyTimesConstants.LINKS_FEEED, linksFeed);
		
		}catch(final Exception exception) {
			System.out.println(exception.getMessage());
		}
		
	}
	
	public void finalyzeCaptureNews(final NewsBO pNewsBO) {
		
		final GenericRequest request = new GenericRequest(pNewsBO.getLink());
		
		request.setTimeOut(60000);
		
		try {
		
			final Response response = request.getResponse();
			
			final Document document = response.parse();
		
			pNewsBO.setTitle( document.selectXpath("//h1[@class=\"single__title\"]").text() );
			pNewsBO.setContents( document.selectXpath("//div[@class=\"single__text\"]/*").html() );
		
		}catch(final Exception exception) {
			System.out.println(exception.getMessage());
		}
		
	}
	
}
