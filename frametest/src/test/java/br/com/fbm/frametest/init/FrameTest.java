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
public class FrameTest {

	public static void main(String[] args) {
		
		final String hostName = (args.length > 0)
				? args[0]
				: FrameTestConstants.REQ_RES_API;
				
		FrameTestProcessor.processImplementationTest(hostName);
		
	}
	
}