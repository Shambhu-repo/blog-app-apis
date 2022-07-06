package com.codewithdurgesh.blog.controllers;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.blog.payloads.ApiResponse;
import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//post - create user
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto userDtoCreated =userService.createUser(userDto);
		return new ResponseEntity<>(userDtoCreated, HttpStatus.CREATED);
		
	}
	
	
	//put - update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto , @PathVariable Integer userId){
		UserDto updatedUser = userService.updateUser(userDto, userId);
		return new ResponseEntity<>(updatedUser,HttpStatus.OK); // or RepositoryEntit.ok(updateduser)
	}
	
	//delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid) {
		userService.deleteUser(uid);
		return new ResponseEntity<>(new ApiResponse("User Deleted successfully " , true), HttpStatus.OK);
		
	}
	
	//Get - Alluser
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
	List<UserDto> userDto =	userService.getAllUsers();
	return ResponseEntity.ok(userDto);
	
		
	}
	
	@GetMapping("/{uid}")
	public ResponseEntity<UserDto> getUser(@PathVariable Integer uid){
	UserDto userDto =	userService.getUserById(uid);
	return ResponseEntity.ok(userDto);
	
		
	}

}
