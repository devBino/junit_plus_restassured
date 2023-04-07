package br.com.fbm.frametest.processor;

import org.junit.runner.JUnitCore;
import org.junit.internal.TextListener;

import br.com.fbm.frametest.iface.FrameTest;
import br.com.fbm.frametest.factories.FrameTestFactory;

/**
 * {@code FrameTestProcessor} provides implementation
 * to process tests to this project.
 *
 * @author Fernando Bino Machado
 */
public class FrameTestProcessor {

	public static void processImplementationsTests(final String... pHostNames) {
		
		for(final String host : pHostNames) {
			processImplementationTest(host);
		}
		
	}

	private static void processImplementationTest(final String pHostApiName) {
		
		final FrameTest frameTest = FrameTestFactory.getFrameTestByHostApiName(pHostApiName);

		if( frameTest == null ) {
			return;
		}
		
		JUnitCore runner = new JUnitCore();
		
		runner.addListener( new TextListener(System.out) );
		runner.run( frameTest.getClass() );
		
	}
	
	
}
