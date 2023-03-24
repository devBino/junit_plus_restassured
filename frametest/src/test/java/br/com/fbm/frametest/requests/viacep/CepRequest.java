package br.com.fbm.frametest.requests.viacep;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;

/**
 * {@code CepRequest} send get request
 * to the defaul api
 *
 * @author Fernando Bino Machado
 */
public class CepRequest {

	public Response getResponseCep(final String pCep) {
		
		final String url = new StringBuilder()
				.append("https://viacep.com.br/ws/")
				.append(pCep)
				.append("/json/")
				.toString();
		
		return get(url);
		
	}
	
}
