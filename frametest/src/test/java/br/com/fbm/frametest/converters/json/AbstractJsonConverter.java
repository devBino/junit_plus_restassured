package br.com.fbm.frametest.converters.json;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * {@code AbstractConverter} given implementations
 * to converter {@code Response#getBody().asString()}
 * to {@code ObjectBO}
 *
 * @author Fernando Bino Machado
 */
public abstract class AbstractJsonConverter {

	protected static ObjectMapper objMapper = new ObjectMapper();
	
	public static String objBoToString(final Object pRespBO) {
		try {
			return objMapper.writeValueAsString(pRespBO);
		}catch(final Exception exception) {
			return null;
		}
	}
	
	public static Object stringToObjBO(final String pJson, final Class<?> pClass) {
		try {
			
			final Object obj = objMapper.readValue(pJson, pClass);
			
			if( obj != null ) {
				return obj;
			}
			
			return pClass.getDeclaredConstructor().newInstance();
			
		}catch(final Exception exception) {
			return null;
		}
	}
	

}
