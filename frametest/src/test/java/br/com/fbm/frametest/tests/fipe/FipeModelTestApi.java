package br.com.fbm.frametest.tests.fipe;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Rule;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.rules.Timeout;
import org.junit.experimental.categories.Category;

import br.com.fbm.frametest.bo.fipe.ListModelsBO;
import br.com.fbm.frametest.bo.fipe.ModelBO;
import br.com.fbm.frametest.cache.FipeApiCache;
import br.com.fbm.frametest.constants.FipeApiConstants;
import br.com.fbm.frametest.converters.fipe.FipeConverter;
import br.com.fbm.frametest.exception.RegistersNotReturnedException;
import br.com.fbm.frametest.iface.FipeApiFlow;
import br.com.fbm.frametest.requests.fipe.FipeRequest;
import io.restassured.response.Response;

/**
 * {@code FipeTestApi} given implementation
 * to do tests for the Model entity for the
 * parallelun api
 *
 * @author Fernando Bino Machado
 */
public class FipeModelTestApi {
	
	/**
	 * Genericaly for this entity all requests does not need 
	 * more than 3000 miliseconds
	 */
	@Rule
	public Timeout timeoutRequests = new Timeout(3000);

	private static FipeRequest fipeRequest;
	private static List<ModelBO> filteredModels;
	
	@BeforeClass
	public static void setUp() {
		
		FipeApiCache.getCache().addInfo(FipeApiConstants.MODEL_NAME, "Palio 1.0 ECONOMY");
		
		//retrieve brand code returned in the last test
		//and building base url request
		final String baseUrl = new StringBuilder()
				.append("https://parallelum.com.br/fipe/api/v1/carros/marcas/")
				.append(FipeApiCache.getCache().getInfo(FipeApiConstants.BRAND_CODE))
				.append("/modelos")
				.toString();
		
		fipeRequest = new FipeRequest();
		fipeRequest.setBaseUriApi(baseUrl);
		
	}
	
	@Category(FipeApiFlow.class)
	@Test(expected = RegistersNotReturnedException.class)
	public void testCanNotFoundModelAnotherBrand() 
			throws RegistersNotReturnedException {
		
		//send request to retrieve list models
		final Response respListModels = fipeRequest.listModelsByBrandCode();
		
		if( respListModels == null ) {
			fail("No response models was returned to the brand code " + 
					FipeApiCache.getCache().getInfo(FipeApiConstants.BRAND_CODE));
		}
		
		//convert response to next tests
		final ListModelsBO listModels = (ListModelsBO) FipeConverter
				.stringToObjBO(respListModels.getBody().asString(), ListModelsBO.class);

		assertTrue("No Models was found to the brand code" +
				FipeApiCache.getCache().getInfo(FipeApiConstants.BRAND_CODE), 
				!listModels.getListModels().isEmpty());

		final List<ModelBO> models = listModels.getListModels()
				.stream()
				.filter(m -> m.getName().contains("AMAROK"))
				.collect(Collectors.toList());
		
		//input fake ModelBO to tests
		ModelBO fakeModelBO = new ModelBO();
		fakeModelBO.setCode("5585");
		fakeModelBO.setName("AMAROK CD2.0 16V/S CD2.0 16V TDI 4x2 Die");
		//models.add(fakeModelBO);
		
		if( models.isEmpty() ) {
			
			throw new RegistersNotReturnedException("AMAROK was not found to the " + 
					FipeApiCache.getCache().getInfo(FipeApiConstants.BRAND_NAME));
			
		}
				
	}
	
	@Test
	@Category(FipeApiFlow.class)
	public void testGetModelByBrandCode() {
		
		//send request to retrieve list models
		final Response respListModels = fipeRequest.listModelsByBrandCode();
		
		if( respListModels == null ) {
			fail("No response models was returned to the brand code " + 
					FipeApiCache.getCache().getInfo(FipeApiConstants.BRAND_CODE));
		}
		
		//convert response to next tests
		final ListModelsBO listModels = (ListModelsBO) FipeConverter
				.stringToObjBO(respListModels.getBody().asString(), ListModelsBO.class);

		assertTrue("No Models was found to the brand code" +
				FipeApiCache.getCache().getInfo(FipeApiConstants.BRAND_CODE), 
				!listModels.getListModels().isEmpty());

		filteredModels = listModels.getListModels()
				.stream()
				.filter(m -> m.getName().contains(
						FipeApiCache.getCache().getInfo(FipeApiConstants.MODEL_NAME).toString()))
				.collect(Collectors.toList());
		
		assertTrue("Specific models was found", !filteredModels.isEmpty());
		
		FipeApiCache.getCache().addInfo(FipeApiConstants.FILTERED_MODELS, filteredModels);
		
	}
	
	
}
