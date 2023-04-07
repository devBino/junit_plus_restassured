package br.com.fbm.frametest.bo.nytimes;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * {@code RssBO} represents a xml tag rss
 * converter to the Java Object from response of this WS
 * https://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml
 *
 * @author Fernando Bino Machado
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RssBO", propOrder = {
		"channelBO"
})
@XmlRootElement(name = "rss")
public class RssBO {

	@XmlElement(name = "channel")
	protected ChannelBO channelBO;

	public ChannelBO getChannelBO() {
		return channelBO;
	}

	public void setChannelBO(ChannelBO channelBO) {
		this.channelBO = channelBO;
	}
	
	
}
