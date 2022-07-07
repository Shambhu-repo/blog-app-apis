package com.codewithdurgesh.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codewithdurgesh.blog.payloads.ApiResponse;

//@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {
	// here ResourceNotFoundException.class throws error message and this error message is passed to ApiResponse and ReponseEntity
	// returns the message with ApiResponse
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex){
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message,false);
		return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class) // this is inbuilt class and exception
	public ResponseEntity<Map<String,String>> methodArgumentNotValidException(MethodArgumentNotValidException ex){
		   Map<String,String> resp = new HashMap<>();
		   ex.getBindingResult().getAllErrors().forEach((error)->{
			   String fieldName = ((FieldError)error).getField();
			   String message = error.getDefaultMessage();
			   resp.put(fieldName, message);
		   });
		   
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
		
	}

}
