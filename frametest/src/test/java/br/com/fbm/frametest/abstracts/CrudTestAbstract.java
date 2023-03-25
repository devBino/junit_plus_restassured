package br.com.fbm.frametest.abstracts;

/**
 * {@code CrudTestAbstract} provides abstract 
 * method from the {@code FrameTestAbstract} 
 * and also abstract methods to this {@code CrudTestAbstract}
 * to facilitate Create, Get, Update and Delete tests
 * in an Api.
 *
 * @author Fernando Bino Machado
 */
public abstract class CrudTestAbstract extends FrameTestAbstract {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.fbm.frametest.abstracts.FrameTestAbstract#executeTests()
	*/
	@Override
	public void executeTests() {
		
		testRequireFields();
		createRegister();
		readCreatedRegister();
		readRegisters();
		updateCreatedRegister();
		deleteRegister();
		
	}
	
	public void testRequireFields() {}
	
	public abstract void createRegister();
	
	public abstract void readCreatedRegister();
	
	public void readRegisters() {}
	
	public abstract void updateCreatedRegister();
	
	public abstract void deleteRegister();
	
}
