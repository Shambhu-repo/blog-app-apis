package com.codewithdurgesh.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.payloads.ApiResponse;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postservice;
	
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postdto, @PathVariable Integer userId, @PathVariable Integer categoryId ){
		PostDto post = postservice.createPost(postdto, userId, categoryId);
		return new ResponseEntity<PostDto>(post,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postdto, @PathVariable Integer postId){
		PostDto post = postservice.updatePost(postdto, postId);
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
		
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
		List<PostDto> postDtos = postservice.getPsotByUser(userId);
		return new  ResponseEntity<List<PostDto>>(postDtos , HttpStatus.OK);
		
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
		List<PostDto> postDtos = postservice.getPostByCategory(categoryId);
		return new  ResponseEntity<List<PostDto>>(postDtos , HttpStatus.OK);
		
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPosts(){
		List<PostDto> posts = postservice.getAllPost();
		return new  ResponseEntity<List<PostDto>>(posts , HttpStatus.OK);
		
	}
	
	@GetMapping("/posts/pagination")
	public ResponseEntity<List<PostDto>> getAllPostsByPagination(
			@RequestParam(value ="pageNumber", defaultValue = "1", required =false) Integer pageNumber,
			@RequestParam(value="pageSize" , defaultValue = "2", required =false)Integer pageSize   )
	{
		List<PostDto> posts = postservice.getAllPostByPagination(pageNumber, pageSize);
		return new  ResponseEntity<List<PostDto>>(posts , HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePosts(@PathVariable Integer postId){
		postservice.deletePost(postId);
		return new  ResponseEntity<ApiResponse>(new ApiResponse("post is deleted ",true),HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostsByPostId(@PathVariable Integer postId){
		PostDto postdto =postservice.getPostById(postId);
		return new  ResponseEntity<PostDto>(postdto,HttpStatus.OK);
	}
	
	
	

}
