package br.com.fbm.frametest.strategy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.fbm.frametest.iface.FrameTest;
import br.com.fbm.frametest.annotation.TestMap;
import br.com.fbm.frametest.tests.fipe.FipeBrandTestApi;
import br.com.fbm.frametest.constants.FrameTestConstants;

/**
 * {@code StrategyFipeTest} Strategic implementation to
 * debugger tests to the Parallelum Api
 * 
 * @see https://deividfortuna.github.io/fipe/?ref=publicapis.dev
 *
 * @author Fernando Bino Machado
 */
@RunWith(Suite.class)
@SuiteClasses({
	FipeBrandTestApi.class
})
@TestMap(hostNameApi = FrameTestConstants.FIPE_API)
public class StrategyFipeTest implements FrameTest {}
