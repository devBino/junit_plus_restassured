package br.com.fbm.frametest.tests.fipe;

import static org.junit.Assert.*;
import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import br.com.fbm.frametest.bo.fipe.ModelBO;
import br.com.fbm.frametest.bo.fipe.YearBO;
import br.com.fbm.frametest.cache.FipeApiCache;
import br.com.fbm.frametest.constants.FipeApiConstants;
import br.com.fbm.frametest.converters.fipe.FipeConverter;
import br.com.fbm.frametest.requests.fipe.FipeRequest;
import io.restassured.response.Response;

/**
 * {@code FipeYearsModelTestApi} given implementation
 * to do tests for Years Models response from 
 * parallelun api.
 * 
 * In the {@code FipeModelTestApi} was retrieved a list
 * {@code ModelBO} from parallelun API. So, to complete
 * the flow, this current test {@code FipeYearsModelTestApi}
 * was apply tests for all {@code ModelBO} retrieved before.
 *
 * @author Fernando Bino Machado
 */
@RunWith(Parameterized.class)
public class FipeYearsModelTestApi {

	/**
	 * Attributes for each {@code ModelBO}
	 * received to test.
	 */
	private String code;
	private String name;
	
	/**
	 * This constructors run during the tests,
	 * receiving attributes for each test 
	 * 
	 * @param pCode
	 * @param pName
	 */
	public FipeYearsModelTestApi(final String pCode, final String pName) {
		code = pCode;
		name = pName;
	}
	
	@Parameters
	public static List<Object[]> data(){
		
		final List<Object> filterdModels = (List<Object>) 
				FipeApiCache.getCache().getInfo(FipeApiConstants.FILTERED_MODELS);
		
		final List<Object[]> listAttributes = new ArrayList<>();
		
		filterdModels
			.stream()
			.forEach(obj -> {
				
				final ModelBO modelBO = (ModelBO) obj;
				
				listAttributes.add(new Object[] {
						modelBO.getCode(),
						modelBO.getName()
				});
				
			});
		
		return listAttributes;
		
	}
	
	@Test
	public void testGetYearsByCode() {
		
		//create a simple test message log
		//to show that this test was parameterized
		//with a list ModelsBO retrieved on the last request tests.
		final String msgLog = new StringBuilder()
				.append("\nApply Test to the specifi model ")
				.append("[").append(name).append("]")
				.append(" with code value ")
				.append("[").append(code).append("]")
				.append(".\n")
				.toString();
		
		System.out.println(msgLog);
		
		//create a particular url test for each model
		final String urlParticularTest = new StringBuilder()
				.append(baseURI)
				.append("/")
				.append(code)
				.append("/anos")
				.toString();
		
		//send particular request for each model found
		final FipeRequest fipeRequest = new FipeRequest();
		
		final Response respYearsModel = fipeRequest.listYearsModelsByCodeModel(urlParticularTest);
		
		if(respYearsModel == null){
			fail("No Years was found to the " + name + " model.");
		}
		
		//convert response for each particular model request to the next tests
		final List<YearBO> yearsList = FipeConverter.stringToListYearBOs(
				respYearsModel.getBody().asString());
		
		if( yearsList == null ) {
			fail("No List Years was converted.");
		}
		
		assertTrue("Check List has years to the " + name + " model.", 
				!yearsList.isEmpty());
		
	}
	
	
}
