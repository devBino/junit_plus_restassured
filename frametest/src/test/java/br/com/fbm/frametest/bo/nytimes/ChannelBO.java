package br.com.fbm.frametest.bo.nytimes;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * {@code ChannelBO} represents a Channel
 * entity returned from https://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml
 * in String xml returned. 
 *
 * @author Fernando Bino Machado
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChannelBO", propOrder = {
		"nTitle",
		"nLink",
		"listItems"
})
@XmlRootElement(name = "channel")
public class ChannelBO {

	@XmlElement(name = "title")
	protected String nTitle;
	
	@XmlElement(name = "link")
	protected String nLink;
	
	@XmlElement(name = "item")
	protected List<ItemBO> listItems;

	public String getnTitle() {
		return nTitle;
	}

	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}

	public String getnLink() {
		return nLink;
	}

	public void setnLink(String nLink) {
		this.nLink = nLink;
	}

	public List<ItemBO> getListItems() {
		
		if( listItems == null ) {
			listItems = new ArrayList<>();
		}
		
		return listItems;
		
	}

	public void setListItems(List<ItemBO> listItems) {
		this.listItems = listItems;
	}

	
	
}
