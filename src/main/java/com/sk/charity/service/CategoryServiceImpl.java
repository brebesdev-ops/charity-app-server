package com.sk.charity.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.sk.charity.model.Category;
import com.sk.charity.repository.CategoryRepository;

public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public void saveCategory(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public Category findCategoryByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
