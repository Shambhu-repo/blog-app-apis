package com.codewithdurgesh.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	@NotNull
	private String name;
	
	@Email
	private String email;
	
	@NotNull
	private String password;
	
	@NotNull
	private String about;
	
	public UserDto() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@NotEmpty
	@Size(min = 4 , message ="username must be min of 4 characters")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Email (message = "your email address is not valid !!")
	@Pattern(regexp = "^[a-zA-Z0-9]+@[gmail|yahoo]+\\.com")
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@NotEmpty
	@Size (min=6, max=12, message="your password must be minmum of 6 characters and maximum of 12 characters!!")
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@NotEmpty
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	
	
	

}
