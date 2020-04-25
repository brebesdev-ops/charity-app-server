package com.sk.charity.service;

import java.util.List;

import com.sk.charity.model.Campaign;

public interface CampaignService {

	public Campaign saveCampaign(Campaign campaign);
	
	public List<Campaign> findCampaignByCategoryId(int categoryId);

	public Campaign findCampaignByName(String name);
	
	public List<Campaign> findAllCampaigns();
	
	public Campaign findCampaignById(int campaignId);
}
