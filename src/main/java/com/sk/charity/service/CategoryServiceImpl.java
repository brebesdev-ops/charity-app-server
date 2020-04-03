package com.sk.charity.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.charity.model.Category;
import com.sk.charity.repository.CategoryRepository;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public void saveCategory(Category category) {
		categoryRepository.save(category);
		
	}

	@Override
	public Category findCategoryByName(String name) {
		return categoryRepository.findByName(name);
	}
	
	@Override
	public List<Category> findAllCategories(){
		return categoryRepository.findAll();
	}

}
