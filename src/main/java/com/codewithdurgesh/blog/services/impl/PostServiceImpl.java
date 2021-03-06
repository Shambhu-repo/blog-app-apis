package com.codewithdurgesh.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.payloads.PostResponse;
import com.codewithdurgesh.blog.repositories.CategoryRepo;
import com.codewithdurgesh.blog.repositories.PostRepo;
import com.codewithdurgesh.blog.repositories.UserRepo;
import com.codewithdurgesh.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categroryrepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "UserId", userId));
		Category category =categroryrepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("User", "UserId", categoryId));
	Post post=	modelmapper.map(postDto, Post.class);
	post.setImageName("default.png");
	post.setAddDate(new Date());
	post.setUser(user);
	post.setCategory(category);
	Post newpost = postRepo.save(post);
	//modelmapper.map(newpost, Post.class);
		return modelmapper.map(newpost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "postId", postId));
		//Category category =categroryrepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("User", "UserId", categoryId));
	//Post post=	modelmapper.map(postDto, Post.class);
	post.setImageName(postDto.getImageName());
	post.setAddDate(postDto.getAddedDate());
	//post.setUser(user);
	//post.setCategory(category);
	post.setTitle(postDto.getTitle());
	post.setContent(postDto.getContent());
	Post updatedpost = postRepo.save(post);
	PostDto newpostDto = modelmapper.map(updatedpost, PostDto.class);
	return newpostDto;
	}

	@Override
	public void deletePost(Integer postId) {
	Post post =	postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "postId", postId));
	postRepo.delete(post);
}

	@Override
	public List<PostDto> getAllPost() {
	List<Post> posts =	postRepo.findAll();
	List<PostDto> postDtos=	posts.stream().map((post)->modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post =postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "postid", postId));
		PostDto postdto =modelmapper.map(post,PostDto.class);
		return postdto;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category cat =categroryrepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "categoryid", categoryId));
		List<Post> posts =postRepo.findAByCategory(cat);
	List<PostDto> postDtos =	posts.stream().map((post)->modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPsotByUser(Integer userId) {
	User user= 	userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "userid", userId));
	List<Post> posts = postRepo.findByUser(user);
	List<PostDto> postDtos = posts.stream().map((post)->modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		//List<Post>  posts= postRepo.findByTitleContaining("%"keyword"%"); - > this is if we use Query annotation in PostRepo class
		List<Post>  posts= postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos = posts.stream().map((post)-> modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public PostResponse getAllPostByPagination(Integer pageNumber, Integer pageSize) {
		Pageable p = PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePost = postRepo.findAll(p); //it will have all content or all pages
		List<Post> allPosts = pagePost.getContent();
		List<PostDto> postDtos= allPosts.stream().map((post)->modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public PostResponse getAllPostByPaginationWithSorting(Integer pageNumber, Integer pageSize, String sortBy) {
		
		 Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
	      //Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());   //for descending order
		
		
		Page<Post> pagePost = postRepo.findAll(p); //it will have all content or all pages
		List<Post> allPosts = pagePost.getContent();
		List<PostDto> postDtos= allPosts.stream().map((post)->modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;	
		
	}

	@Override
	public PostResponse getAllPostByPaginationWithSortingAndDirection(Integer pageNumber, Integer pageSize,
			String sortBy, String direction) {
		
		Sort sort = (direction.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		//ternary 
		/*if(direction.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}else {
			sort = Sort.by(sortBy).descending();
		} */
Pageable p = PageRequest.of(pageNumber, pageSize, sort);  
		
		Page<Post> pagePost = postRepo.findAll(p); //it will have all content or all pages
		List<Post> allPosts = pagePost.getContent();
		List<PostDto> postDtos= allPosts.stream().map((post)->modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;	
				
	}

}
