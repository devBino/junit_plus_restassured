package br.com.fbm.frametest.cache;

import java.util.HashMap;

import br.com.fbm.frametest.abstracts.FrameCacheAbstract;

/**
 * {@code FipeApiCache} provide Singleton implementation
 * to store temporary informations to the parallelun api.
 *
 * @author Fernando Bino Machado
 */
public class FipeApiCache extends FrameCacheAbstract {

	private static FipeApiCache cache;
	
	private FipeApiCache() {
		data = new HashMap<>();
	}
	
	public static FipeApiCache getCache() {
		
		if( cache == null ) {
			cache = new FipeApiCache();
		}
		
		return cache;
		
	}
	
}
