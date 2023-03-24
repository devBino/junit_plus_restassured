/*
 * COPYRIGHT NTT DATA 2023 - ALL RIGHTS RESERVED.
 * 
 * This software is only to be used for the purpose for which it has been
 * provided. No part of it is to be reproduced, disassembled, transmitted,
 * stored in a retrieval system nor translated in any human or computer
 * language in any way or for any other purposes whatsoever without the prior
 * written consent of NTT DATA.
 */
package br.com.fbm.frametest.converters;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * {@code AbstractConverter} given implementations
 * to converter {@code Response#getBody().asString()}
 * to {@code ObjectBO}
 *
 * @author Fernando Bino Machado
 */
public abstract class AbstractConverter {

	protected static ObjectMapper objMapper = new ObjectMapper();
	
	public static String objBoToString(final Object pRespBO) {
		try {
			return objMapper.writeValueAsString(pRespBO);
		}catch(final Exception exception) {
			return null;
		}
	}
	
	public static Object stringToObjBO(final String pJson, final Class<?> pClass) {
		try {
			
			final Object obj = objMapper.readValue(pJson, pClass);
			
			if( obj != null ) {
				return obj;
			}
			
			return pClass.getDeclaredConstructor().newInstance();
			
		}catch(final Exception exception) {
			return null;
		}
	}
	

}
