package br.com.fbm.frametest.init;

import br.com.fbm.frametest.constants.FrameTestConstants;
import br.com.fbm.frametest.processor.FrameTestProcessor;

/**
 * {@code FrameTest} pass host name api
 * to the {@code FrameTestProcessor} and it
 * will process test according to host name api.
 *
 * @author Fernando Bino Machado
 */
public class FrameTestInit {

	public static void main(String[] args) {
				
		FrameTestProcessor.processImplementationsTests(
				FrameTestConstants.FAKE_STORE_API,
				FrameTestConstants.CATEGORY_FIPE_API_FLOW_TESTS,
				FrameTestConstants.VIA_CEP_API,
				FrameTestConstants.NY_TIMES_WS,
				FrameTestConstants.MONEY_TIMES_CAPTURES
		);
		
	}
	
}