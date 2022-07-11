package com.codewithdurgesh.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.entities.User;

public interface PostRepo  extends JpaRepository<Post, Integer>{
	
	List<Post> findByUser(User user);
	List<Post> findAByCategory(Category category);
	 List<Post> findByTitleContaining(String title); // Title is field name and Containing : if title contains "java" then it will gives all teh list of post containing java
	 // List<Post> findByContentContaining(String title); // we can use it for every field , Containing will make : Like sql query
  //################################## same as List<Post> findByTitileContaining(String title)
	 
	 //@Query(select p from Post p where p.title like:key")  -> here "key" dynamic injection of value from posServiceImpl
	 //List<Post> findByTitleContaining(@Param ("key") String title);


}
