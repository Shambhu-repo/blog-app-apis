package com.codewithdurgesh.blog.services;

import java.util.List;

import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.payloads.PostDto;

public interface PostService {
	
	PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);
	
	PostDto updatePost(PostDto postDto , Integer postId);
	
	void	deletePost(Integer postId);
	
	List<PostDto> getAllPost();
	
	PostDto getPostById(Integer postId);

	List<PostDto> getPostByCategory(Integer categoryId);
	
	List<PostDto> getPsotByUser(Integer userId);
	
	List<Post> searchPosts(String keyword);
	
	List<PostDto> getAllPostByPagination(Integer pageNumber , Integer pageSize);
}
