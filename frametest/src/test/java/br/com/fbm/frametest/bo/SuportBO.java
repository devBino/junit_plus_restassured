package br.com.fbm.frametest.bo;

import java.io.Serializable;

/**
 * {@code SuportBO} given information of the
 * url and text about reqres.in
 *
 * @author Fernando Bino Machado
 */
public class SuportBO implements Serializable {

	private static final long serialVersionUID = 2021416371033161007L;

	private String url;
	private String text;
	
	public SuportBO() {}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	
}
