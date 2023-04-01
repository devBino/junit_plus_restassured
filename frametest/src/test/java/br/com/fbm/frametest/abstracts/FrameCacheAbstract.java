package br.com.fbm.frametest.abstracts;

import java.util.Map;

/**
 * {@code FrameCacheAbstract} provedes generic 
 * implementation to store temporary cache for 
 * api tests.
 *
 * @author Fernando Bino Machado
 */
public class FrameCacheAbstract {

	protected Map<String, Object> data;
	
	public void addInfo(final String pKey, 
			final Object pValue ) {
		
		data.put(pKey, pValue);
		
	}
	
	public Object getInfo(final String pKey) {
		return data.get(pKey);
	}
	
	public void clearCache() {
		data.clear();
	}
	
}
