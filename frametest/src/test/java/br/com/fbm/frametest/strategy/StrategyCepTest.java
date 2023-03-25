package br.com.fbm.frametest.strategy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.fbm.frametest.annotation.TestMap;
import br.com.fbm.frametest.iface.FrameTest;
import br.com.fbm.frametest.tests.viacep.TestCep;
import br.com.fbm.frametest.constants.FrameTestConstants;

/**
 * {@code StrategyCepTest} Strategic implementation to
 * debugger tests to the ViaCep Api
 * 
 * @see https://viacep.com.br/
 *
 * @author Fernando Bino Machado
 */
@RunWith(Suite.class)
@SuiteClasses({
	TestCep.class
})
@TestMap(hostNameApi = FrameTestConstants.VIA_CEP_API)
public class StrategyCepTest implements FrameTest {}
