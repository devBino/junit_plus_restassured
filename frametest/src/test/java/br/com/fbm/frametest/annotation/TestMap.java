package br.com.fbm.frametest.annotation;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * {@code TestMap} This annotation 
 * must be inputed in the {@code FrameTestIface}
 * Implementations.
 *
 * @author Fernando Bino Machado
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestMap {

	String hostNameApi() default "";
	
}