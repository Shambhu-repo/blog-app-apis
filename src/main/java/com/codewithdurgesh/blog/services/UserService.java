package com.codewithdurgesh.blog.services;

import java.util.List;

import com.codewithdurgesh.blog.payloads.UserDto;

public interface UserService {
	UserDto createUser(UserDto userDto);  //thiss is UserDto will help to transfer data 
	UserDto getUserById(Integer userid);
	UserDto updateUser(UserDto user, Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);

}
