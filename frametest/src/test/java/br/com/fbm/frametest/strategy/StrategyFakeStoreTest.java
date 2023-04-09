package br.com.fbm.frametest.strategy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.fbm.frametest.annotation.TestMap;
import br.com.fbm.frametest.iface.FrameTest;
import br.com.fbm.frametest.tests.fakestore.TestFakeStoreApi;
import br.com.fbm.frametest.constants.FrameTestConstants;

/**
 * {@code StrategyFakeStoreTest} Strategic implementation to
 * debugger tests to the Fake Store Api
 * 
 * @see https://fakestoreapi.com/docs
 *
 * @author Fernando Bino Machado
 */
@RunWith(Suite.class)
@SuiteClasses({
	TestFakeStoreApi.class
})
@TestMap(hostNameApi = FrameTestConstants.FAKE_STORE_API)
public class StrategyFakeStoreTest implements FrameTest {}
