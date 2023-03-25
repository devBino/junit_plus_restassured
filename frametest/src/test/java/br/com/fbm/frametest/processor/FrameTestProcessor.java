package br.com.fbm.frametest.processor;

import org.junit.runner.JUnitCore;
import org.junit.internal.TextListener;

import br.com.fbm.frametest.iface.FrameTestIface;
import br.com.fbm.frametest.factories.FrameTestFactory;

/**
 * {@code FrameTestProcessor} provides implementation
 * to process tests to this project.
 *
 * @author Fernando Bino Machado
 */
public class FrameTestProcessor {

	public static void processImplementationTest(final String pHostApiName) {
		
		final FrameTestIface frameTest = FrameTestFactory.getFrameTestByHostApiName(pHostApiName);

		if( frameTest == null ) {
			return;
		}
		
		JUnitCore runner = new JUnitCore();
		
		runner.addListener( new TextListener(System.out) );
		runner.run( frameTest.getClass() );
		
	}
	
	
}
