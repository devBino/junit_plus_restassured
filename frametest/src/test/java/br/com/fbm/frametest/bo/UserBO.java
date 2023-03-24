package br.com.fbm.frametest.bo;

import java.io.Serializable;

/**
 * {@code UserBO} represente a object
 * json response from the API reqres.in
 * when we have created an user
 *
 * @author Fernando Bino Machado
 */
public class UserBO implements Serializable {

	private static final long serialVersionUID = 6289398411947550441L;

	private int id;
	
	private String name;
	private String job;
	private String createdAt; 
	private String email;
	private String first_name;
	private String last_name;
	private String avatar;
	
	public UserBO() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	
	
}
