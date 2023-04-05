package br.com.fbm.frametest.strategy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.fbm.frametest.iface.FrameTest;
import br.com.fbm.frametest.annotation.TestMap;
import br.com.fbm.frametest.tests.geonames.GeoNamesTestApis;
import br.com.fbm.frametest.constants.FrameTestConstants;

/**
 * {@code StrategyGeoNamesTest} Strategic implementation to
 * debugger tests to the GeoNames Api
 * 
 * @see http://www.geonames.org/export/web-services.html
 *
 * @author Fernando Bino Machado
 */
@RunWith(Suite.class)
@SuiteClasses({
	GeoNamesTestApis.class
})
@TestMap(hostNameApi = FrameTestConstants.GEO_NAMES_API)
public class StrategyGeoNamesTest implements FrameTest {}
