package br.com.fbm.frametest.converters.xmls;

/**
 * {@code ObjConverted} provide implementation
 * to return a generic type according to which 
 * objetc was passed to the {@code AbstractXmlConverter}.
 *
 * @author Fernando Bino Machado
 */
public class ObjConverted<T> {

	private T converted;
	
	public ObjConverted(final T converted) {
		this.converted = converted;
	}

	public T getConverted() {
		return converted;
	}

	public void setConverted(T converted) {
		this.converted = converted;
	}
	
}
