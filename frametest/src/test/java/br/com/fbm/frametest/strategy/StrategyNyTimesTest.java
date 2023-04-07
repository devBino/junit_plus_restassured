package br.com.fbm.frametest.strategy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.fbm.frametest.iface.FrameTest;
import br.com.fbm.frametest.tests.nytimes.NyTimesTestWs;
import br.com.fbm.frametest.annotation.TestMap;
import br.com.fbm.frametest.constants.FrameTestConstants;

/**
 * {@code StrategyNyTimesTest} Strategic implementation to
 * debugger tests to the GeoNames Api
 * 
 * @see https://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml
 *
 * @author Fernando Bino Machado
 */
@RunWith(Suite.class)
@SuiteClasses({
	NyTimesTestWs.class
})
@TestMap(hostNameApi = FrameTestConstants.NY_TIMES_WS)
public class StrategyNyTimesTest implements FrameTest {}
