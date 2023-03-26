package br.com.fbm.frametest.bo.reqres;

import java.io.Serializable;

/**
 * {@code ResponseGetUserBO} given a 
 * response BO when we receive
 * users response from the reqres.in
 * after request an user by id
 *
 * @author Fernando Bino Machado
 */
public class ResponseUserBO implements Serializable {

	private static final long serialVersionUID = 4799846180383383708L;
	
	private UserBO data;
	private SuportBO support;
	
	public ResponseUserBO() {}

	public UserBO getData() {
		return data;
	}

	public void setData(UserBO data) {
		this.data = data;
	}

	public SuportBO getSupport() {
		return support;
	}

	public void setSupport(SuportBO support) {
		this.support = support;
	}
	
	
}
