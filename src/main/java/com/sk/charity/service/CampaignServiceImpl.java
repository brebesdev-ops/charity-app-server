package com.sk.charity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.charity.model.Campaign;
import com.sk.charity.model.Category;
import com.sk.charity.repository.CampaignRepository;
import com.sk.charity.repository.CategoryRepository;

@Service("campaignService")
public class CampaignServiceImpl implements CampaignService {

	@Autowired
	CampaignRepository campaignRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public Campaign saveCampaign(Campaign campaign) {
		Category category = categoryRepository.getOne(campaign.getCategoryId());
			
		campaign.setCategory(category);
		return campaignRepository.save(campaign);
	}

	@Override
	public List<Campaign> findCampaignByCategoryId(int categoryId) {
		Category category = categoryRepository.getOne(categoryId);
		return campaignRepository.findByCategory(category);
	}
	
	public Campaign findCampaignByName(String name) {
		return campaignRepository.findByCampaignName(name);
	}

	@Override
	public List<Campaign> findAllCampaigns() {
		return campaignRepository.findAll();
	}
	
	public Campaign findCampaignById(int campaignId) {
		return campaignRepository.getOne(campaignId);
	}

}
