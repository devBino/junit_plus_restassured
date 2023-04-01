package br.com.fbm.frametest.bo.fipe;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * {@code ModelBO} represents a car, motocicle and truck
 * model returned of the parallelum api.
 *
 * @author Fernando Bino Machado
 */
@JsonPropertyOrder({"code", "name"})
public class ModelBO implements Serializable {

	private static final long serialVersionUID = -8403415687277265478L;

	@JsonProperty("codigo")
	private String code;
	
	@JsonProperty("nome")
	private String name;
	
	public ModelBO() {}

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
