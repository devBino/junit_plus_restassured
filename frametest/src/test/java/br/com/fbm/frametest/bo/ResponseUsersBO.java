package br.com.fbm.frametest.bo;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

/**
 * {@code ResponseUsersBO} given a 
 * response BO when we receive
 * list users response from the reqres.in
 * after request list users per page
 *
 * @author Fernando Bino Machado
 */
public class ResponseUsersBO implements Serializable {

	private static final long serialVersionUID = 4799846180383383708L;
	
	private int page;
	private int per_page;
	private int total;
	private int total_pages;
	private List<UserBO> data;
	private SuportBO support;
	
	public ResponseUsersBO() {}
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPer_page() {
		return per_page;
	}

	public void setPer_page(int per_page) {
		this.per_page = per_page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotal_pages() {
		return total_pages;
	}

	public void setTotal_pages(int total_pages) {
		this.total_pages = total_pages;
	}

	public List<UserBO> getData() {
		
		if( data == null ) {
			data = new ArrayList<>();
		}
		
		return data;
		
	}

	public void setData(List<UserBO> data) {
		this.data = data;
	}

	public SuportBO getSupport() {
		return support;
	}

	public void setSupport(SuportBO support) {
		this.support = support;
	}
	
	
}
