package br.com.fbm.frametest.strategy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;

import br.com.fbm.frametest.iface.FrameTest;
import br.com.fbm.frametest.annotation.TestMap;
import br.com.fbm.frametest.tests.fipe.FipeBrandTestApi;
import br.com.fbm.frametest.tests.reqres.UserBasicFlowTest;
import br.com.fbm.frametest.tests.viacep.TestCep;
import br.com.fbm.frametest.iface.TestListCategory;
import br.com.fbm.frametest.constants.FrameTestConstants;

/**
 * {@code StrategyCategorieListTest} Strategic implementation to
 * debugger categorized list tests
 *
 * @author Fernando Bino Machado
 */
@RunWith(Categories.class)
@IncludeCategory(TestListCategory.class)
@SuiteClasses({
	UserBasicFlowTest.class,
	TestCep.class,
	FipeBrandTestApi.class
})
@TestMap(hostNameApi = FrameTestConstants.CATEGORY_LIST_TESTS)
public class StrategyCategorieListTest implements FrameTest {}
