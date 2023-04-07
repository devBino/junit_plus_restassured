package br.com.fbm.frametest.bo.nytimes;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * {@code ItemBO} represente a item in
 * List Items returned from 
 * https://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml
 *
 * @author Fernando Bino Machado
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Item", propOrder = {
		"titleItem",
		"linkItem",
		"categories"
})
public class ItemBO {

	@XmlElement(name = "title")
	protected String titleItem;
	
	@XmlElement(name = "link")
	protected String linkItem;
	
	@XmlElement(name = "category")
	protected List<String> categories;
	
	public String getTitleItem() {
		return titleItem;
	}
	
	public void setTitleItem(String titleItem) {
		this.titleItem = titleItem;
	}
	
	public String getLinkItem() {
		return linkItem;
	}
	
	public void setLinkItem(String linkItem) {
		this.linkItem = linkItem;
	}

	public List<String> getCategories() {
		
		if( categories == null ) {
			categories = new ArrayList<>();
		}
		
		return categories;
		
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	
	
}
