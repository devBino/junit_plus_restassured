package br.com.fbm.frametest.converters.xmls;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * {@code AbstractXmlConverter} given implementations
 * to converter {@code Response#getBody().asString()}
 * to {@code ObjectBO}
 *
 * @author Fernando Bino Machado
 */
public abstract class AbstractXmlConverter {

	public static String objBoToString(final Object pRespBO) {
		return null;
	}
	
	public static Object stringToObjBO(final String pXml, final Class<?> pClass) {
		
		try {
			
			final JAXBContext jaxbCtx = JAXBContext.newInstance(pClass);
			
			final Unmarshaller unmarsh = jaxbCtx.createUnmarshaller();
			
			final StringReader reader = new StringReader(pXml);
			
			final Object objConverted = unmarsh.unmarshal(reader);

			return objConverted;
			
		}catch(final Exception exception) {
			System.out.println(exception.getMessage());
			return null;
		}
		
	}
	

}
