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
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.blog.payloads.ApiResponse;
import com.codewithdurgesh.blog.payloads.CategoryDto;
import com.codewithdurgesh.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
public ResponseEntity<CategoryDto> createCategroy(@RequestBody CategoryDto categorydto){
		CategoryDto createCategroy =	categoryService.createCategory(categorydto);
		return new ResponseEntity<CategoryDto>(createCategroy,HttpStatus.CREATED);
}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategroy(@RequestBody CategoryDto categorydto, @PathVariable Integer categoryId){
			CategoryDto updatedCategroy =	categoryService.updateCategory(categorydto, categoryId);
			return new ResponseEntity<CategoryDto>(updatedCategroy,HttpStatus.OK);
	}
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategroy(@PathVariable Integer categoryId){
			categoryService.deleteCategory(categoryId);
			return new ResponseEntity<ApiResponse>(new ApiResponse("category Deleted successfully " , true), HttpStatus.OK);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategroy(@PathVariable Integer categoryId){
		CategoryDto categroydto =		categoryService.getCategory(categoryId);
			return new ResponseEntity<CategoryDto>(categroydto, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategroy(){
		    List<CategoryDto> Allcategroydto    =		categoryService.getAllCategory();
			return new ResponseEntity<List<CategoryDto>>(Allcategroydto, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
}
