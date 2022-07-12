package com.codewithdurgesh.blog.payloads;

public class CommentDto {

	private Integer id;
	
	private String comment;
	
	/*
	private PostDto postdto;
	
	
	public PostDto getPostdto() {
		return postdto;
	}

	public void setPostdto(PostDto postdto) {
		this.postdto = postdto;
	}
	*/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	

}
