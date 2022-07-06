package com.codewithdurgesh.blog.payloads;

public class ApiResponse {

	private String messsage;
	private boolean success;
	
	
	public ApiResponse() {
		
	}
	
public ApiResponse(String messsage, boolean success) {
		super();
		this.messsage = messsage;
		this.success = success;
	}




	public String getMesssage() {
		return messsage;
	}


	public void setMesssage(String messsage) {
		this.messsage = messsage;
	}


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}
}
