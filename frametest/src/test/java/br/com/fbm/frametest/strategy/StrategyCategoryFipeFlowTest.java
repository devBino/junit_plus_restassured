package br.com.fbm.frametest.strategy;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import br.com.fbm.frametest.annotation.TestMap;
import br.com.fbm.frametest.constants.FrameTestConstants;
import br.com.fbm.frametest.iface.FipeApiFlow;
import br.com.fbm.frametest.iface.FrameTest;
import br.com.fbm.frametest.tests.fipe.FipeBrandTestApi;
import br.com.fbm.frametest.tests.fipe.FipeModelTestApi;
import br.com.fbm.frametest.tests.fipe.FipeYearsModelTestApi;

/**
 * {@code StrategyCategoryFipeFlowTest} Strategic implementation to
 * debugger categorized flow fipe api tests
 *
 * @author Fernando Bino Machado
 */
@RunWith(Categories.class)
@IncludeCategory(FipeApiFlow.class)
@SuiteClasses({
	FipeBrandTestApi.class,
	FipeModelTestApi.class
})
@TestMap(hostNameApi = FrameTestConstants.CATEGORY_FIPE_API_FLOW_TESTS)
public class StrategyCategoryFipeFlowTest implements FrameTest {}
