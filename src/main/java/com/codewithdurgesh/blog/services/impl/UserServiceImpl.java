package com.codewithdurgesh.blog.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.repositories.UserRepo;
import com.codewithdurgesh.blog.services.UserService;
import com.codewithdurgesh.blog.exceptions.*;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.userDtoToUser(userDto);
		User savedUser=userRepo.save(user);
		return this.userToUserDto(savedUser);
		
	}

	@Override
	public UserDto getUserById(Integer userid) {
	//Optional<User> user= 	
	User user=	userRepo.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User","UserId" , userid));
          return this.userToUserDto(user);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
     User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","UserId" , userId));
            user.setName(userDto.getName());
            user.setPassword(userDto.getEmail());
            user.setAbout(userDto.getAbout());
            user.setName(userDto.getName());
           User updatedUser = userRepo.save(user);
        UserDto userDto1=   this.userToUserDto(updatedUser);
        return userDto1;
	}

	@Override
	public List<UserDto> getAllUsers() {
	List<User> userList= 	userRepo.findAll();
	List<UserDto> userDto = userList.stream().map(user->this.userToUserDto(user)).collect(Collectors.toList());
	return userDto;
	
	
	/* UserDto userDto = new UserDto();
	 List<UserDto> userDtoList= new ArrayList<UserDto>();
	 userDtoList.add(userDto);
	for( int i=0;i<userList.size();i++) {
    	 userDto.setName(userList.get(i).getName());
    }
	 return userDtoList;*/
    	 
    }

	@Override
	public void deleteUser(Integer userId) {
	User user=	userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","UserId" , userId));
		userRepo.delete(user);
	}
	
	//conveerting one object to antoher object : - > UserDto to User
	private User userDtoToUser(UserDto userDto ) {  // converting user to userDto
	    User user = modelMapper.map(userDto, User.class);
	    return user;
	    
	    //Manual conversion of data from one to another object
	    /*user.setName(userDto.getName());
		user.setId(userDto.getId());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		return user;  */
	}
	
	//converting from User to UserDto
	private UserDto userToUserDto(User user) {  // converting UserDto to user
		UserDto userDto = modelMapper.map(user,UserDto.class);
		return userDto;
		
		
		/*userDto.setAbout(user.getAbout());
	    userDto.setEmail(user.getEmail());
	    userDto.setId(user.getId());
	    userDto.setName(user.getName());
	    userDto.setPassword(user.getPassword());
	    return userDto; */
	}

}
