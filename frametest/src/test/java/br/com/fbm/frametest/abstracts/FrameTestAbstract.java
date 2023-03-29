package br.com.fbm.frametest.abstracts;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

/**
 * {@code FrameTestAbstract} provides 
 * abstract methods to execute a
 * generic test simple test.
 *
 * @author Fernando Bino Machado
 */
public abstract class FrameTestAbstract {
	
	@Before
	public void setUp() {
		setUpBefore();
	}
	
	@Test
	public abstract void executeTests();
	
	@After
	public void tearDown() {
		executeTearDown();
	}
	
	public abstract void setUpBefore();
	public abstract void executeTearDown();
	
	
}
