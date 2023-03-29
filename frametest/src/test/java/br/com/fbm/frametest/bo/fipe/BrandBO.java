package br.com.fbm.frametest.bo.fipe;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * {@code BrandBO} representes a brand
 * returned from parallelum api.
 *
 * @author Fernando Bino Machado
 */
@JsonPropertyOrder({"code", "name"})
public class BrandBO implements Serializable {

	private static final long serialVersionUID = 5681971168127855731L;
	
	@JsonProperty("codigo")
	private String code;
	
	@JsonProperty("nome")
	private String name;
	
	public BrandBO() {}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
