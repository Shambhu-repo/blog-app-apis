package com.codewithdurgesh.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codewithdurgesh.blog.config.AppConstants;
import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.payloads.ApiResponse;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.payloads.PostResponse;
import com.codewithdurgesh.blog.services.FileService;
import com.codewithdurgesh.blog.services.PostService;


@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postservice;
	
	@Autowired    //to upload image
	private FileService fileService;
	
     @Value("${project.image}")
	private String path;
	
	
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
	public ResponseEntity<PostResponse> getAllPostsByPagination(
			@RequestParam(value ="pageNumber", defaultValue = "1", required =false) Integer pageNumber,
			@RequestParam(value="pageSize" , defaultValue = "2", required =false)Integer pageSize   )
	{
		PostResponse allPostBy= postservice.getAllPostByPagination(pageNumber, pageSize);
		return new  ResponseEntity<PostResponse>(allPostBy , HttpStatus.OK);
		
	}
	
	@GetMapping("/posts/pagination/sort")
	public ResponseEntity<PostResponse> getAllPostsByPaginationWithSorting(
			@RequestParam(value ="pageNumber", defaultValue = "0", required =false) Integer pageNumber,
			@RequestParam(value="pageSize" , defaultValue = "2", required =false)Integer pageSize ,
			@RequestParam(value="sortby" , defaultValue = "postId", required =false)String sortBy
			)
	        
	{
		PostResponse allPostBy= postservice.getAllPostByPaginationWithSorting(pageNumber, pageSize,sortBy);
		return new  ResponseEntity<PostResponse>(allPostBy , HttpStatus.OK);
		
	}
	
	@GetMapping("/posts/pagination/direction") //this method will give descending or ascending uisng = asc or dsc
	public ResponseEntity<PostResponse> getAllPostsByPaginationWithSorting(
			@RequestParam(value ="pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required =false) Integer pageNumber,
			@RequestParam(value="pageSize" , defaultValue = AppConstants.PAGE_SIZE, required =false)Integer pageSize ,
			@RequestParam(value="sortby" , defaultValue = AppConstants.SORT_BY, required =false)String sortBy,
			@RequestParam(value="sortDir" , defaultValue = AppConstants.SORT_DIR, required =false)String sortDir)
	        
	{
		PostResponse allPostBy= postservice.getAllPostByPaginationWithSortingAndDirection(pageNumber, pageSize,sortBy,sortDir);
		return new  ResponseEntity<PostResponse>(allPostBy , HttpStatus.OK);
		
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
	
	//search
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostbyTitile(@PathVariable String keywords){
		List<PostDto> result = postservice.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
	}
	
	//post image upoda
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam ("image") MultipartFile image, @PathVariable Integer postId  ) throws IOException
	{
		PostDto postDto = postservice.getPostById(postId);
		String filename = fileService.uploadImage(path, image);	
		postDto.setImageName(filename);
		postDto.setAddedDate(new Date());
		PostDto updatedpost = postservice.updatePost(postDto, postId);
		return new  ResponseEntity<PostDto>(updatedpost,HttpStatus.OK);
	}
	
	//method to serve file or to get the image
	@GetMapping(value="/post/image/{imageName}",produces= MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable ("imageName") String imageName, HttpServletResponse response) throws IOException {
		InputStream resource = fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource,response.getOutputStream());
	}
	

}
