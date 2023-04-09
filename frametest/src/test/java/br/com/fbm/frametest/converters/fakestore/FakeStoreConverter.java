package br.com.fbm.frametest.converters.fakestore;

import java.util.List;
import java.util.ArrayList;

import br.com.fbm.frametest.bo.fakestore.ProductBO;
import br.com.fbm.frametest.converters.json.GenericJsonConverter;

/**
 * {@code FakeStoreConverter} convert json response
 * to object java according to specific json return 
 * of the fakestore api.
 *
 * @author Fernando Bino Machado
 */
public class FakeStoreConverter extends GenericJsonConverter {

	public static List<ProductBO> stringToListProductsBO(final String pJson) {
		
		try {
			
			final ProductBO[] convertItems = objMapper.readValue(pJson, ProductBO[].class);
			
			if( convertItems != null ) {
				return List.of(convertItems);
			}
			
			return new ArrayList<ProductBO>();
			
		}catch(final Exception exception) {
			return null;
		}
		
	}
	
	
}
