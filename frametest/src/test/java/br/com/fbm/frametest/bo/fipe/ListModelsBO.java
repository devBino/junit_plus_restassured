package br.com.fbm.frametest.bo.fipe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * {@code ListModelsBO} represents a list models
 * represented by {@code ModelBO} and returned by
 * parallelun api.
 *
 * @author Fernando Bino Machado
 */
@JsonPropertyOrder({"listModels", "years"})
public class ListModelsBO implements Serializable {

	private static final long serialVersionUID = 8415471409307524260L;
	
	@JsonProperty("modelos")
	private List<ModelBO> listModels;
	
	@JsonProperty("anos")
	private List<YearBO> years;

	public ListModelsBO() {}
	
	public List<ModelBO> getListModels() {
		
		if( listModels == null ) {
			listModels = new ArrayList<ModelBO>();
		}
		
		return listModels;
		
	}

	public void setListModels(List<ModelBO> listModels) {
		this.listModels = listModels;
	}
	
	public List<YearBO> getYears() {
		
		if( years == null ) {
			years = new ArrayList<>();
		}
		
		return years;
		
	}

	public void setYears(List<YearBO> years) {
		this.years = years;
	}
	
}
