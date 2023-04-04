package br.com.fbm.frametest.bo.fipe;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * {@code YearsBO} representes a model year
 * returned from parallelum api.
 *
 * @author Fernando Bino Machado
 */
@JsonPropertyOrder({"code","name"})
public class YearBO implements Serializable {

	private static final long serialVersionUID = -2510261195615141653L;
	
	@JsonProperty("codigo")
	private String code;
	
	@JsonProperty("nome")
	private String name;
	
	public YearBO() {}

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
