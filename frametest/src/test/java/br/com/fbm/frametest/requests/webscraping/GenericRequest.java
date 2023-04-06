package br.com.fbm.frametest.requests.webscraping;

/**
 * {@code GenericRequest} Allow access to new instances consumes
 * {@code GenericRequest} methods and strategies using 
 * {@link org.jsoup.Jsoup} implementations. 
 * 
 * @author Fernando Bino Machado. 
 */
public class GenericRequest extends AbstractRequest {

	public GenericRequest(String url) {
		super(url);
	}
	
}
