package br.com.fbm.frametest.requests.webscraping;

import java.util.HashMap;
import java.util.Map;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;

/**
 * {@code AbstractRequest} provide a Proxy design
 * pattern to any {@link br.com.fbm.frametest.requests.webscraping.GenericRequest}
 * implementations consumer {@link org.jsoup.Jsoup} methods and strategies
 * implementations.
 *
 * @author Fernando Bino Machado
 */
public abstract class AbstractRequest {

	private boolean sessionStarted;
	private boolean redirect;
	private String url;
	private String userAgent;
	private int timeOut;
	private String referer;
	private String charsetResponse;
	
	private Map<String, String> headers;
	private Map<String, String> data;
	private Map<String, String> cookies;
	
	private Map<String, String> dadosArquivo;
	
	private String contentTypeUploads;
	
	public AbstractRequest(final String url) {
		
		this.url = url;
		
		headers = new HashMap<String, String>(); 
		data = new HashMap<String, String>();
		cookies = new HashMap<String, String>();
		dadosArquivo = new HashMap<String, String>();
		charsetResponse = StandardCharsets.UTF_8.name();
		contentTypeUploads = "text/plain";
		
	}

	public boolean isSessionStarted() {
		return sessionStarted;
	}

	public void setSessionStarted(boolean sessionStarted) {
		this.sessionStarted = sessionStarted;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public Map<String, String> getDadosArquivo() {
		return dadosArquivo;
	}

	public void setDadosArquivo(Map<String, String> dadosArquivo) {
		this.dadosArquivo = dadosArquivo;
	}

	public void addHeaders(String... headers) {
		this.headers.put(headers[0], headers[1]);
	}
	
	public void addParams(String... params) {
		this.data.put(params[0], params[1]);
	}
	
	public Map<String, String> getCookies() {
		return cookies;
	}
	
	public void setContentTypeUploads(final String contentTypeUploads) {
		this.contentTypeUploads = contentTypeUploads;
	}

	public void setCookies(Map<String, String> cookies) {
		this.cookies = cookies;
	}

	public Document get() throws Exception {
		
		Connection con = Jsoup.connect(url);
		
		if( referer != null && !referer.isEmpty() ) {
			con.referrer(referer);
		}
		
		if(timeOut > 0) {
			con.timeout(timeOut);
		}
		
		if( !headers.isEmpty() ) {
			con.headers(headers);
		}
		
		return con.get();
		
	}
	
	public Response getResponse() throws Exception {
		
		Connection con = Jsoup.newSession();
		
		con.url(url);
		
		con.method(Method.GET);
		
		if( !cookies.isEmpty() ) {
			con.cookies(cookies);
		}
		
		if( referer != null && !referer.isEmpty() ) {
			con.referrer(referer);
		}
		
		if(timeOut > 0) {
			con.timeout(timeOut);
		}
		
		if( !headers.isEmpty() ) {
			con.headers(headers);
		}
		
		return con.execute().charset(charsetResponse);
		
	}
	
	public Document post() throws Exception {
		
		Connection con = Jsoup.connect(url);
		
		con.method(Method.POST);
		
		if( !cookies.isEmpty() ) {
			con.cookies(cookies);
		}
		
		if( timeOut > 0 ) {
			con.timeout(timeOut);
		}
		
		if( referer != null && !referer.isEmpty() ) {
			con.referrer(referer);
		}
		
		if( !headers.isEmpty() ) {
			con.headers(headers);
		}
		
		if( !data.isEmpty() ) {
			con.data(data);
		}
		
		if( !dadosArquivo.isEmpty() ) {
			
			con.data(
			dadosArquivo.get("fileParam"),
				dadosArquivo.get("fileName"), 
				new ByteArrayInputStream( dadosArquivo.get("fileContents").getBytes(StandardCharsets.US_ASCII) ),
				contentTypeUploads
			);
			
		}
		
		con.followRedirects(redirect);
		
		return con.post();
		
	}
	
	public Response postResponse() throws Exception {
		
		Connection con = Jsoup.newSession();
		
		con.url(url);
		
		con.method(Method.POST);
		
		if( !cookies.isEmpty() ) {
			con.cookies(cookies);
		}
		
		if( timeOut > 0 ) {
			con.timeout(timeOut);
		}
		
		if( referer != null && !referer.isEmpty() ) {
			con.referrer(referer);
		}
		
		if( !headers.isEmpty() ) {
			con.headers(headers);
		}
		
		if( !data.isEmpty() ) {
			con.data(data);
		}
		
		if( !dadosArquivo.isEmpty() ) {
			
			con.data(
				dadosArquivo.get("fileParam"),
				dadosArquivo.get("fileName"), 
				new ByteArrayInputStream( dadosArquivo.get("fileContents").getBytes(StandardCharsets.US_ASCII) ),
				contentTypeUploads
			);
			
		}
		
		con.followRedirects(redirect);
		
		return con.execute().charset(charsetResponse);
		
	}
	
	
}
