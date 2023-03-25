package br.com.fbm.frametest.strategy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.fbm.frametest.annotation.TestMap;
import br.com.fbm.frametest.iface.FrameTestIface;
import br.com.fbm.frametest.tests.reqres.UserTest;
import br.com.fbm.frametest.constants.FrameTestConstants;

/**
 * {@code StrategyReqResTest} Strategic implementation to
 * debugger tests to the ReqRes Api
 * 
 * @see https://reqres.in/
 *
 * @author Fernando Bino Machado
 */
@RunWith(Suite.class)
@SuiteClasses({
	UserTest.class
})
@TestMap(hostNameApi = FrameTestConstants.REQ_RES_API)
public class StrategyReqResTest implements FrameTestIface {}