package br.com.fbm.frametest.bo.jsouptests;

import java.io.Serializable;

/**
 * {@code NewsBO} represent a new information
 * retrieved by web scraping in any web site.
 *
 * @author Fernando Bino Machado
 */
public class NewsBO implements Serializable {

	private static final long serialVersionUID = -6753026072325786189L;
	
	private String title;
	private String link;
	private String contents;
	
	public NewsBO() {}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
	
	
}
