package com.sk.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sk.charity.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	Category findByName(String name);
}
