package com.sk.charity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.charity.model.Campaign;
import com.sk.charity.model.Category;
import com.sk.charity.model.Donation;
import com.sk.charity.model.User;
import com.sk.charity.repository.CampaignRepository;
import com.sk.charity.repository.CategoryRepository;
import com.sk.charity.repository.DonationRepository;
import com.sk.charity.repository.UserRepository;

@Service("donationService")
public class DonationServiceImpl implements DonationService{

	@Autowired
	DonationRepository donationRepository;
	
	@Autowired
	UserRepository userRepository;
		
	@Autowired
	CampaignRepository campaignRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public void saveDonation(Donation donation) {
		
		//If value not given store as null in database
		if (donation.getUserId() != 0 ) {
			User user = userRepository.getOne(donation.getUserId());
			donation.setUser(user);
		}
			
		if (donation.getCategoryId() != 0) {
			Category category = categoryRepository.getOne(donation.getCategoryId());
			donation.setCategory(category);
		}
			
		if (donation.getCampaignId() != 0) {
			Campaign campaign = campaignRepository.getOne(donation.getCampaignId());
			donation.setCampaign(campaign);
		}	
		
		donationRepository.save(donation);	
	}
	
	public List<Donation> findAllDonations(){
		return donationRepository.findAll();
	}
	
	public List<Donation> findDonationsByUserId(int userId){
		User user = userRepository.getOne(userId);
		
		return donationRepository.findByUser(user);
	}

	public List<Donation> findDonationsByCategoryId(int categoryId) {
		Category category = categoryRepository.getOne(categoryId);
		
		return donationRepository.findByCategory(category);
	}

	public List<Donation> findDonationsByCampaignId(int campaignId) {
		Campaign campaign = campaignRepository.getOne(campaignId);
		
		return donationRepository.findByCampaign(campaign);
	}

}
