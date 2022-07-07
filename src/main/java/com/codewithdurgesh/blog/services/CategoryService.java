package com.codewithdurgesh.blog.services;

import java.util.List;

import com.codewithdurgesh.blog.payloads.CategoryDto;

public interface CategoryService {
	
	 CategoryDto createCategory(CategoryDto categorydto);
	
	 CategoryDto updateCategory(CategoryDto categorydto, Integer categoryId);
	
	 void deleteCategory(Integer categoryId);

	 CategoryDto getCategory(Integer categoryId);
	
	 List<CategoryDto> getAllCategory();
	

}
