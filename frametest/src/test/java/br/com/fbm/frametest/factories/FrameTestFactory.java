package br.com.fbm.frametest.factories;

import static br.com.fbm.frametest.constants.FrameTestConstants.FOLDER_FRAME_TESTS;
import static br.com.fbm.frametest.constants.FrameTestConstants.PACKAGE_FRAME_TESTS;

import java.io.File;

import br.com.fbm.frametest.annotation.TestMap;
import br.com.fbm.frametest.iface.FrameTestIface;

/**
 * {@code FrameTestFactory} Try find {@code FrameTestIface}
 * implementations and return according to specific 
 * received parameters.
 *
 * @author Fernando Bino Machado
 */
public class FrameTestFactory {

	/**
	 * Retrieve {@code FrameTestIface} implementation by Host Name Api.
	 * 
	 * @param pHostName
	 * @return
	 */
	public static FrameTestIface getFrameTestByHostApiName(final String pHostName) {
		
		FrameTestIface implementedTest = null;
		
		try {
			
			final File folderTests = new File(FOLDER_FRAME_TESTS);
			
			for( File file : folderTests.listFiles() ) {
				
				//get Class to check annotation
				final Class<?> implFrameTest = getClasFileImpl(file);
				
				if( !implFrameTest.isAnnotationPresent(TestMap.class) ) {
					continue;
				}
				
				//check hostNameApi value
				final TestMap mapAnnotation = (TestMap) implFrameTest.getAnnotation(TestMap.class);

				if( mapAnnotation.hostNameApi().isEmpty() ) {
					continue;
				}
			
				//if an implementation was found
				if( mapAnnotation.hostNameApi().equals(pHostName) ) {
					implementedTest = (FrameTestIface) implFrameTest.getDeclaredConstructor().newInstance();
					break;
				}
				
			}
			
		}catch(final Exception exception) {
			//TODO BINO - Needs to give a decision here
			//Backlog project has this task - Task 8 - Implement Tests Exceptions
		}
		
		return implementedTest;
		
	}
	
	/**
	 * Retrieve Class<?> of a File to facilitate
	 * check members values.
	 * 
	 * @param pFile
	 * @return
	 * @throws Exception
	 */
	private static Class<?> getClasFileImpl(final File pFile)
			throws Exception {
			
		final String className = pFile.getName().replaceAll(".java", "");
		final Class<?> classFile = Class.forName( 
				String.format("%s.%s", PACKAGE_FRAME_TESTS, className) );
		
		return classFile;
		
	}
	
	
}
