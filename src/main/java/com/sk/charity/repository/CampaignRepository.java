package com.sk.charity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sk.charity.model.Campaign;
import com.sk.charity.model.Category;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {
	List<Campaign> findByCategory(Category category);
	
	Campaign findByCampaignName(String name);
		
}
