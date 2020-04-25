package com.sk.charity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sk.charity.model.Donation;
import com.sk.charity.service.DonationService;
import com.sk.charity.util.Response;

@RestController
public class DonationController {

	@Autowired
	DonationService donationService;
	
	@Autowired
	Response res;
	
	@PostMapping("/donations")
	public Response saveDonation(@RequestBody List<Donation> donations) {
		for ( Donation donation : donations)
			donationService.saveDonation(donation);
		
		res.setStatus(201);
		res.setMessage("Donation saved successfully");
		return res;
	}
	
	@GetMapping("/donations")
	public List<Donation> allDonations(){
		return donationService.findAllDonations();
	}
	
	@GetMapping("/user/{userId}/donations")
	public List<Donation> allDonationsByUserId(@PathVariable int userId){
		return donationService.findDonationsByUserId(userId);
	}
	
	@GetMapping("/user/{userId}/donations/total")
	public Double totalDonationByUserId(@PathVariable int userId){
		List<Donation> donations = donationService.findDonationsByUserId(userId);

		Double total = 0.0;
		for (Donation donation : donations) {
			total += donation.getAmount().doubleValue();
		}
		
		return total;
	}
	
	@GetMapping("/category/{categoryId}/donations")
	public List<Donation> allDonationsByCategoryId(@PathVariable int categoryId){
		return donationService.findDonationsByCategoryId(categoryId);
	}
	
	@GetMapping("/campaign/{campaignId}/donations")
	public List<Donation> allDonationsByCampaignId(@PathVariable int campaignId){
		return donationService.findDonationsByCampaignId(campaignId);
	}
}
