package com.sk.charity.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NonUniqueResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sk.charity.model.Category;
import com.sk.charity.service.CategoryService;
import com.sk.charity.util.Response;

@RestController
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	Response responseHandler;
	
	@PostMapping("/category")
	public Response createCategory(@RequestBody Category category) {
		Category categoryExists = categoryService.findCategoryByName(category.getName());
		
		//Avoid saving duplicates
		if ( categoryExists == null ) {
			categoryService.saveCategory(category);
		
			responseHandler.setError(null);
			responseHandler.setStatus(201);
			responseHandler.setMessage("Category saved successfully");
		}else {
			throw new NonUniqueResultException("Category with name " + category.getName() + " already exists.");
		}
		
		return responseHandler;
	}
	
	@GetMapping("/categories")
	public List<Category> allCategories(){
		return categoryService.findAllCategories();
	}
	
	
	//Returns category or error if not found
	@GetMapping("/category/{name}")
	public Category categoryByName(@PathVariable String name) {
		Category category = categoryService.findCategoryByName(name);
		
		if (category != null ) {
			return category;
		}else
			throw new EntityNotFoundException("Couldn't find category with name " + name);

	}
	
	@ExceptionHandler(Exception.class)
	public Response excpectionHandler(Exception e) {
		responseHandler.setStatus(403);
		responseHandler.setError(e.getMessage());
		responseHandler.setMessage("");
		
		return responseHandler;
	}
}
