package com.sk.charity.service;

import com.sk.charity.model.Category;

public interface CategoryService {
	public void saveCategory(Category category);
	
	public Category findCategoryByName(String name);
}
