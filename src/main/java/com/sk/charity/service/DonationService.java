package com.sk.charity.service;

import java.util.List;

import com.sk.charity.model.Donation;

public interface DonationService {
	
	public void saveDonation(Donation donation);
	
	public List<Donation> findAllDonations();
	
	public List<Donation> findDonationsByUserId(int userId);

	public List<Donation> findDonationsByCategoryId(int categoryId);
	
	public List<Donation> findDonationsByCampaignId(int campaignId);
}
