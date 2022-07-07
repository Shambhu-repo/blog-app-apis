package com.codewithdurgesh.blog.payloads;

import java.util.Date;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.entities.User;

public class PostDto {
	
	
	PostDto(){
		
	}
	
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	private CategoryDto category;
	//pirvate Category category
	private UserDto user;
	//private User user
	
	
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Date getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	
	public CategoryDto getCategory() {
		return category;
	}
	public void setCategory(CategoryDto category) {
		this.category = category;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
