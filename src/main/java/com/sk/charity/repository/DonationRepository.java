package com.sk.charity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sk.charity.model.Campaign;
import com.sk.charity.model.Category;
import com.sk.charity.model.Donation;
import com.sk.charity.model.User;

@Repository("donationRepository")
public interface DonationRepository extends JpaRepository<Donation, Long> {
	List<Donation> findByUser(User user);
	
	List<Donation> findByCategory(Category category);
	
	List<Donation> findByCampaign(Campaign campaign);
	
	//Optional<Donation> findByIdAndUser(int id, int userId);
}
