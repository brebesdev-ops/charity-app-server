package com.sk.charity.controller;

import java.util.List;

import javax.persistence.NonUniqueResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sk.charity.model.Campaign;
import com.sk.charity.service.CampaignService;
import com.sk.charity.util.Response;

@RestController
public class CampaignController {

	@Autowired
	Response responseHandler;
	
	@Autowired
	CampaignService campaignService;
	
	@PostMapping("/campaign")
	public Campaign createCampaign(@RequestBody Campaign campaign) {
		Campaign campaignExists = campaignService.findCampaignByName(campaign.getCampaignName());
		
		//Avoid saving duplicates
		if ( campaignExists == null ) {
			return campaignService.saveCampaign(campaign);		
		}else {
			throw new NonUniqueResultException("Campaign with name " + campaign.getCampaignName() + " already exists.");
		}
		
	}
	
	@GetMapping("/campaigns")
	public List<Campaign> allCampaigns() {
		return campaignService.findAllCampaigns();
	}
	
	@GetMapping("/campaign/{campaignId}")
	public Campaign getCampaign(@PathVariable int campaignId) {
		return campaignService.findCampaignById(campaignId);
	}
	
	@GetMapping("/category/{categoryId}/campaigns")
	public List<Campaign> allCampaignsByCategoryId(@PathVariable int categoryId){
		return campaignService.findCampaignByCategoryId(categoryId);
	}
}
