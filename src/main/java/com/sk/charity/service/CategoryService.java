package com.sk.charity.service;

import java.util.List;

import com.sk.charity.model.Category;

public interface CategoryService {
	public void saveCategory(Category category);
	
	public Category findCategoryByName(String name);

	public List<Category> findAllCategories();
}
