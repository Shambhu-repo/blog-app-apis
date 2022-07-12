package com.codewithdurgesh.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.entities.Comment;
import com.codewithdurgesh.blog.entities.User;

public class PostDto {
	
	
	PostDto(){
		
	}
	private Integer postId;
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	private CategoryDto category;
	//private Category category;   -> error can not convert from category to categoryDto
	private UserDto user;
	//private User user;  -> error can not convert from category to categoryDto
	
	private Set<Comment> comments = new HashSet<Comment>();
	
	/*
	private Set<CommentDto> comments = new HashSet<CommentDto>();
	
	
	public Set<CommentDto> getComments() {
		return comments;
	}
	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}   */  
	
	public String getImageName() {
		return imageName;
	}
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
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
