package br.com.fbm.frametest.cache;

import java.util.HashMap;

import br.com.fbm.frametest.abstracts.FrameCacheAbstract;

/**
 * {@code MoneyTimesCache} provide Singleton implementation
 * to store temporary informations to the jsoup web scrapping 
 * testes.
 *
 * @author Fernando Bino Machado
 */
public class MoneyTimesCache extends FrameCacheAbstract {

	private static MoneyTimesCache cache;
	
	private MoneyTimesCache() {
		data = new HashMap<>();
	}
	
	public static MoneyTimesCache getCache() {
		
		if( cache == null ) {
			cache = new MoneyTimesCache();
		}
		
		return cache;
		
	}
	
}
