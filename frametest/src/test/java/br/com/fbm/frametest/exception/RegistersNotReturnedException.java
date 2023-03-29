package br.com.fbm.frametest.exception;

/**
 * {@code RegistersNotReturnedException} capture exceptions
 * when APIs responses does not given registers.
 *
 * @author Fernando Bino Machado.
 */
public class RegistersNotReturnedException extends Exception {

	private static final long serialVersionUID = 4055535435998708712L;

	public RegistersNotReturnedException(final String pMessage) {
		super(pMessage);
	}
	
}
