package com.codewithdurgesh.blog.services;

import java.util.List;

import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.payloads.PostDto;

public interface PostService {
	
	PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);
	
	PostDto updatePost(PostDto postDto , Integer postId);
	
	void	deletePost(Integer postId);
	
	List<Post> getAllPost();
	
	Post getPostById(Integer postId);

	List<Post> getPostByCategory(Integer categoryId);
	
	List<Post> getPsotByUser(Integer userId);
	
	List<Post> searchPosts(String keyword);
}
