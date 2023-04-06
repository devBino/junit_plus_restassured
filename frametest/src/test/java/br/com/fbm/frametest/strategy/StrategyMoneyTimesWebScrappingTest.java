package br.com.fbm.frametest.strategy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.fbm.frametest.annotation.TestMap;
import br.com.fbm.frametest.constants.FrameTestConstants;
import br.com.fbm.frametest.iface.FrameTest;
import br.com.fbm.frametest.tests.moneytimes.MoneyTimesWebScrapLinksFeedTest;

/**
 * {@code StrategyMoneyTimesWebScrappingTest} Strategic implementation to
 * debugger tests to the web scrapping capturs from the money times vehicle
 *
 * @author Fernando Bino Machado
 */
@RunWith(Suite.class)
@SuiteClasses({
	MoneyTimesWebScrapLinksFeedTest.class
})
@TestMap(hostNameApi = FrameTestConstants.MONEY_TIMES_CAPTURES)
public class StrategyMoneyTimesWebScrappingTest implements FrameTest {}
