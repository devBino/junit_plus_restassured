package br.com.fbm.frametest.bo.fakestore;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * {@code ProductBO} represents a product
 * returned 
 *
 * @author Fernando Bino Machado
 */
@JsonPropertyOrder({
	"id",
	"title",
	"price",
	"description",
	"category",
	"image",
	"rating"
})
public class ProductBO implements Serializable {

	private static final long serialVersionUID = 4909575127874304214L;

	private Integer id;
	
	private String title;
	
	private BigDecimal price;
	
	private String description;
	
	private String category;
	
	private String image;
	
	private Object rating;
	
	public ProductBO() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Object getRating() {
		return rating;
	}

	public void setRating(Object rating) {
		this.rating = rating;
	}
	
	
}
