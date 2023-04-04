package br.com.fbm.frametest.converters.fipe;

import java.util.ArrayList;
import java.util.List;

import br.com.fbm.frametest.bo.fipe.BrandBO;
import br.com.fbm.frametest.bo.fipe.YearBO;
import br.com.fbm.frametest.converters.GenericConverter;

/**
 * {@code FipeConverter} convert json response
 * to object java according to specific json return 
 * of the parallelum api.
 *
 * @author Fernando Bino Machado
 */
public class FipeConverter extends GenericConverter {

	public static List<BrandBO> stringToListBrandsBO(final String pJson) {
		
		try {
			
			final BrandBO[] convertItems = objMapper.readValue(pJson, BrandBO[].class);
			
			if( convertItems != null ) {
				return List.of(convertItems);
			}
			
			return new ArrayList<BrandBO>();
			
		}catch(final Exception exception) {
			return null;
		}
		
	}
	
	public static List<YearBO> stringToListYearBOs(final String pJson) {
		
		try {
			
			final YearBO[] convertItems = objMapper.readValue(pJson, YearBO[].class);
			
			if( convertItems != null ) {
				return List.of(convertItems);
			}
			
			return new ArrayList<YearBO>();
			
		}catch(final Exception exception) {
			return null;
		}
		
	}
	
}
