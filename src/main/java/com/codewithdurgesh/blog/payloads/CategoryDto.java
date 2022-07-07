package com.codewithdurgesh.blog.payloads;

import javax.persistence.Column;

public class CategoryDto {
	
	public CategoryDto() {
	}
	
	
	private Integer categorId;
	
	private String categoryTitle;
	
	private String categoryDescription;

	public Integer getCategorId() {
		return categorId;
	}

	public void setCategorId(Integer categorId) {
		this.categorId = categorId;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	

}
